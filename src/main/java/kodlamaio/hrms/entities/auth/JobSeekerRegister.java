package kodlamaio.hrms.entities.auth;

import kodlamaio.hrms.entities.concretes.JobSeeker;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobSeekerRegister {
    private JobSeeker jobSeeker;
    private String password;
}
