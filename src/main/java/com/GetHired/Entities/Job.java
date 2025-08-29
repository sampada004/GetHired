package com.GetHired.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Lob
    private String description;
    private String location;
    private String salary;

    private LocalDate apply_before;

    @Column(name = "recruiter_id")
    private Long recruiter_id;

    private LocalDateTime posted_date;

    @PrePersist
    public void setPostedDate() {
        this.posted_date = LocalDateTime.now();
    }

    private String required_skills;

    public Job() {
    }

    public Job(LocalDate apply_before, String description, Long id, String location, LocalDateTime posted_date, Long recruiter_id, String required_skills, String salary, String title) {
        this.apply_before = apply_before;
        this.description = description;
        this.id = id;
        this.location = location;
        this.posted_date = posted_date;
        this.recruiter_id = recruiter_id;
        this.required_skills = required_skills;
        this.salary = salary;
        this.title = title;
    }

    public LocalDate getApply_before() {
        return apply_before;
    }

    public void setApply_before(LocalDate apply_before) {
        this.apply_before = apply_before;
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

    public LocalDateTime getPosted_date() {
        return posted_date;
    }

    public void setPosted_date(LocalDateTime posted_date) {
        this.posted_date = posted_date;
    }

    public Long getRecruiter_id() {
        return recruiter_id;
    }

    public void setRecruiter_id(Long recruiter_id) {
        this.recruiter_id = recruiter_id;
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

    public String getRequired_skills() {
        return required_skills;
    }

    public void setRequired_skills(String required_skills) {
        this.required_skills = required_skills;
    }

    @Override
    public String toString() {
        return "Job{" +
                "apply_before=" + apply_before +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", salary='" + salary + '\'' +
                ", recruiter_id=" + recruiter_id +
                ", posted_date=" + posted_date +
                '}';
    }
}
