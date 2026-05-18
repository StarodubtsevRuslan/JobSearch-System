package ua.kpi.jobsearch.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Application {
    private int id;
    private final int vacancyId;
    private final String candidateName;
    private final String candidateEmail;
    private final String coverLetter;
    private final LocalDateTime applyDate;

    public Application(int id, int vacancyId, String candidateName, String candidateEmail, String coverLetter) {
        this.id = id;
        this.vacancyId = vacancyId;
        this.candidateName = candidateName;
        this.candidateEmail = candidateEmail;
        this.coverLetter = coverLetter;
        this.applyDate = LocalDateTime.now(); 
    }

   
    public int getId() { return id; }
    public void setId(int id) { this.id = id; } 
    public int getVacancyId() { return vacancyId; }
    public String getCandidateName() { return candidateName; }
    public String getCandidateEmail() { return candidateEmail; }
    public String getCoverLetter() { return coverLetter; }
    
   
    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return applyDate.format(formatter);
    }
}