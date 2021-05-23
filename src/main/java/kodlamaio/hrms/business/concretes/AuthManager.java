package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.AuthService;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.business.validators.jobSeekerValidations.JobSeekerRegisterValidation;
import kodlamaio.hrms.core.adapters.IdentityValidationService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.entities.abstracts.User;
import kodlamaio.hrms.entities.auth.JobSeekerRegister;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthManager implements AuthService {

    private final UserService userService;
    private final JobSeekerService jobSeekerService;
    private final IdentityValidationService identityValidationService;

    @Autowired
    public AuthManager(UserService userService, JobSeekerService jobSeekerService, IdentityValidationService identityValidationService) {
        this.userService = userService;
        this.jobSeekerService = jobSeekerService;
        this.identityValidationService = identityValidationService;
    }

    public Result registerForJobSeeker(JobSeekerRegister jobSeekerRegister) {
        var validate = JobSeekerRegisterValidation.checkValidate(jobSeekerRegister);
        if (validate != null) return validate;

        var jobSeeker = jobSeekerRegister.getJobSeeker();

        boolean checkRealUser = this.identityValidationService.validate(jobSeeker);

        if (!checkRealUser)
            return new ErrorResult("Girilen bilgiler geçersiz");

        var result = addUser(jobSeeker);

        if (!result.isSuccess())
            return new ErrorResult("Kullanıcı eklenemedi");

        jobSeeker.setId(result.getData().getId());
        this.jobSeekerService.add(jobSeeker);

        return new SuccessResult("Kayıt olundu");
    }

    public Result loginForJobSeeker(JobSeeker jobSeeker) {
        return new SuccessResult("Giriş yapıldı");
    }

    private DataResult<User> addUser(User user) {
        this.userService.add(user);
        return this.userService.getByEmail(user.getEmail());
    }
}
