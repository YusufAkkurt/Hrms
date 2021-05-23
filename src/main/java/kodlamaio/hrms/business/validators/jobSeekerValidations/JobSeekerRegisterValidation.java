package kodlamaio.hrms.business.validators.jobSeekerValidations;

import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.entities.auth.JobSeekerRegister;
import kodlamaio.hrms.entities.concretes.JobSeeker;

public class JobSeekerRegisterValidation {
    private static boolean resultOfRule = true;

    public static ErrorResult checkValidate(JobSeekerRegister jobSeekerRegister) {
        resultOfRule = true;

        var jobSeeker = jobSeekerRegister.getJobSeeker();

        requiredString(jobSeeker.getFirstName());
        requiredString(jobSeeker.getLastName());
        requiredString(jobSeeker.getIdentityNumber());
        requiredInteger(jobSeeker.getYearOfBirth());
        requiredString(jobSeeker.getEmail());
        requiredString(jobSeeker.getPassword());
        requiredString(jobSeekerRegister.getPassword());

        boolean passwordResult =  confirmPassword(jobSeeker.getPassword(), jobSeekerRegister.getPassword());

        if (!passwordResult)
            return new ErrorResult("Şifreler uyuşmuyor");

        if (!resultOfRule)
            return new ErrorResult("Boş alan bırakmayın");

        return null;
    }

    private static void requiredString(String value) {
        if (value == null || value.length() == 0) {
            resultOfRule = false;
        }
    }

    private static void requiredInteger(int value) {
        if (value == 0 || value < 0) {
            resultOfRule = false;
        }
    }

    private static boolean confirmPassword(String password, String passwordConfirm) {
        return password.equals(passwordConfirm);
    }
}
