package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.TechnologyService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.TechnologyDao;
import kodlamaio.hrms.entities.concretes.Technology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyManager implements TechnologyService {
    private final TechnologyDao technologyDao;

    @Autowired
    public TechnologyManager(TechnologyDao technologyDao) {
        this.technologyDao = technologyDao;
    }

    public DataResult<List<Technology>> getAll() {
        return new SuccessDataResult<>(this.technologyDao.findAll());
    }

    public DataResult<Technology> getById(int id) {
        var result = this.technologyDao.findById(id);
        return result.isEmpty()
                ? new ErrorDataResult<>("Teknoloji bulunamadı")
                : new SuccessDataResult<>(result.get(), "Bulundu");
    }

    public Result add(Technology technology) {
        this.technologyDao.save(technology);
        return new SuccessResult("Eklendi");
    }
}
