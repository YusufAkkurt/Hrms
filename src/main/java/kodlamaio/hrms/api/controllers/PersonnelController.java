package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.PersonnelService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/personnels")
public class PersonnelController {
    private final EmployerService employerService;
    private final PersonnelService personnelService;

    @Autowired
    public PersonnelController(EmployerService employerService, PersonnelService personnelService) {
        this.employerService = employerService;
        this.personnelService = personnelService;
    }

    @GetMapping("api/listOfActiveIsFalse")
    public DataResult<List<Employer>> listOfActiveIsFalse() {
        return this.employerService.getByPersonnelActivateFalse();
    }

    @PutMapping("api/activateEmployer")
    @ResponseBody
    public Result activatePersonnel(@RequestParam int userId) {
        return personnelService.validateEmployer(userId);
    }
}
