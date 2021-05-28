package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.JobAdService;
import kodlamaio.hrms.business.validators.JobAdValidation;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.JobAdDao;
import kodlamaio.hrms.entities.concretes.JobAd;
import kodlamaio.hrms.entities.dtos.JobAdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobAdManager implements JobAdService {
    private final JobAdDao jobAdDao;

    @Autowired
    public JobAdManager(JobAdDao jobAdDao) {
        this.jobAdDao = jobAdDao;
    }

    public DataResult<List<JobAdDto>> dtoGetJobAdsByActiveIs(boolean status) {
        var result = this.jobAdDao.dtoGetJobAdsByActiveIs(status);
        return result.isEmpty()
                ? new ErrorDataResult<>("Aktif iş ilanı bulunamadı")
                : new SuccessDataResult<>(result, "listelendi");
    }

    public DataResult<List<JobAdDto>> dtoGetJobAdsByActiveReverseSort() {
        var result = this.jobAdDao.dtoGetJobAdsByActiveIs(true);

        var sortedResult = result.stream()
                .sorted(Comparator.comparing(JobAdDto::getCreatedAt).reversed())
                .collect(Collectors.toList());

        return result.isEmpty()
                ? new ErrorDataResult<>("Aktif iş ilanı bulunamadı")
                : new SuccessDataResult<>(sortedResult, "Oluşturulma tarihine göre sıralandı");
    }

    public DataResult<List<JobAdDto>> dtoGetJobAdsByActiveAndEmployerId(int employerId) {
        var result = this.jobAdDao.dtoGetJobAdsByActiveAndEmployerId(employerId);
        return result.isEmpty()
                ? new ErrorDataResult<>("Firmaya ait iş ilanı bulunamadı")
                : new SuccessDataResult<>(result, "Firmaya ait iş ilanları listelendi");
    }

    public DataResult<JobAd> getById(int id) {
        var result = this.jobAdDao.findById(id);
        return result.isEmpty()
                ? new ErrorDataResult<>("Aktif iş ilanı bulunamadı")
                : new SuccessDataResult<>(result.get(), "getirildi");
    }

    public Result doPassive(int id) {
        var jobAd = this.getById(id);
        if (!jobAd.isSuccess())
            return jobAd;

        jobAd.getData().setActive(false);

        var result = this.update(jobAd.getData());
        if (result == null)
            return new ErrorResult("İş ilan pasifleştirilemedi");

        return new SuccessResult("iş ilanı pasifleştirildi");
    }

    private Result save(JobAd jobAd) {
        var validate = JobAdValidation.checkValidate(jobAd);
        if (validate != null)
            return validate;

        this.jobAdDao.save(jobAd);
        return new SuccessResult("Eklendi");
    }

    public Result add(JobAd jobAd) {
        return this.save(jobAd);
    }

    public Result update(JobAd jobAd) {
        return this.save(jobAd);
    }
}
