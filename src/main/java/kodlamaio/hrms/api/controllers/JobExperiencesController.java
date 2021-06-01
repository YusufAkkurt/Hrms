package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.JobExperienceService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobExperience;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jobExperiences")
public class JobExperiencesController {
    private final JobExperienceService jobExperienceService;

    public JobExperiencesController(JobExperienceService jobExperienceService) {
        this.jobExperienceService = jobExperienceService;
    }

    @GetMapping
    public DataResult<List<JobExperience>> getAll() {
        return this.jobExperienceService.getAll();
    }

    @GetMapping("getByJobSeekerId")
    public DataResult<List<JobExperience>> getByJobSeekerId(@RequestParam int jobSeekerId) {
        return this.jobExperienceService.getByJobSeeker(jobSeekerId);
    }

    @GetMapping("getByJobSeekerIdDescOnCreatedAt")
    public DataResult<List<JobExperience>> getByJobSeekerIdDescOnCreatedAt(@RequestParam int jobSeekerId) {
        return this.jobExperienceService.getByJobSeekerIdDescOnCreatedAt(jobSeekerId);
    }

    @PostMapping
    public Result add(@RequestBody JobExperience jobExperience) {
        return this.jobExperienceService.add(jobExperience);
    }
}
