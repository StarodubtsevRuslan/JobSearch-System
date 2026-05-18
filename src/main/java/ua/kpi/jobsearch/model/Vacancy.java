package ua.kpi.jobsearch.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Vacancy implements Serializable {
    
    private int id;
    private String title;
    private String category;
    private String description;
    private String requirements;
    private String experience;
    private String employmentType;
    private String keywords;
    private int salary;
    private String location;
    private LocalDate creationDate;
    private int employerId;

    public Vacancy() {
    }

    public Vacancy(int id, String title, String category, String description, 
                   String requirements, String experience, String employmentType, 
                   String keywords, int salary, String location, 
                   LocalDate creationDate, int employerId) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.description = description;
        this.requirements = requirements;
        this.experience = experience;
        this.employmentType = employmentType;
        this.keywords = keywords;
        this.salary = salary;
        this.location = location;
        this.creationDate = creationDate;
        this.employerId = employerId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getRequirements() { return requirements; }
    public void setRequirements(String requirements) { this.requirements = requirements; }

    public String getExperience() { return experience; }
    public void setExperience(String experience) { this.experience = experience; }

    public String getEmploymentType() { return employmentType; }
    public void setEmploymentType(String employmentType) { this.employmentType = employmentType; }

    public String getKeywords() { return keywords; }
    public void setKeywords(String keywords) { this.keywords = keywords; }

    public int getSalary() { return salary; }
    public void setSalary(int salary) { this.salary = salary; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public LocalDate getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }

    public int getEmployerId() { return employerId; }
    public void setEmployerId(int employerId) { this.employerId = employerId; }
}