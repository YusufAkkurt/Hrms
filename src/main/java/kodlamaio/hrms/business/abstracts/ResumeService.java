package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Resume;
import kodlamaio.hrms.entities.dtos.ResumeAddDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ResumeService {
    DataResult<List<Resume>> getAll();
    DataResult<Resume> getById(int id);
    DataResult<List<Resume>> getByJobSeekerId(int jobSeekerId);

    Result add(ResumeAddDto resumeAddDto);
    Result addImage(MultipartFile multipartFile, int id) throws IOException;
    Result update(Resume resume);
}
