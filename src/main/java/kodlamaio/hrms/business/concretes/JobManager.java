package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.JobService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.JobDao;
import kodlamaio.hrms.entities.concretes.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobManager implements JobService {

    private final JobDao jobDao;

    @Autowired
    public JobManager(JobDao jobDao) {
        this.jobDao = jobDao;
    }

    public DataResult<List<Job>> getAll() {
        var result = this.jobDao.findAll();
        if (result.isEmpty())
            return new ErrorDataResult<>("Henüz iş pozisyonu bulunmuyor");

        return new SuccessDataResult<>(result, "Tüm iş pozisyonları listelendi");
    }

    public DataResult<Job> getByName(String name) {
        var result = this.jobDao.findByName(name);

        if (result == null)
            return new ErrorDataResult<>("İş pozisyonu bulunmuyor");

        return new SuccessDataResult<>(result, "İş pozisyonu getirildi");
    }

    public Result add(Job job) {
        var checkName = this.getByName(job.getName()).isSuccess();
        if (checkName)
            return new ErrorResult("Bu iş pozisyonu mevcut");

        this.jobDao.save(job);

        return new SuccessResult("Eklendi");
    }
}
