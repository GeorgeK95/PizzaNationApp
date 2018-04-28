package pizzaNation.pcloud.image;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pizzaNation.pcloud.auth.CloudAuthorizationService;
import pizzaNation.pcloud.http3.HttpRequestExecutor;

import java.io.IOException;

@Component
public class CloudImageUploader {
    static final String PICTURE_FOLDER_ID = "1646477902";

    private static final String QUERY_PATH_SEPARATOR = "?";

    private static final String QUERY_PARAMETER_SEPARATOR = "&";

    private static final String AUTH_PARAMETER = "auth=";

    private static final String FOLDER_ID_PARAMETER = "folderid=";

    private static final String FILE_NAME_PARAMETER = "filename=";

    private static final String UPLOAD_FILE_URL
            = "https://api.pcloud.com/uploadfile";

    private final HttpRequestExecutor httpRequestExecutor;

    private final CloudAuthorizationService cloudAuthorizationService;

    public CloudImageUploader(HttpRequestExecutor httpRequestExecutor, CloudAuthorizationService cloudAuthorizationService) {
        this.httpRequestExecutor = httpRequestExecutor;
        this.cloudAuthorizationService = cloudAuthorizationService;
    }

    public String uploadImage(MultipartFile file) {
        try {
            this.uploadFile(file);
            return file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void uploadFile(MultipartFile file) throws IOException {
        this.httpRequestExecutor.executePostRequest(
                (UPLOAD_FILE_URL
                        + QUERY_PATH_SEPARATOR
                        + FOLDER_ID_PARAMETER
                        + PICTURE_FOLDER_ID
                        + QUERY_PARAMETER_SEPARATOR
                        + FILE_NAME_PARAMETER
                        + file.getOriginalFilename()
                        + QUERY_PARAMETER_SEPARATOR
                        + AUTH_PARAMETER
                        + this.cloudAuthorizationService.getAccessToken())
                , file.getContentType()
                , file.getBytes()
        );
    }
}
