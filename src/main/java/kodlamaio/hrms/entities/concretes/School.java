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
@Table(name = "schools")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "part")
    private String part;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "finish_date")
    private LocalDate finishDate;

    @JsonIgnore
    @Column(name = "created_at")
    private LocalDate createdAt = LocalDate.now();

    @JsonIgnore
    @Column(name = "active")
    private boolean active = true;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "job_seeker_id")
    private JobSeeker jobSeeker;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "schools")
    private List<Resume> resumes;

    public String getFinishDate() {
        if (this.finishDate == null) return "Devam ediyor";
        return this.finishDate.toString();
    }
}
