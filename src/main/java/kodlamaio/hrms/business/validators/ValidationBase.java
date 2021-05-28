package kodlamaio.hrms.business.validators;

import java.time.LocalDate;

public class ValidationBase {

    public static boolean requiredString(String value) {
        return value != null && value.length() != 0;
    }

    public static boolean requiredLocalDate(LocalDate value) {
        return value != null;
    }

    public static boolean requiredInteger(int value) {
        return value != 0 && value >= 0;
    }

    public static boolean confirmPassword(String password, String passwordConfirm) {
        return password.equals(passwordConfirm);
    }
}
