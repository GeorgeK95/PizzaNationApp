package pizzaNation.app.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.admin.repository.MenuRepository;
import pizzaNation.app.exception.MenuNotFoundException;
import pizzaNation.app.exception.ProductNotFoundException;
import pizzaNation.app.model.entity.Menu;
import pizzaNation.app.model.entity.Product;
import pizzaNation.app.model.request.AddProductRequestModel;
import pizzaNation.app.model.request.EditProductRequestModel;
import pizzaNation.app.model.response.ProductResponseModel;
import pizzaNation.app.model.transfer.EmailVerification;
import pizzaNation.app.model.view.HomeViewModel;
import pizzaNation.app.model.view.MenuProductsViewModel;
import pizzaNation.app.model.view.ProductViewModel;
import pizzaNation.app.repository.ProductRepository;
import pizzaNation.app.service.contract.IImageService;
import pizzaNation.app.service.contract.IIngredientService;
import pizzaNation.app.service.contract.IProductService;
import pizzaNation.app.util.DTOConverter;
import pizzaNation.user.service.BaseService;
import pizzaNation.user.service.IUserService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static pizzaNation.app.util.WebConstants.*;

/**
 * Created by George-Lenovo on 01/04/2018.
 */
@Service
@Transactional
public class ProductService extends BaseService implements IProductService {

    private final ProductRepository productRepository;

    private final MenuRepository menuRepository;

    private final IIngredientService ingredientService;

    private final IImageService imageService;

    private final JmsTemplate jmsTemplate;

    private final IUserService userService;


    @Autowired
    public ProductService(ProductRepository productService, IIngredientService ingredientService, IImageService imageService, MenuRepository menuRepository, JmsTemplate jmsTemplate, IUserService userService) {
        this.productRepository = productService;
        this.ingredientService = ingredientService;
        this.imageService = imageService;
        this.menuRepository = menuRepository;
        this.jmsTemplate = jmsTemplate;
        this.userService = userService;
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

        if (this.checkForDuplicateName(addProductRequestModel.getName(), ADD_PRODUCT_ERROR, attributes)) return false;

        this.persistProduct(addProductRequestModel);

        new Thread(this::sendEmailToSubscribers).start();

        return true;
    }

    @Override
    public boolean persistProduct(AddProductRequestModel addProductRequestModel) {
        Product product = DTOConverter.convert(addProductRequestModel, Product.class);

        MultipartFile productImage = addProductRequestModel.getImage();
        if (!productImage.getName().isEmpty()) product.setImage(this.imageService.uploadImage(productImage));

        this.productRepository.saveAndFlush(product);

        return true;
    }

    @Override
    public <T> T findByName(String name, Class<T> clazz) {
        Product product = this.productRepository.findByName(name);

        if (product == null) throw new ProductNotFoundException();

        T model = DTOConverter.convert(product, clazz);

        return model;
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
            product.setImage(this.imageService.uploadImage(editProductRequestModel.getImage()));

        this.productRepository.saveAndFlush(product);

        return true;
    }

    @Override
    public boolean deleteProduct(String name) {
        Product product = this.productRepository.findByName(name);

        if (product == null) throw new ProductNotFoundException();

        product.setIngredients(null); //release ingredients so they wont be deleted

        this.clearProductFromItsMenus(product);

        this.productRepository.delete(product);

        return true;
    }

    @Override
    public List<MenuProductsViewModel> getMenuProducts(String menuName) {
         if (this.menuRepository.findByName(menuName) == null) throw new MenuNotFoundException();

        Set<Product> menuProducts = this.productRepository.getMenuProducts(menuName);
        List<MenuProductsViewModel> convert = DTOConverter.convert(menuProducts, MenuProductsViewModel.class);
        return convert;
    }

    private boolean hasErrors(Product product, EditProductRequestModel editMenuRequestModel, BindingResult bindingResult,
                              RedirectAttributes attributes) {
        if (product == null) throw new ProductNotFoundException();

        if (this.checkForDuplicateName(editMenuRequestModel.getName(), EDIT_PRODUCT_ERROR, attributes) &&
                !product.getName().equals(editMenuRequestModel.getName())) return true;

        return super.containErrors(bindingResult, attributes, EDIT_PRODUCT_ERROR);
    }

    private void sendEmailToSubscribers() {
        Set<String> subscribersEmails = this.userService.getSubscribersEmails();

        for (String email : subscribersEmails) {
            this.jmsTemplate.convertAndSend(PROMOTIONAL_PRODUCT_DESTINATION,
                    new Gson().toJson(new EmailVerification(email, PROMOTIONAL_PRODUCTS_ARRIVED_MESSAGE)));
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
            this.menuRepository.saveAndFlush(menu);
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
