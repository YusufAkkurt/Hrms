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
        return new SuccessDataResult<>(this.jobSeekerDao.findAll());
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
