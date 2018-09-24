package pizzaNation.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.admin.repository.MenuRepository;
import pizzaNation.app.exception.MenuNotFoundException;
import pizzaNation.app.exception.ProductNotFoundException;
import pizzaNation.app.model.entity.Product;
import pizzaNation.app.model.request.AddProductRequestModel;
import pizzaNation.app.model.response.ProductResponseModel;
import pizzaNation.app.model.view.HomeViewModel;
import pizzaNation.app.model.view.MenuProductsViewModel;
import pizzaNation.app.model.view.ProductViewModel;
import pizzaNation.app.repository.ProductRepository;
import pizzaNation.app.service.api.IImageService;
import pizzaNation.app.service.api.IProductService;
import pizzaNation.app.util.DTOConverter;
import pizzaNation.cloudinary.enumerations.Folder;
import pizzaNation.email.factory.EmailFactory;
import pizzaNation.email.model.Mail;
import pizzaNation.email.service.api.IEmailService;
import pizzaNation.user.service.BaseService;
import pizzaNation.user.service.api.IUserService;

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
    private final MenuRepository menuRepository;
    private final IImageService imageService;
    private final IUserService userService;
    private final IEmailService emailService;


    @Autowired
    public ProductService(ProductRepository productRepository, MenuRepository menuRepository, IImageService imageService, IUserService userService, IEmailService emailService) {
        this.productRepository = productRepository;
        this.menuRepository = menuRepository;
        this.imageService = imageService;
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

        if (this.productRepository.existsByName(productName)) {
            attributes.addFlashAttribute(ADD_PRODUCT_ERROR, PRODUCT_NAME_ALREADY_TAKEN_MESSAGE);
            return false;
        }
//        if (this.checkForDuplicateName(productName, ADD_PRODUCT_ERROR, attributes)) return false;

        this.persistProduct(addProductRequestModel);

        new Thread(() -> this.sendEmailToSubscribers(productName)).start();

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
    public List<MenuProductsViewModel> getMenuProducts(String menuName) {
        if (this.menuRepository.findByName(menuName) == null) throw new MenuNotFoundException();
        Set<Product> menuProducts = this.productRepository.getMenuProducts(menuName);
        return DTOConverter.convert(menuProducts, MenuProductsViewModel.class);
    }

    private void sendEmailToSubscribers(String productName) {
        Set<String> subscribersEmails = this.userService.getSubscribersEmails();

        for (String email : subscribersEmails) {
            Mail emailVerification = EmailFactory.generateMailObject(ADMIN_EMAIL, email, NEW_PRODUCT_SUBJECT,
                    String.format(NEW_PRODUCT_CONTENT, productName));
            this.emailService.sendSimpleMessage(emailVerification);
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
