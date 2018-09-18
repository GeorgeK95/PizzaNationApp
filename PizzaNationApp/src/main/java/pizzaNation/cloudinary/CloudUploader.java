package pizzaNation.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import pizzaNation.cloudinary.enumerations.Folder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static pizzaNation.cloudinary.CloudConstants.*;

public class CloudUploader {

    public static String uploadImage(MultipartFile image, Folder folderName) {
        Map<Object, Object> cloudParams = configureCloudParams();
        Cloudinary cloudinary = new Cloudinary(cloudParams);

        String directory = folderName.name() + SLASH + image.getOriginalFilename();
        Map params = ObjectUtils.asMap(PUBLIC_ID, directory);
        Map imageResult = null;

        try {
            imageResult = cloudinary.uploader().upload(image.getBytes(), params);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageResult != null ? imageResult.toString() : null;
    }

    private static Map<Object, Object> configureCloudParams() {
        Map<Object, Object> config = new HashMap<>();
        config.put(CLOUD_NAME, CLOUD_NAME_VALUE);
        config.put(API_KEY, API_KEY_VALUE);
        config.put(API_SECRET, SECRET_KEY_VALUE);
        return config;
    }
}
