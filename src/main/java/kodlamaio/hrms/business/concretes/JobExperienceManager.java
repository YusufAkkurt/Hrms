package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.JobExperienceService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.JobExperienceDto;
import kodlamaio.hrms.entities.concretes.JobExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobExperienceManager implements JobExperienceService {
    private final JobExperienceDto jobExperienceDto;

    @Autowired
    public JobExperienceManager(JobExperienceDto jobExperienceDto) {
        this.jobExperienceDto = jobExperienceDto;
    }

    public DataResult<List<JobExperience>> getAll() {
        return new SuccessDataResult<>(this.jobExperienceDto.findAll());
    }

    public DataResult<JobExperience> getById(int id) {
        var result = this.jobExperienceDto.findById(id);
        return result.isEmpty()
                ? new ErrorDataResult<>("Deneyim bulunamadı")
                : new SuccessDataResult<>(result.get(), "Bulundu");
    }

    public Result add(JobExperience jobExperience) {
        this.jobExperienceDto.save(jobExperience);
        return new SuccessResult("Eklendi");
    }
}
