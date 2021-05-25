package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.EmailVerification;

public interface EmailVerificationService {
    DataResult<EmailVerification> getByCode(String code);

    Result add(EmailVerification emailVerification);
    Result update(EmailVerification emailVerification);
    Result delete(EmailVerification emailVerification);
}
