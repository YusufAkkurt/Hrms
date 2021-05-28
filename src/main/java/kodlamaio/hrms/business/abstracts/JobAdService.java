package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAd;
import kodlamaio.hrms.entities.dtos.JobAdDto;

import java.util.List;

public interface JobAdService {
    DataResult<List<JobAdDto>> dtoGetJobAdsByActiveIs(boolean status);
    DataResult<List<JobAdDto>> dtoGetJobAdsByActiveReverseSort();
    DataResult<List<JobAdDto>> dtoGetJobAdsByActiveAndEmployerId(int employerId);
    DataResult<JobAd> getById(int id);

    Result doPassive(int id);
    Result add(JobAd jobAd);
    Result update(JobAd jobAd);
}
