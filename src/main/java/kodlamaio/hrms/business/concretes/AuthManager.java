package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.*;
import kodlamaio.hrms.business.validators.jobSeekerValidations.JobSeekerRegisterValidation;
import kodlamaio.hrms.core.adapters.IdentityValidationService;
import kodlamaio.hrms.core.utilities.results.*;
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
    private final EmailVerificationService emailVerificationService;

    @Autowired
    public AuthManager(UserService userService, JobSeekerService jobSeekerService,
                       IdentityValidationService identityValidationService,
                       EmailVerificationService emailVerificationService) {
        this.userService = userService;
        this.jobSeekerService = jobSeekerService;
        this.identityValidationService = identityValidationService;
        this.emailVerificationService = emailVerificationService;
    }

    public Result registerForJobSeeker(JobSeekerRegister jobSeekerRegister) {
        var validate = JobSeekerRegisterValidation.checkValidate(jobSeekerRegister);
        if (validate != null) return validate;

        var jobSeeker = jobSeekerRegister.getJobSeeker();

        boolean checkRealUser = this.identityValidationService.validate(jobSeeker);

        if (!checkRealUser)
            return new ErrorResult("Girilen bilgiler geçersiz");

        var checkIdentity = checkUserByIdentityNumber(jobSeeker.getIdentityNumber());
        if (checkIdentity != null) return checkIdentity;

        var result = addUser(jobSeeker);
        if (!result.isSuccess()) return result;

        jobSeeker.setId(result.getData().getId());
        this.jobSeekerService.add(jobSeeker);

        return new SuccessResult("Kayıt olundu");
    }

    public Result loginForJobSeeker(JobSeeker jobSeeker) {
        return new SuccessResult("Giriş yapıldı");
    }

    public Result activateEmail(String activationCode) {
        var existingEmailCode = this.emailVerificationService.getByCode(activationCode);
        if (!existingEmailCode.isSuccess()) return existingEmailCode;

        if (!existingEmailCode.getData().isActive())
            return new ErrorResult("Bu kod artık geçersiz lütfen yeni bir kod isteyin");

        var user = this.userService.getById(existingEmailCode.getData().getUserId());
        if (!user.isSuccess()) return user;

        user.getData().setEmailVerified(true);
        var result = this.userService.update(user.getData());

        return result.isSuccess()
                ? new SuccessResult("Email aktivasyonu başarılı")
                : new ErrorResult("Email aktivasyonu başarısız");
    }

    private DataResult<User> addUser(User user) {
        boolean validate = this.userService.getByEmail(user.getEmail()).isSuccess();
        if (validate) return new ErrorDataResult("Eposta adresi kullanımda");

        this.userService.add(user);
        return this.userService.getByEmail(user.getEmail());
    }

    private Result checkUserByIdentityNumber(String identityNumber) {
        var jobSeeker = this.jobSeekerService.getByIdentityNumber(identityNumber);
        System.out.println(jobSeeker.getData());

        if (jobSeeker.isSuccess())
            return new ErrorResult("Kimlik numarası kullanımda");

        return null;
    }
}
