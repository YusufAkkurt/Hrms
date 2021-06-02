package kodlamaio.hrms.core.utilities.images;

import kodlamaio.hrms.core.utilities.results.DataResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    DataResult<?> upload(MultipartFile multipartFile) throws IOException;
    DataResult<?> delete(int id) throws IOException;
}
