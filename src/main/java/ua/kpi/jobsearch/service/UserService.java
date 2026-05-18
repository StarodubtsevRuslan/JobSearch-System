package ua.kpi.jobsearch.service;

import ua.kpi.jobsearch.model.User;

public interface UserService {

    User authenticate(String email, String password);
    

    boolean registerUser(User user);
    
    boolean isEmailTaken(String email);
    
    User getUserById(int id);
}