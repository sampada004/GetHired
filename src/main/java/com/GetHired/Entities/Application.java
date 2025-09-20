package com.GetHired.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employeeid")
    private Long employeeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "jobid", insertable = false, updatable = false)
    private Job job;

    @Column(name = "jobid")
    private Long jobId;

    private String application_status;

    @Lob
    private String recruiter_notes;

    private LocalDate application_date;

    @PrePersist
    public void setDefaults() {
        if (this.application_status == null) {
            this.application_status = "Applied";
        }
        if (this.recruiter_notes == null) {
            this.recruiter_notes = "";
        }
        if (this.application_date == null) {
            this.application_date = LocalDate.now();
        }
    }

    // Getters and setters for Job field
    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
