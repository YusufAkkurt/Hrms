package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jobs")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "jobAds"})
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private LocalDate createdAt = LocalDate.now();

    @Column(name = "active")
    private boolean active = true;

    @OneToMany(mappedBy = "job")
    private List<JobAd> jobAds;
}
