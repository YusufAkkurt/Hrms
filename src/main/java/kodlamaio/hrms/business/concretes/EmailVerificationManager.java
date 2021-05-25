package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.EmailVerificationService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.EmailVerificationDao;
import kodlamaio.hrms.entities.concretes.EmailVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailVerificationManager implements EmailVerificationService {
    private final EmailVerificationDao emailVerificationDao;

    @Autowired
    public EmailVerificationManager(EmailVerificationDao emailVerificationDao) {
        this.emailVerificationDao = emailVerificationDao;
    }

    public DataResult<EmailVerification> getByCode(String code) {
        var result = emailVerificationDao.findByCode(code);

        return result == null
                ? new ErrorDataResult<>("Aktivasyon kodu bulunamadı")
                : new SuccessDataResult<>(result, "Activasyon kodu bulundu");
    }

    public Result add(EmailVerification emailVerification) {
        var checkCode = checkExistingCode(emailVerification.getCode());

        if (checkCode)
            return new ErrorResult("Bu kod zaten mevcut");

        this.emailVerificationDao.save(emailVerification);
        return new SuccessDataResult<>(emailVerification, "Oluşturuldu");
    }

    public Result update(EmailVerification emailVerification) {
        this.emailVerificationDao.save(emailVerification);
        return new SuccessDataResult<>(emailVerification, "Güncellendi");
    }

    public Result delete(EmailVerification emailVerification) {
        this.emailVerificationDao.save(emailVerification);
        return new SuccessResult("Silindi");
    }

    private boolean checkExistingCode(String code) {
        return getByCode(code).isSuccess();
    }
}
