package kodlamaio.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeAddDto {
    private int jobSeekerId;
    private String imageUrl;
    private String github;
    private String linkedin;
    private String coverLetter;
    private List<Integer> schoolIds;
    private List<Integer> jobExperienceIds;
}
