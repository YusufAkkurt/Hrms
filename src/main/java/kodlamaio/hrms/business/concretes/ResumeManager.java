package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.*;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.ResumeDao;
import kodlamaio.hrms.entities.concretes.Resume;
import kodlamaio.hrms.entities.dtos.ResumeAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeManager implements ResumeService {
    private final ResumeDao resumeDao;
    private final SchoolService schoolService;
    private final JobSeekerService jobSeekerService;
    private final JobExperienceService jobExperienceService;
    private final ForeignLanguageService foreignLanguageService;
    private final TechnologyService technologyService;

    @Autowired
    public ResumeManager(ResumeDao resumeDao, SchoolService schoolService, JobSeekerService jobSeekerService, JobExperienceService jobExperienceService, ForeignLanguageService foreignLanguageService, TechnologyService technologyService) {
        this.resumeDao = resumeDao;
        this.schoolService = schoolService;
        this.jobSeekerService = jobSeekerService;
        this.jobExperienceService = jobExperienceService;
        this.foreignLanguageService = foreignLanguageService;
        this.technologyService = technologyService;
    }

    public DataResult<List<Resume>> getAll() {
        return new SuccessDataResult<>(this.resumeDao.findAll());
    }

    public Result add(ResumeAddDto resumeAddDto) {
        var jobSeeker = this.jobSeekerService.getById(resumeAddDto.getJobSeekerId());

        var resume = new Resume(
                jobSeeker.getData(),
                resumeAddDto.getImageUrl(),
                resumeAddDto.getGithub(),
                resumeAddDto.getLinkedin(),
                resumeAddDto.getCoverLetter());

        for (int schoolId : resumeAddDto.getSchoolIds()) {
            var school = this.schoolService.getById(schoolId).getData();
            resume.getSchools().add(school);
        }

        for (int experienceId : resumeAddDto.getJobExperienceIds()) {
            var jobExperience = this.jobExperienceService.getById(experienceId).getData();
            resume.getJobExperiences().add(jobExperience);
        }

        for (int foreignLanguageId : resumeAddDto.getForeignLanguageIds()) {
            var foreignLanguage = this.foreignLanguageService.getById(foreignLanguageId).getData();
            resume.getForeignLanguages().add(foreignLanguage);
        }

        for (int technologyId : resumeAddDto.getTechnologyIds()) {
            var technology = this.technologyService.getById(technologyId).getData();
            resume.getTechnologies().add(technology);
        }

        this.resumeDao.save(resume);
        return new SuccessResult("Eklendi");
    }
}
