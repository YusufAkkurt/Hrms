package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.WorkType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkTypeDao extends JpaRepository<WorkType, Integer> {

}
