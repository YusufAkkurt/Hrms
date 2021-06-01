package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.SchoolService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/schools")
public class SchoolsController {
    private final SchoolService schoolService;

    @Autowired
    public SchoolsController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping
    public DataResult<List<School>> getAll() {
        return this.schoolService.getAll();
    }

    @GetMapping("getByJobSeekerId")
    public DataResult<List<School>> getByJobSeekerId(@RequestParam int jobSeekerId) {
        return this.schoolService.getByJobSeekerId(jobSeekerId);
    }

    @GetMapping("getByJobSeekerIdDescOnCreatedAt")
    public DataResult<List<School>> getByJobSeekerIdDescOnCreatedAt(@RequestParam int jobSeekerId) {
        return this.schoolService.getByJobSeekerIdDescOnCreatedAt(jobSeekerId);
    }

    @PostMapping
    public Result add(@RequestBody School school) {
        return this.schoolService.add(school);
    }
}
