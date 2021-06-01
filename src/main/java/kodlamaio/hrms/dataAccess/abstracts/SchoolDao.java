package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolDao extends JpaRepository<School, Integer> {
    List<School> getByJobSeeker_Id(int jobSeekerId);
}
