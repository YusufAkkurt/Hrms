package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.ResumeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.ResumeDao;
import kodlamaio.hrms.entities.concretes.Resume;
import kodlamaio.hrms.entities.concretes.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeManager implements ResumeService {
    private final ResumeDao resumeDao;
    private final SchoolManager schoolManager;

    @Autowired
    public ResumeManager(ResumeDao resumeDao, SchoolManager schoolManager) {
        this.resumeDao = resumeDao;
        this.schoolManager = schoolManager;
    }

    public DataResult<List<Resume>> getAll() {
        return new SuccessDataResult<>(this.resumeDao.findAll());
    }

    public Result add(Resume resume) {
        var school = this.schoolManager.getById(1).getData();
        resume.getSchools().add(school);

        this.resumeDao.save(resume);
        return new SuccessResult("Eklendi");
    }
}
