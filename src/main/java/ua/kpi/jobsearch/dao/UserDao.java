package ua.kpi.jobsearch.dao;

import ua.kpi.jobsearch.model.User;

public interface UserDao {
    boolean registerUser(User user);
    User getUserByEmail(String email);
    User getUserById(int id);
}