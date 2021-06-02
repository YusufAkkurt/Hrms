package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.*;
import kodlamaio.hrms.core.utilities.images.ImageService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.ResumeDao;
import kodlamaio.hrms.entities.concretes.Resume;
import kodlamaio.hrms.entities.dtos.ResumeAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ResumeManager implements ResumeService {
    private final ResumeDao resumeDao;
    private final SchoolService schoolService;
    private final JobSeekerService jobSeekerService;
    private final JobExperienceService jobExperienceService;
    private final ForeignLanguageService foreignLanguageService;
    private final TechnologyService technologyService;
    private final ImageService imageService;

    @Autowired
    public ResumeManager(ResumeDao resumeDao, SchoolService schoolService, JobSeekerService jobSeekerService, JobExperienceService jobExperienceService, ForeignLanguageService foreignLanguageService, TechnologyService technologyService, ImageService imageService) {
        this.resumeDao = resumeDao;
        this.schoolService = schoolService;
        this.jobSeekerService = jobSeekerService;
        this.jobExperienceService = jobExperienceService;
        this.foreignLanguageService = foreignLanguageService;
        this.technologyService = technologyService;
        this.imageService = imageService;
    }

    public DataResult<List<Resume>> getAll() {
        return new SuccessDataResult<>(this.resumeDao.findAll());
    }

    public DataResult<Resume> getById(int id) {
        var result = this.resumeDao.findById(id);
        return result.isEmpty()
                ? new ErrorDataResult<>("Özgeçmiş Bulunamadı")
                : new SuccessDataResult<>(result.get(), "Bulundu");
    }

    public Result add(ResumeAddDto resumeAddDto) {
        var jobSeeker = this.jobSeekerService.getById(resumeAddDto.getJobSeekerId());
        if (!jobSeeker.isSuccess()) return jobSeeker;

        var resume = new Resume(
                jobSeeker.getData(),
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

    public Result addImage(MultipartFile multipartFile, int id) throws IOException {
        var resume = this.getById(id);
        if (!resume.isSuccess()) return resume;

        var imageAddResult = this.imageService.upload(multipartFile);
        if (!imageAddResult.isSuccess()) return new ErrorResult("Resim eklenmedi");

        var currentResume = resume.getData();
        Map<String, String> newImageResult = (Map<String, String>) imageAddResult.getData();

        currentResume.setImageUrl((newImageResult.get("url")));
        var updateResume = this.update(currentResume);
        if (!updateResume.isSuccess()) return new ErrorResult("Resim özgeçmişe eklenemedi");

        return new SuccessResult("Resim Güncellendi");
    }

    public Result update(Resume resume) {
        this.resumeDao.save(resume);
        return new SuccessResult("Güncellendi");
    }
}
