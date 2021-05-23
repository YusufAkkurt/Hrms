package kodlamaio.hrms.core.adapters;

import kodlamaio.hrms.entities.concretes.JobSeeker;

public interface IdentityValidationService {
    boolean validate(JobSeeker jobSeeker);
}
