package kodlamaio.hrms.core.adapters;

import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.services.mernis.HEEKPSPublicSoap;
import org.springframework.stereotype.Service;

@Service
public class MernisAdapter implements IdentityValidationService {
    public boolean validate(JobSeeker jobSeeker) {
        boolean result;

        HEEKPSPublicSoap publicSoap = new HEEKPSPublicSoap();

        try {
            result = publicSoap.TCKimlikNoDogrula(
                    Long.parseLong(jobSeeker.getIdentityNumber()),
                    jobSeeker.getFirstName().toUpperCase(),
                    jobSeeker.getLastName().toUpperCase(),
                    jobSeeker.getYearOfBirth()
            );

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
