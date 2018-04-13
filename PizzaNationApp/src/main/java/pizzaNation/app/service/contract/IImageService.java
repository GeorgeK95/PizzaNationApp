package pizzaNation.app.service.contract;

import org.springframework.web.multipart.MultipartFile;
import pizzaNation.app.model.entity.Image;

/**
 * Created by George-Lenovo on 13/04/2018.
 */
public interface IImageService {

    Image uploadImage(MultipartFile picture);
}
