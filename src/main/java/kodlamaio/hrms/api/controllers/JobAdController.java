package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.concretes.JobAdManager;
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
    private final JobAdManager jobAdManager;

    @Autowired
    public JobAdController(JobAdManager jobAdManager) {
        this.jobAdManager = jobAdManager;
    }

    @GetMapping("getAll")
    private DataResult<List<JobAdDto>> dtoGetJobAdsByActiveIs(@RequestParam boolean status) {
        return this.jobAdManager.dtoGetJobAdsByActiveIs(status);
    }

    @GetMapping("getByEmployerId")
    private DataResult<List<JobAdDto>> dtoGetJobAdsByActiveAndEmployerId(@RequestParam int employerId) {
        return this.jobAdManager.dtoGetJobAdsByActiveAndEmployerId(employerId);
    }

    @GetMapping("getAllReverseSortedByCreatedAt")
    private DataResult<List<JobAdDto>> dtoGetJobAdsByActiveReverseSort() {
        return this.jobAdManager.dtoGetJobAdsByActiveReverseSort();
    }

    @PostMapping
    private Result add(@RequestBody JobAd jobAd) {
        return this.jobAdManager.add(jobAd);
    }

    @PostMapping("doPassive")
    private Result add(@RequestBody int jobAdId) {
        return this.jobAdManager.doPassive(jobAdId);
    }
}
