package pizzaNation.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pizzaNation.app.service.contract.IImageService;
import pizzaNation.pcloud.image.CloudImageExtractor;
import pizzaNation.pcloud.image.CloudImageUploader;
import pizzaNation.app.model.entity.Image;
import pizzaNation.app.repository.ImageRepository;

/**
 * Created by George-Lenovo on 13/04/2018.
 */
@Service
@Transactional
public class ImageService implements IImageService {

    private final CloudImageUploader cloudImageUploader;

    private final CloudImageExtractor cloudImageExtractor;

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(CloudImageUploader cloudImageUploader, CloudImageExtractor cloudImageExtractor, ImageRepository imageRepository) {
        this.cloudImageUploader = cloudImageUploader;
        this.cloudImageExtractor = cloudImageExtractor;
        this.imageRepository = imageRepository;
    }

    @Override
    public Image uploadImage(MultipartFile picture) {
        String imageName = this.cloudImageUploader.uploadImage(picture);
        return this.cloudImageExtractor.getImageByName(imageName);
//        this.imageRepository.saveAndFlush(image);
    }

}
