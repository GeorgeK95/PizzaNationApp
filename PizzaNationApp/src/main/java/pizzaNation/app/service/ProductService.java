package pizzaNation.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.admin.service.IMenuService;
import pizzaNation.app.exception.MenuNotFoundException;
import pizzaNation.app.exception.ProductNotFoundException;
import pizzaNation.app.model.entity.Menu;
import pizzaNation.app.model.entity.Product;
import pizzaNation.app.model.request.AddProductRequestModel;
import pizzaNation.app.model.request.EditProductRequestModel;
import pizzaNation.app.model.response.ProductResponseModel;
import pizzaNation.app.model.view.HomeViewModel;
import pizzaNation.app.model.view.MenuProductsViewModel;
import pizzaNation.app.model.view.ProductViewModel;
import pizzaNation.app.repository.ProductRepository;
import pizzaNation.app.service.contract.IImageService;
import pizzaNation.app.service.contract.IProductService;
import pizzaNation.app.util.DTOConverter;
import pizzaNation.cloudinary.enumerations.Folder;
import pizzaNation.email.factory.EmailFactory;
import pizzaNation.email.model.Mail;
import pizzaNation.email.service.api.IEmailService;
import pizzaNation.user.service.BaseService;
import pizzaNation.user.service.IUserService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static pizzaNation.app.util.WebConstants.*;
import static pizzaNation.email.util.Constants.NEW_PRODUCT_CONTENT;
import static pizzaNation.email.util.Constants.NEW_PRODUCT_SUBJECT;

@Service
@Transactional
public class ProductService extends BaseService implements IProductService {

    private final ProductRepository productRepository;
    private final IMenuService menuService;
    private final IImageService imageService;
    private final IUserService userService;
    private final IEmailService emailService;


    @Autowired
    public ProductService(ProductRepository productService, IImageService imageService, IMenuService menuService, IUserService userService, IEmailService emailService) {
        this.productRepository = productService;
        this.imageService = imageService;
        this.menuService = menuService;
        this.userService = userService;
        this.emailService = emailService;
    }

    @Override
    public HomeViewModel constructHomeModel() {
        ProductViewModel bestSeller = this.tryGetBestSeller();
        ProductViewModel newest = this.tryGetNewest();
        ProductViewModel promotional = this.tryGetPromotional();

        return new HomeViewModel(bestSeller, newest, promotional);
    }

    @Override
    public List<ProductViewModel> findAll() {
        return DTOConverter.convert(this.productRepository.findAllOrderByDate(), ProductViewModel.class);
    }

    @Override
    public List<ProductResponseModel> findAllByDate() {
        return DTOConverter.convert(this.productRepository.findAllOrderByDate(), ProductResponseModel.class);
    }

    @Override
    public Set<Product> getAllByIds(String[] productIds) {
        return this.productRepository.findAllByIdIn(productIds);
    }

    @Override
    public boolean addProduct(AddProductRequestModel addProductRequestModel, RedirectAttributes attributes,
                              BindingResult bindingResult) {
        attributes.addFlashAttribute(ADD_PRODUCT_REQUEST_MODEL, addProductRequestModel);

        if (super.containErrors(bindingResult, attributes, ADD_PRODUCT_ERROR)) return false;

        String productName = addProductRequestModel.getName();
        if (this.checkForDuplicateName(productName, ADD_PRODUCT_ERROR, attributes)) return false;

        this.persistProduct(addProductRequestModel);

        this.sendEmailToSubscribers(productName);

        return true;
    }

    @Override
    public boolean persistProduct(AddProductRequestModel addProductRequestModel) {
        Product product = DTOConverter.convert(addProductRequestModel, Product.class);

        MultipartFile productImage = addProductRequestModel.getImage();
        if (!productImage.getName().isEmpty())
            product.setImage(this.imageService.uploadImage(productImage, Folder.PRODUCT));

        this.productRepository.saveAndFlush(product);

        return true;
    }

    @Override
    public <T> T findByName(String name, Class<T> clazz) {
        Product product = this.productRepository.findByName(name);

        if (product == null) throw new ProductNotFoundException();

        return DTOConverter.convert(product, clazz);
    }

    @Override
    public boolean editProduct(EditProductRequestModel editProductRequestModel, RedirectAttributes attributes, BindingResult bindingResult, String name) {
        attributes.addFlashAttribute(EDIT_PRODUCT_REQUEST_MODEL, editProductRequestModel);

        Product product = this.productRepository.findByName(name);

        if (this.hasErrors(product, editProductRequestModel, bindingResult, attributes)) return false;

        product.setName(editProductRequestModel.getName());
        product.setDetails(editProductRequestModel.getDetails());
        product.setPromotional(editProductRequestModel.getPromotional());
        product.setPrice(editProductRequestModel.getPrice());
        if (!editProductRequestModel.getImage().getOriginalFilename().isEmpty())
            product.setImage(this.imageService.uploadImage(editProductRequestModel.getImage(), Folder.PRODUCT));

        this.productRepository.saveAndFlush(product);

        return true;
    }

    @Override
    public boolean deleteProduct(String name) {
        Product product = this.productRepository.findByName(name);

        if (product == null) throw new ProductNotFoundException();

//        product.setIngredients(null); //release ingredients so they wont be deleted

        this.clearProductFromItsMenus(product);

        this.productRepository.delete(product);

        return true;
    }

    @Override
    public List<MenuProductsViewModel> getMenuProducts(String menuName) {
        if (this.menuService.findByName(menuName) == null) throw new MenuNotFoundException();
        Set<Product> menuProducts = this.productRepository.getMenuProducts(menuName);
        return DTOConverter.convert(menuProducts, MenuProductsViewModel.class);
    }

    private boolean hasErrors(Product product, EditProductRequestModel editMenuRequestModel, BindingResult bindingResult,
                              RedirectAttributes attributes) {
        if (product == null) throw new ProductNotFoundException();

        if (this.checkForDuplicateName(editMenuRequestModel.getName(), EDIT_PRODUCT_ERROR, attributes) &&
                !product.getName().equals(editMenuRequestModel.getName())) return true;

        return super.containErrors(bindingResult, attributes, EDIT_PRODUCT_ERROR);
    }

    private void sendEmailToSubscribers(String productName) {
        Set<String> subscribersEmails = this.userService.getSubscribersEmails();

        for (String email : subscribersEmails) {
            Mail emailVerification = EmailFactory.generateMailObject(ADMIN_EMAIL, email, NEW_PRODUCT_SUBJECT,
                    String.format(NEW_PRODUCT_CONTENT, productName));
            this.emailService.sendSimpleMessage(emailVerification);
        }
    }

    private boolean checkForDuplicateName(String name, String error, RedirectAttributes attributes) {
        if (this.productRepository.existsByName(name)) {
            attributes.addFlashAttribute(error, PRODUCT_NAME_ALREADY_TAKEN_MESSAGE);
            return true;
        }

        return false;
    }

    private void clearProductFromItsMenus(Product product) {
        Set<Product> newSet = new HashSet<>();

        for (Menu menu : product.getMenus()) {
            for (Product curr : menu.getProducts()) {
                if (!curr.getName().equals(product.getName()))
                    newSet.add(curr);
            }

            menu.setProducts(newSet);
            this.menuService.persistMenuEntity(menu);
            newSet = new HashSet<>();
        }
    }

    private ProductViewModel tryGetPromotional() {
        Optional<Product> sellerOptional = this.productRepository.getPromotionalProducts().stream().findFirst();
        return sellerOptional.map(product -> DTOConverter.convert(product, ProductViewModel.class)).orElse(null);
    }

    private ProductViewModel tryGetNewest() {
        Optional<Product> sellerOptional = this.productRepository.findAllOrderByDate().stream().findFirst();
        return sellerOptional.map(product -> DTOConverter.convert(product, ProductViewModel.class)).orElse(null);
    }

    private ProductViewModel tryGetBestSeller() {
        Optional<Product> sellerOptional = this.productRepository.getBestSeller().stream().findFirst();
        return sellerOptional.map(product -> DTOConverter.convert(product, ProductViewModel.class)).orElse(null);
    }

}
