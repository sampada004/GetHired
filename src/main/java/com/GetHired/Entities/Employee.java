package com.GetHired.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String degree;
    private int passout_year;
    private String skills;
    private String resume_link;

    public Employee(){}

    public Employee(String degree, String email, Long id, String name, int passout_year, String password, String resume_link, String skills) {
        this.degree = degree;
        this.email = email;
        this.id = id;
        this.name = name;
        this.passout_year = passout_year;
        this.password = password;
        this.resume_link = resume_link;
        this.skills = skills;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPassout_year() {
        return passout_year;
    }

    public void setPassout_year(int passout_year) {
        this.passout_year = passout_year;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResume_link() {
        return resume_link;
    }

    public void setResume_link(String resume_link) {
        this.resume_link = resume_link;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "degree='" + degree + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", passout_year=" + passout_year +
                ", skills='" + skills + '\'' +
                ", resume_link='" + resume_link + '\'' +
                '}';
    }
}
