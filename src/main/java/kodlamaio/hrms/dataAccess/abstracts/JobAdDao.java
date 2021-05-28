package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.JobAd;
import kodlamaio.hrms.entities.dtos.JobAdDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobAdDao extends JpaRepository<JobAd, Integer> {

    @Query("select new kodlamaio.hrms.entities.dtos.JobAdDto(id, employer.companyName, job.name, openPositionCount, createdAt, applicationDeadline) from JobAd where employer.id = :employerId")
    List<JobAdDto> dtoGetJobAdsByActiveAndEmployerId(@Param("employerId") int employerId);

    @Query("select new kodlamaio.hrms.entities.dtos.JobAdDto(id, employer.companyName, job.name, openPositionCount, createdAt, applicationDeadline) from JobAd where active = :status")
    List<JobAdDto> dtoGetJobAdsByActiveIs(@Param("status") boolean status);
}
