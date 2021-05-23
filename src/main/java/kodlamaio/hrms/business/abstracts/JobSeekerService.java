package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobSeeker;

import java.util.List;

public interface JobSeekerService {
    DataResult<List<JobSeeker>> getAll();

    Result add(JobSeeker jobSeeker);
    Result update(JobSeeker jobSeeker);
    Result delete(JobSeeker jobSeeker);
}
