package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.JobExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobExperienceDao extends JpaRepository<JobExperience, Integer> {
    List<JobExperience> getByJobSeeker_Id(int jobSeekerId);
}
