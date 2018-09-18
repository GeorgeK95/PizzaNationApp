package com.example.demo.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CloudUploader {

    public static final String CLOUD_NAME = "cloud_name";
    public static final String API_KEY = "api_key";
    public static final String API_SECRET = "api_secret";
    public static final String SECRET_KEY_VALUE = "ZCFd6O17dwRHFzxCnElqgte8c6I";
    public static final String API_KEY_VALUE = "625643926957863";
    public static final String CLOUD_NAME_VALUE = "ddtsclgue";
    public static final String PUBLIC_ID = "public_id";

    public static String uploadImage(MultipartFile image) throws IOException {
        Map<Object, Object> cloudParams = configureCloudParams();
        Cloudinary cloudinary = new Cloudinary(cloudParams);

        String directory = "my_folder/my_name";
        Map params = ObjectUtils.asMap(PUBLIC_ID, directory);
        Map imageResult = cloudinary.uploader().upload(image.getBytes(), params);

        return imageResult.toString();
    }

    private static Map<Object, Object> configureCloudParams() {
        Map<Object, Object> config = new HashMap<>();
        config.put(CLOUD_NAME, CLOUD_NAME_VALUE);
        config.put(API_KEY, API_KEY_VALUE);
        config.put(API_SECRET, SECRET_KEY_VALUE);
        return config;
    }
}
