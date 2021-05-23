package kodlamaio.hrms.entities.concretes;

import kodlamaio.hrms.entities.abstracts.User;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "personnel")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name="user_id", referencedColumnName="id")
public class Personnel extends User {
//    @Id
//    @Column(name = "user_id")
//    private int userId;

    @Column(name = "department_id")
    private int departmentId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}
