package pizzaNation.app.model.request.api;

import org.springframework.web.multipart.MultipartFile;

public interface MenuRequestModel {
    String getId();

    String getName();

    String getDescription();

    Integer getPriority();

    MultipartFile getImage();
}
