package pizzaNation.app.model.request.contract;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by George-Lenovo on 14/04/2018.
 */
public interface MenuRequestModel {
    String getId();

    String getName();

    String getDescription();

    Integer getPriority();

    MultipartFile getImage();
}
