package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.JobAd;
import kodlamaio.hrms.entities.dtos.JobAdDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobAdDao extends JpaRepository<JobAd, Integer> {
    @Query("select new kodlamaio.hrms.entities.dtos.JobAdDto(JobAd.id, jobAd.employer.companyName, jobAd.job.name, jobAd.openPositionCount, jobAd.createdAt, jobAd.applicationDeadline) from JobAd jobAd where jobAd.active = true")
    List<JobAdDto> retrieveJobAdsAsActiveDto();
}
