package kodlamaio.hrms.business.validators.auth;

import kodlamaio.hrms.business.validators.ValidationBase;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.entities.auth.JobSeekerRegister;

public class JobSeekerRegisterValidation extends ValidationBase {

    public static ErrorResult checkValidate(JobSeekerRegister jobSeekerRegister) {
        boolean result = true;

        var jobSeeker = jobSeekerRegister.getJobSeeker();

        if (!ValidationBase.requiredString(jobSeeker.getFirstName()))
            result = false;

        if (!ValidationBase.requiredString(jobSeeker.getLastName()))
            result = false;

        if (!ValidationBase.requiredString(jobSeeker.getIdentityNumber()))
            result = false;

        if (!ValidationBase.requiredInteger(jobSeeker.getYearOfBirth()))
            result = false;

        if (!UserRegisterValidation.checkValidate(jobSeeker))
            result = false;

        boolean passwordResult = ValidationBase.confirmPassword(jobSeeker.getPassword(), jobSeekerRegister.getPassword());

        if (!passwordResult)
            return new ErrorResult("Şifreler uyuşmuyor");

        if (!result)
            return new ErrorResult("Boş alan bırakmayın");

        return null;
    }
}
