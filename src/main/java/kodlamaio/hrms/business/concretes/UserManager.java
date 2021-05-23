package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.abstracts.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    public DataResult<List<User>> getAll() {
        return new SuccessDataResult<>(this.userDao.findAll());
    }

    public DataResult<User> getByEmail(String email) {
        return new SuccessDataResult<>(this.userDao.findByEmail(email));
    }

    public Result add(User user) {
        this.userDao.save(user);
        return new SuccessResult("Eklendi");
    }

    public Result update(User user) {
        this.userDao.save(user);
        return new SuccessResult("Güncellendi");
    }

    public Result delete(User user) {
        this.userDao.delete(user);
        return new SuccessResult("Silindi");
    }
}
