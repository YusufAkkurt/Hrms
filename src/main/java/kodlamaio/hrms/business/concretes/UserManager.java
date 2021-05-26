package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.EmailVerificationService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.abstracts.User;
import kodlamaio.hrms.entities.concretes.EmailVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class UserManager implements UserService {

    private final UserDao userDao;
    private final EmailVerificationService emailVerificationService;

    @Autowired
    public UserManager(UserDao userDao, EmailVerificationService emailVerificationService) {
        this.userDao = userDao;
        this.emailVerificationService = emailVerificationService;
    }

    public DataResult<List<User>> getAll() {
        return new SuccessDataResult<>(this.userDao.findAll());
    }

    public DataResult<User> getById(int id) {
        var result = this.userDao.findById(id);
        return result.isEmpty()
                ? new ErrorDataResult<>(null, "Kullanıcı bulunamadı")
                : new SuccessDataResult<>(result.get(), "Kullanıcı bulundu");
    }

    public DataResult<User> getByEmail(String email) {
        var result = this.userDao.findByEmail(email);
        return result != null ? new SuccessDataResult<>(result, "Kullanıcı mevcut") : new ErrorDataResult<>("Kullanıcı bulunamadı");
    }

    public Result add(User user) {
        this.userDao.save(user);
        return createEmailVerification(user.getEmail());
    }

    private Result createEmailVerification(String email) {
        var user = getByEmail(email);
        if (!user.isSuccess())
            return user;

        var emailVerification = new EmailVerification();
        emailVerification.setUserId(user.getData().getId());
        emailVerification.setCode(createCode());

        return this.emailVerificationService.add(emailVerification);
    }

    private String createCode() {
        UUID uuid = UUID.randomUUID();

        var formatter = DateTimeFormatter.ofPattern("yyMMddHHmmssSSS");
        var localDateTime = LocalDateTime.now();

        return formatter.format(localDateTime) + uuid;
    }

    public Result update(User user) {
        this.userDao.save(user);
        return new SuccessResult("Güncellendi");
    }

    public Result delete(User user) {
        this.userDao.delete(user);
        return new SuccessResult("Silindi");
    }

    public Result activateUser(int id, String code) {
        var user = this.userDao.findById(id);

        if (user.isEmpty()) return new ErrorResult("Active edilecek Kullanıcı bulunamadı");

        user.get().setEmailVerified(true);
        return new SuccessDataResult<>(user);
    }
}
