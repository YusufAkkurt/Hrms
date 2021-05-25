package kodlamaio.hrms.entities.auth;

import kodlamaio.hrms.entities.concretes.Employer;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployerRegister {
    private Employer employer;
    private String password;
}
