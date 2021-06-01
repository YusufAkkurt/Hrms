package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Resume;
import kodlamaio.hrms.entities.dtos.ResumeAddDto;

import java.util.List;

public interface ResumeService {
    DataResult<List<Resume>> getAll();

    Result add(ResumeAddDto resumeAddDto);
}
