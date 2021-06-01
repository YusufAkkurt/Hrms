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
@Table(name = "foreign_languages")
public class ForeignLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "language")
    private String language;

    @Column(name = "level")
    private int level;

    @Column(name = "created_at")
    private LocalDate createdAt = LocalDate.now();

    @Column(name = "active")
    private boolean active = true;
}
