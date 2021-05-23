package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.AuthService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.entities.abstracts.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthManager implements AuthService {

    private final UserService userService;

    @Autowired
    public AuthManager(UserService userService) {
        this.userService = userService;
    }

    public Result register(User user) {
        // Gerekli kontroller
        this.userService.add(user);
        return new SuccessResult("Kayıt olundu");
    }

    public Result login(User user) {
        this.userService.getByEmail(user.getEmail());
        return new SuccessResult("Giriş yapıldı");
    }
}
