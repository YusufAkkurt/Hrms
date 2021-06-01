package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.JobExperienceService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.JobExperienceDao;
import kodlamaio.hrms.entities.concretes.JobExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobExperienceManager implements JobExperienceService {
    private final JobExperienceDao jobExperienceDao;

    @Autowired
    public JobExperienceManager(JobExperienceDao jobExperienceDao) {
        this.jobExperienceDao = jobExperienceDao;
    }

    public DataResult<List<JobExperience>> getAll() {
        return new SuccessDataResult<>(this.jobExperienceDao.findAll());
    }

    public DataResult<JobExperience> getById(int id) {
        var result = this.jobExperienceDao.findById(id);
        return result.isEmpty()
                ? new ErrorDataResult<>("Deneyim bulunamadı")
                : new SuccessDataResult<>(result.get(), "Bulundu");
    }

    public DataResult<List<JobExperience>> getByJobSeeker(int jobSeekerId) {
        var result = this.jobExperienceDao.getByJobSeeker_Id(jobSeekerId);
        return result.isEmpty()
                ? new ErrorDataResult<>("Deneyim bulunamadı")
                : new SuccessDataResult<>(result, "Bulundu");
    }

    public DataResult<List<JobExperience>> getByJobSeekerIdDescOnCreatedAt(int jobSeekerId) {
        var result = this.getByJobSeeker(jobSeekerId);
        if (!result.isSuccess()) return result;

        var sortedResult = result.getData().stream()
                .sorted(Comparator.comparing(JobExperience::getFinishDate, Comparator.nullsLast(Comparator.naturalOrder())).reversed())
                .collect(Collectors.toList());

        return new SuccessDataResult<>(sortedResult, "");
    }

    public Result add(JobExperience jobExperience) {
        this.jobExperienceDao.save(jobExperience);
        return new SuccessResult("Eklendi");
    }
}
