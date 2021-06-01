package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "resumes")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "github")
    private String github;

    @Column(name = "linkedin")
    private String linkedin;

    @Column(name = "cover_letter")
    private String coverLetter;

    @Column(name = "created_at")
    private LocalDate createdAt = LocalDate.now();

    @Column(name = "active")
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id")
    private JobSeeker jobSeeker;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "resume_schools",
            joinColumns = {@JoinColumn(name = "resume_id")},
            inverseJoinColumns = {@JoinColumn(name = "school_id")})
    private Set<School> schools = new HashSet<>();

    public Resume(JobSeeker jobSeeker, String imageUrl, String github, String linkedin, String coverLetter) {
        this.jobSeeker = jobSeeker;
        this.imageUrl = imageUrl;
        this.github = github;
        this.linkedin = linkedin;
        this.coverLetter = coverLetter;
    }
}
