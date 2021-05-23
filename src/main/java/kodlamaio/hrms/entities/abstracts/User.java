package kodlamaio.hrms.entities.abstracts;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "email_verified")
    private boolean emailVerified = false;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private LocalDate createdAt = LocalDate.now();

    @Column(name = "active")
    private boolean active = true;
}
