package kodlamaio.hrms.core.utilities.images.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import kodlamaio.hrms.core.Environment;
import kodlamaio.hrms.core.utilities.images.ImageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryManager implements ImageService {
    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryManager() {
        Map<String, String> values = new HashMap<>();
        var environment = new Environment();

        values.put("cloud_name", environment.getCloudName());
        values.put("api_key", environment.getApiKey());
        values.put("api_secret", environment.getApiSecret());

        this.cloudinary = new Cloudinary(values);
    }

    public DataResult<?> upload(MultipartFile multipartFile) throws IOException {
        var result = this.cloudinary.uploader().upload(multipartFile.getBytes(), ObjectUtils.emptyMap());
        return new SuccessDataResult<>(result);
    }

    public DataResult<?> delete(int id) throws IOException {
        var result = this.cloudinary.uploader().upload(id, ObjectUtils.emptyMap());
        return new SuccessDataResult<>(result);
    }
}
