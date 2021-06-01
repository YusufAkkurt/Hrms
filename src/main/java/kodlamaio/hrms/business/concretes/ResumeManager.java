package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.abstracts.ResumeService;
import kodlamaio.hrms.business.abstracts.SchoolService;
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

    @Autowired
    public ResumeManager(ResumeDao resumeDao, SchoolService schoolService, JobSeekerService jobSeekerService) {
        this.resumeDao = resumeDao;
        this.schoolService = schoolService;
        this.jobSeekerService = jobSeekerService;
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

        this.resumeDao.save(resume);
        return new SuccessResult("Eklendi");
    }
}
