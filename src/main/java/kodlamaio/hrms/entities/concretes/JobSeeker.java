package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kodlamaio.hrms.entities.abstracts.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "job_seekers")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name="user_id", referencedColumnName="id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "resumes"})
public class JobSeeker extends User {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "identity_number")
    private String identityNumber;

    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @OneToMany(mappedBy = "jobSeeker")
    private List<Resume> resumes;
}
