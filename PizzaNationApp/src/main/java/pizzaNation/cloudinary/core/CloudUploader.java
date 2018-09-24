package pizzaNation.cloudinary.core;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pizzaNation.cloudinary.enumerations.Folder;

import java.io.IOException;
import java.util.Map;

import static pizzaNation.app.util.WebConstants.SLASH;
import static pizzaNation.cloudinary.CloudConstants.PUBLIC_ID;

@Component
public class CloudUploader {

    private final Cloudinary cloudinary;

    @Autowired
    public CloudUploader(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public Object uploadImage(MultipartFile image, Folder folderName, String imageName) {
        String directory = folderName.name() + SLASH + imageName;
        Map params = ObjectUtils.asMap(PUBLIC_ID, directory);
        Map imageResult = null;

        try {
            imageResult = this.cloudinary.uploader().upload(image.getBytes(), params);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageResult;
    }


}
