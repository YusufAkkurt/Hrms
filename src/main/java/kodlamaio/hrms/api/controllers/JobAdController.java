package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.JobAdService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAd;
import kodlamaio.hrms.entities.dtos.JobAdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jobAds")
public class JobAdController {
    private final JobAdService jobAdService;

    @Autowired
    public JobAdController(JobAdService jobAdService) {
        this.jobAdService = jobAdService;
    }

    @GetMapping("getAll")
    private DataResult<List<JobAdDto>> dtoGetJobAdsByActiveIs(@RequestParam boolean status) {
        return this.jobAdService.dtoGetJobAdsByActiveIs(status);
    }

    @GetMapping("getByEmployerId")
    private DataResult<List<JobAdDto>> dtoGetJobAdsByActiveAndEmployerId(@RequestParam int employerId) {
        return this.jobAdService.dtoGetJobAdsByActiveAndEmployerId(employerId);
    }

    @GetMapping("getAllReverseSortedByCreatedAt")
    private DataResult<List<JobAdDto>> dtoGetJobAdsByActiveReverseSort() {
        return this.jobAdService.dtoGetJobAdsByActiveReverseSort();
    }

    @PostMapping
    private Result add(@RequestBody JobAd jobAd) {
        return this.jobAdService.add(jobAd);
    }

    @PostMapping("doPassive")
    private Result add(@RequestBody int jobAdId) {
        return this.jobAdService.doPassive(jobAdId);
    }
}
