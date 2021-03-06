package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.ResumeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Resume;
import kodlamaio.hrms.entities.dtos.ResumeAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/resumes")
public class ResumeController {
    private final ResumeService resumeService;

    @Autowired
    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping
    public DataResult<List<Resume>> getAll() {
        return this.resumeService.getAll();
    }

    @GetMapping("getByJobSeekerId")
    public DataResult<List<Resume>> getByJobSeekerId(@RequestParam int jobSeekerId) {
        return this.resumeService.getByJobSeekerId(jobSeekerId);
    }

    @PostMapping
    public Result add(@RequestBody ResumeAddDto resumeAddDto) {
        return this.resumeService.add(resumeAddDto);
    }

    @PostMapping("addImage")
    public Result addImage(@RequestBody MultipartFile multipartFile, @RequestParam int id) throws IOException {
        return this.resumeService.addImage(multipartFile, id);
    }
}
