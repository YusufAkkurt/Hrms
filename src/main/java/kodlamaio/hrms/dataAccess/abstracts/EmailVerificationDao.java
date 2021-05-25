package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailVerificationDao extends JpaRepository<EmailVerification, Integer> {
    EmailVerification findByCode(String code);
}
