package pizzaNation.app.service;

import com.cloudinary.Cloudinary;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pizzaNation.app.service.contract.IImageService;
import pizzaNation.app.model.entity.Image;
import pizzaNation.app.repository.ImageRepository;
import pizzaNation.app.util.DTOConverter;
import pizzaNation.cloudinary.CloudUploader;
import pizzaNation.cloudinary.enumerations.Folder;
import pizzaNation.cloudinary.model.response.CloudResponseModel;

@Service
@Transactional
public class ImageService implements IImageService {

    @Override
    public Image uploadImage(MultipartFile picture, Folder folder) {
        String response = CloudUploader.uploadImage(picture, folder);
        CloudResponseModel cloudResponseModel = new Gson().fromJson(response, CloudResponseModel.class);
        Image proba = DTOConverter.convert(cloudResponseModel, Image.class);
        return new Image(picture.getOriginalFilename(), cloudResponseModel.getUrl());
    }

}
