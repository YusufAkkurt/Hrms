package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerDao;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeekerManager implements JobSeekerService {
    private final JobSeekerDao jobSeekerDao;

    @Autowired
    public JobSeekerManager(JobSeekerDao jobSeekerDao) {
        this.jobSeekerDao = jobSeekerDao;
    }

    public DataResult<List<JobSeeker>> getAll() {
        var result = this.jobSeekerDao.findAll();

        if (result.isEmpty())
            return new ErrorDataResult<>("Liste boş");

        return new SuccessDataResult<>(result, "Listelendi");
    }

    public DataResult<JobSeeker> getById(int id) {
        var result = this.jobSeekerDao.findById(id);
        return result.isEmpty()
                ? new ErrorDataResult<>("İş arayan bulunamadı")
                : new SuccessDataResult<>(result.get(), "Bulundu");
    }

    public DataResult<JobSeeker> getByIdentityNumber(String identityNumber) {
        var result = this.jobSeekerDao.findByIdentityNumber(identityNumber);

        return result != null ? new SuccessDataResult<>(result, "Kullanıcı mevcut") : new ErrorDataResult<>("Kullanıcı bulunamadı");
    }

    public Result add(JobSeeker jobSeeker) {
        this.jobSeekerDao.save(jobSeeker);
        return new SuccessResult("Eklendi");
    }

    public Result update(JobSeeker jobSeeker) {
        return null;
    }

    public Result delete(JobSeeker jobSeeker) {
        return null;
    }
}
