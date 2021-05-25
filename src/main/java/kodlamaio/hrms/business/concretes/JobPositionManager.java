package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import kodlamaio.hrms.entities.concretes.JobPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPositionManager implements JobPositionService {

    private final JobPositionDao jobPositionDao;

    @Autowired
    public JobPositionManager(JobPositionDao jobPositionDao) {
        this.jobPositionDao = jobPositionDao;
    }

    public DataResult<List<JobPosition>> getAll() {
        var result = this.jobPositionDao.findAll();
        if (result.isEmpty())
            return new ErrorDataResult<>("Henüz iş pozisyonu bulunmuyor");

        return new SuccessDataResult<>(result, "Tüm iş pozisyonları listelendi");
    }

    public DataResult<JobPosition> getByName(String name) {
        var result = this.jobPositionDao.findByName(name);

        if (result == null)
            return new ErrorDataResult<>("İş pozisyonu bulunmuyor");

        return new SuccessDataResult<>(result, "İş pozisyonu getirildi");
    }

    public Result add(JobPosition jobPosition) {
        var checkName = this.getByName(jobPosition.getName()).isSuccess();
        if (checkName)
            return new ErrorResult("Bu iş pozisyonu mevcut");

        this.jobPositionDao.save(jobPosition);

        return new SuccessResult("Eklendi");
    }
}
