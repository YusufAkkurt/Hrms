package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.SchoolService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.SchoolDao;
import kodlamaio.hrms.entities.concretes.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolManager implements SchoolService {
    private final SchoolDao schoolDao;

    @Autowired
    public SchoolManager(SchoolDao schoolDao) {
        this.schoolDao = schoolDao;
    }

    public DataResult<List<School>> getAll() {
        return new SuccessDataResult<>(this.schoolDao.findAll());
    }

    public DataResult<List<School>> getByJobSeekerId(int jobSeekerId) {
        var result = this.schoolDao.getByJobSeeker_Id(jobSeekerId);
        return result.isEmpty()
                ? new ErrorDataResult<>("Okul bulunamadı")
                : new SuccessDataResult<>(result, "Bulundu");
    }

    public DataResult<List<School>> getByJobSeekerIdDescOnCreatedAt(int jobSeekerId) {
        var result = this.getByJobSeekerId(jobSeekerId);
        if (!result.isSuccess()) return result;

        var sortedResult = result.getData().stream()
                .sorted(Comparator.comparing(School::getFinishDate, Comparator.nullsLast(Comparator.naturalOrder())).reversed())
                .collect(Collectors.toList());

        return new SuccessDataResult<>(sortedResult, "");
    }

    public DataResult<School> getById(int id) {
        var result = this.schoolDao.findById(id);
        return result.isEmpty()
                ? new ErrorDataResult<>("Okul bulunamadı")
                : new SuccessDataResult<>(result.get(), "Bulundu");
    }

    public Result add(School school) {
        this.schoolDao.save(school);
        return new SuccessResult("Eklendi");
    }
}
