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
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "resume_schools",
            joinColumns = {@JoinColumn(name = "resume_id")},
            inverseJoinColumns = {@JoinColumn(name = "school_id")})
    private Set<School> schools = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "resume_job_experiences",
            joinColumns = {@JoinColumn(name = "resume_id")},
            inverseJoinColumns = {@JoinColumn(name = "job_experience_id")})
    private Set<JobExperience> jobExperiences = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "resume_foreign_languages",
            joinColumns = {@JoinColumn(name = "resume_id")},
            inverseJoinColumns = {@JoinColumn(name = "foreign_language_id")})
    private Set<ForeignLanguage> foreignLanguages = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "resume_technologies",
            joinColumns = {@JoinColumn(name = "resume_id")},
            inverseJoinColumns = {@JoinColumn(name = "technology_id")})
    private Set<Technology> technologies = new HashSet<>();

    public Resume(JobSeeker jobSeeker, String github, String linkedin, String coverLetter) {
        this.jobSeeker = jobSeeker;
        this.github = github;
        this.linkedin = linkedin;
        this.coverLetter = coverLetter;
    }
}
