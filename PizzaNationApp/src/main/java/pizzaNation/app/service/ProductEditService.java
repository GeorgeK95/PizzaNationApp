package pizzaNation.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaNation.app.exception.ProductNotFoundException;
import pizzaNation.app.model.entity.Product;
import pizzaNation.app.model.request.EditProductRequestModel;
import pizzaNation.app.repository.ProductRepository;
import pizzaNation.app.service.api.IImageService;
import pizzaNation.app.service.api.IProductEditService;
import pizzaNation.cloudinary.enumerations.Folder;
import pizzaNation.user.service.BaseService;

import static pizzaNation.app.util.WebConstants.*;

@Service
@Transactional
public class ProductEditService extends BaseService implements IProductEditService {
    private final ProductRepository productRepository;
    private final IImageService imageService;

    @Autowired
    public ProductEditService(ProductRepository productRepository, IImageService imageService) {
        this.productRepository = productRepository;
        this.imageService = imageService;
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

    private boolean hasErrors(Product product, EditProductRequestModel editMenuRequestModel, BindingResult bindingResult,
                              RedirectAttributes attributes) {
        if (product == null) throw new ProductNotFoundException();

        if (this.checkForDuplicateName(editMenuRequestModel.getName(), EDIT_PRODUCT_ERROR, attributes) &&
                !product.getName().equals(editMenuRequestModel.getName())) return true;

        return super.containErrors(bindingResult, attributes, EDIT_PRODUCT_ERROR);
    }

    private boolean checkForDuplicateName(String name, String error, RedirectAttributes attributes) {
        if (this.productRepository.existsByName(name)) {
            attributes.addFlashAttribute(error, PRODUCT_NAME_ALREADY_TAKEN_MESSAGE);
            return true;
        }

        return false;
    }

}
