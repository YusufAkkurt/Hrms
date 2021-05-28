package kodlamaio.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobAdDto {
    private int id;
    private String companyName;
    private String jobName;
    private int openPositionCount;
    private LocalDate createdAt;
    private LocalDate applicationDeadline;
}
