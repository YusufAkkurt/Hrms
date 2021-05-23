package kodlamaio.hrms.entities.concretes;

import kodlamaio.hrms.entities.abstracts.User;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employers")
@EqualsAndHashCode(callSuper = true)
public class Employer extends User {

    @Id
    @Column(name = "user_id")
    private int userId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "web_site")
    private String webSite;

    @Column(name = "verified_by_personnel")
    private boolean verifiedByPersonnel;

    @Column(name = "phone_number")
    private String phoneNumber;
}
