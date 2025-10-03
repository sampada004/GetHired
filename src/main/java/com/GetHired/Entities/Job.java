package com.GetHired.Entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String description;

    private String location;

    private String salary;

    private String company;

    @Column(name = "apply_before")
    private LocalDate applyBefore;

    @ManyToOne
    @JoinColumn(name = "recruiter_id", referencedColumnName = "id", nullable = true) // foreign key
    private Recruiter recruiter;

    @Column(name = "posted_date")
    private LocalDateTime postedDate;

    @PrePersist
    public void setPostedDate() {
        this.postedDate = LocalDateTime.now();
    }

    @Column(name = "required_skills") // 👈 corrected column name
    private String requiredSkills;

    public Job() {}

    // constructor without recruiter for now
    public Job(LocalDate applyBefore, String description, Long id, String location,
               LocalDateTime postedDate, Recruiter recruiter, String requiredSkills,
               String salary, String title, String company) {
        this.applyBefore = applyBefore;
        this.description = description;
        this.id = id;
        this.location = location;
        this.postedDate = postedDate;
        this.recruiter = recruiter;
        this.requiredSkills = requiredSkills;
        this.salary = salary;
        this.title = title;
        this.company = company;
    }

    // Getters & Setters

    public LocalDate getApplyBefore() {
        return applyBefore;
    }

    public void setApplyBefore(LocalDate applyBefore) {
        this.applyBefore = applyBefore;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(LocalDateTime postedDate) {
        this.postedDate = postedDate;
    }

    public Recruiter getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", salary='" + salary + '\'' +
                ", company='" + company + '\'' +
                ", applyBefore=" + applyBefore +
                ", recruiter=" + (recruiter != null ? recruiter.getId() : null) +
                ", postedDate=" + postedDate +
                ", requiredSkills='" + requiredSkills + '\'' +
                '}';
    }
}
