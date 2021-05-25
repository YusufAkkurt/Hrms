package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.PersonnelService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonnelManager implements PersonnelService {
    private final EmployerService employerService;

    @Autowired
    public PersonnelManager(EmployerService employerService) {
        this.employerService = employerService;
    }

    public Result validateEmployer(int userId) {
        var employer = employerService.getById(userId);
        if (!employer.isSuccess())
            return employer;

        if (employer.getData().isVerifiedByPersonnel())
            return new ErrorResult("Kullanıcı zaten onaylanmış");

        employer.getData().setVerifiedByPersonnel(true);

        var result = this.employerService.update(employer.getData());
        if (!result.isSuccess())
            return result;

        return new SuccessResult("İş veren onaylandı");
    }
}
