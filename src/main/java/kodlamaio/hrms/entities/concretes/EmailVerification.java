package kodlamaio.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "email_verifications")
public class EmailVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "code")
    private String code;

    @Column(name = "created_at")
    private LocalDate createdAt = LocalDate.now();

    @Column(name = "active")
    private boolean active = true;
}
