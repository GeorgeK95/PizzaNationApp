package pizzaNation.app.service.contract;

import org.springframework.web.multipart.MultipartFile;
import pizzaNation.app.model.entity.Image;
import pizzaNation.cloudinary.enumerations.Folder;

public interface IImageService {

    Image uploadImage(MultipartFile picture, Folder folder);
}
