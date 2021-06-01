package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobExperience;

import java.util.List;

public interface JobExperienceService {
    DataResult<List<JobExperience>> getAll();
    DataResult<JobExperience> getById(int id);
    DataResult<List<JobExperience>> getByJobSeeker(int jobSeekerId);
    DataResult<List<JobExperience>> getByJobSeekerIdDescOnCreatedAt(int jobSeekerId);

    Result add(JobExperience jobExperience);
}
