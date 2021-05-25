package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.abstracts.User;

import java.util.List;

public interface UserService {
    DataResult<List<User>> getAll();
    DataResult<User> getById(int id);
    DataResult<User> getByEmail(String email);

    Result add(User user);
    Result update(User user);
    Result delete(User user);
    Result activateUser(int id, String code);
}