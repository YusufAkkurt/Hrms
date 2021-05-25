package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.auth.JobSeekerRegister;
import kodlamaio.hrms.entities.concretes.JobSeeker;

public interface AuthService {
    Result registerForJobSeeker(JobSeekerRegister jobSeekerRegister);
    Result loginForJobSeeker(JobSeeker jobSeeker);
    Result activateEmail(String activationCode);
}
