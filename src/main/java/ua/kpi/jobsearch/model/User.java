package ua.kpi.jobsearch.model;

import java.io.Serializable;

public class User implements Serializable {
    
    public enum Role {
        EMPLOYER, SEEKER
    }

    private int id;
    private String login;
    private String password;
    private Role role;
    private String email;
    private String companyName;

    public User() {
    }

    public User(int id, String login, String password, Role role, String email, String companyName) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
        this.companyName = companyName;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
}