package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "technologies")
public class Technology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @Column(name = "created_at")
    private LocalDate createdAt = LocalDate.now();

    @JsonIgnore
    @Column(name = "active")
    private boolean active = true;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "technologies")
    private List<Resume> resumes;
}
