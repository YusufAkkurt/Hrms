package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerManager implements EmployerService {
    private final EmployerDao employerDao;

    @Autowired
    public EmployerManager(EmployerDao employerDao) {
        this.employerDao = employerDao;
    }

    public DataResult<List<Employer>> getByPersonnelActivateFalse() {
        var result = this.employerDao.findByVerifiedByPersonnelFalse();
        return result.size() > 0
                ? new SuccessDataResult<>(result, "Onay bekleyen iş verenler")
                : new ErrorDataResult<>("Onay bekleyen iş veren bulunamadı");
    }

    public Result add(Employer employer) {
        this.employerDao.save(employer);
        return new SuccessResult("Eklendi");
    }

    public Result update(Employer employer) {
        this.employerDao.save(employer);
        return new SuccessResult("Güncellendi");
    }
}
