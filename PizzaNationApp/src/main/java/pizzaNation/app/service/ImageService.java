package pizzaNation.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pizzaNation.app.model.entity.Image;
import pizzaNation.app.service.api.IImageService;
import pizzaNation.app.util.DTOConverter;
import pizzaNation.cloudinary.core.CloudUploader;
import pizzaNation.cloudinary.enumerations.Folder;
import pizzaNation.cloudinary.model.response.CloudResponseModel;

import java.util.UUID;

@Service
@Transactional
public class ImageService implements IImageService {

    private final CloudUploader cloudUploader;

    @Autowired
    public ImageService(CloudUploader cloudUploader) {
        this.cloudUploader = cloudUploader;
    }

    @Override
    public Image uploadImage(MultipartFile picture, Folder folder) {
        String imageName = this.generateUniqueName();
        Object cloudResponse = this.cloudUploader.uploadImage(picture, folder, imageName);
        CloudResponseModel responseObj = DTOConverter.convert(cloudResponse, CloudResponseModel.class);
        return new Image(imageName, responseObj.getUrl());
    }

    private String generateUniqueName() {
        return UUID.randomUUID().toString();
    }

}
