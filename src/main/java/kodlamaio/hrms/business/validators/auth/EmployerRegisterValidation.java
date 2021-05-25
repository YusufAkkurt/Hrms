package kodlamaio.hrms.business.validators.auth;

import kodlamaio.hrms.business.validators.ValidationBase;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.entities.auth.EmployerRegister;

import java.util.Locale;

public class EmployerRegisterValidation {
    public static ErrorResult checkValidate(EmployerRegister employerRegister) {
        boolean result = true;

        var employer = employerRegister.getEmployer();

        if (!ValidationBase.requiredString(employer.getCompanyName()))
            result = false;

        if (!ValidationBase.requiredString(employer.getWebSite()))
            result = false;

        if (!ValidationBase.requiredString(employer.getPhoneNumber()))
            result = false;

        if (!UserRegisterValidation.checkValidate(employer))
            result = false;

        boolean passwordResult = ValidationBase.confirmPassword(employer.getPassword(), employerRegister.getPassword());

        if (!passwordResult)
            return new ErrorResult("Şifreler uyuşmuyor");

        boolean emailResult = checkEmailDomain(employer.getEmail(), employer.getWebSite());

        if (!emailResult)
            return new ErrorResult("Geçersiz e-posta adresi");

        if (!result)
            return new ErrorResult("Boş alan bırakmayın");

        return null;
    }

    private static boolean checkEmailDomain(String email, String webSite) {
        var emailParameters = email.split("@");

        var domain = emailParameters[emailParameters.length - 1];
        return domain.equals(webSite.toLowerCase(Locale.ROOT));
    }
}
