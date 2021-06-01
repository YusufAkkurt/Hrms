package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.ForeignLanguageService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.ForeignLanguageDao;
import kodlamaio.hrms.entities.concretes.ForeignLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForeignLanguageManager implements ForeignLanguageService {
    private final ForeignLanguageDao foreignLanguageDao;

    @Autowired
    public ForeignLanguageManager(ForeignLanguageDao foreignLanguageDao) {
        this.foreignLanguageDao = foreignLanguageDao;
    }

    public DataResult<List<ForeignLanguage>> getAll() {
        return new SuccessDataResult<>(this.foreignLanguageDao.findAll());
    }

    public DataResult<ForeignLanguage> getById(int id) {
        var result = this.foreignLanguageDao.findById(id);
        return result.isEmpty()
                ? new ErrorDataResult<>("Yabancı dil bulunamadı")
                : new SuccessDataResult<>(result.get(), "Bulundu");
    }

    public Result add(ForeignLanguage foreignLanguage) {
        this.foreignLanguageDao.save(foreignLanguage);
        return new SuccessResult("Eklendi");
    }
}
