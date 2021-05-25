package kodlamaio.hrms.business.validators.auth;

import kodlamaio.hrms.business.validators.ValidationBase;
import kodlamaio.hrms.entities.abstracts.User;

public class UserRegisterValidation {
    public static boolean checkValidate(User user) {
        if(!ValidationBase.requiredString(user.getEmail())) return false;
        return ValidationBase.requiredString(user.getPassword());
    }
}
