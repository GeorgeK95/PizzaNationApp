package pizzaNation.pcloud.image;

import com.google.gson.Gson;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;
import pizzaNation.pcloud.auth.CloudAuthorizationService;
import pizzaNation.pcloud.http3.HttpRequestExecutor;
import pizzaNation.app.model.entity.Image;

import java.io.IOException;
import java.util.*;

@Component
public class CloudImageExtractor {
    private static final String PICTURE_FOLDER_ID = "1646477902";

    private static final String QUERY_PATH_SEPARATOR = "?";

    private static final String QUERY_PARAMETER_SEPARATOR = "&";

    private static final String FOLDER_ID_PARAMETER = "folderid=";

    private static final String FILE_ID_PARAMETER = "fileid=";

    private static final String AUTH_PARAMETER = "auth=";

    private static final String CODE_PARAMETER = "code=";

    private static final String LIST_FOLDER_URL
            = "https://api.pcloud.com/listfolder";

    private static final String LIST_FILE_URL
            = "https://api.pcloud.com/getfilepublink";

    private static final String DOWNLOAD_FILE_URL
            = "https://api.pcloud.com/getpublinkdownload";

    private final HttpRequestExecutor httpRequestExecutor;

    private final CloudAuthorizationService cloudAuthorizationService;

    public CloudImageExtractor(HttpRequestExecutor httpRequestExecutor, CloudAuthorizationService cloudAuthorizationService) {
        this.httpRequestExecutor = httpRequestExecutor;
        this.cloudAuthorizationService = cloudAuthorizationService;
    }

    @CachePut("images")
    public List<Image> getAllImages() throws IOException {
        Gson gson = new Gson();
        String accessToken = this.cloudAuthorizationService.getAccessToken();

        String folderJsonResult = this.httpRequestExecutor.executeGetRequest(
                LIST_FOLDER_URL
                        + QUERY_PATH_SEPARATOR
                        + FOLDER_ID_PARAMETER
                        + PICTURE_FOLDER_ID
                        + QUERY_PARAMETER_SEPARATOR
                        + AUTH_PARAMETER
                        + accessToken
        ).body()
                .string();

        Map<String, Object> folderData = gson.fromJson(folderJsonResult, Map.class);

        ArrayList<Map<String, Object>> filesData = (ArrayList<Map<String, Object>>) ((Map<String, Object>) folderData.get("metadata")).get("contents");

        List<Image> resultImages = new ArrayList<Image>();

        for (Map<String, Object> singleFileData : filesData) {
            String fileId = singleFileData.get("id").toString().substring(1);

            String fileListJsonResult = this.httpRequestExecutor.executeGetRequest(
                    LIST_FILE_URL
                            + QUERY_PATH_SEPARATOR
                            + FILE_ID_PARAMETER
                            + fileId
                            + QUERY_PARAMETER_SEPARATOR
                            + AUTH_PARAMETER
                            + accessToken
            ).body()
                    .string();

            String fileCode = gson.fromJson(fileListJsonResult, Map.class).get("code").toString();

            String fileDownloadJsonResult = this.httpRequestExecutor.executeGetRequest(
                    DOWNLOAD_FILE_URL
                            + QUERY_PATH_SEPARATOR
                            + CODE_PARAMETER
                            + fileCode
            ).body()
                    .string();

            Map<String, Object> fileDownloadData = gson.fromJson(fileDownloadJsonResult, Map.class);

            String filePath = fileDownloadData.get("path").toString();
            String host = ((ArrayList<String>) fileDownloadData.get("hosts")).get(0);

            String fileName = singleFileData.get("name").toString();
            String fileUrl = "https://" + host + filePath;

            Image image = new Image(fileName, fileUrl);

            resultImages.add(image);
        }

        return resultImages;
    }

    public Image getImageByName(String imageName) {
        try {
            return this.getAllImages().stream().filter(i -> i.getName().equals(imageName)).findFirst().get();
        } catch (IOException | NoSuchElementException e) {
            e.printStackTrace();
        }

        return null;
    }
}