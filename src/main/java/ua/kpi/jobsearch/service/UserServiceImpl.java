package ua.kpi.jobsearch.service;

import org.mindrot.jbcrypt.BCrypt;
import ua.kpi.jobsearch.dao.UserDao;
import ua.kpi.jobsearch.dao.UserDaoImpl;
import ua.kpi.jobsearch.model.User;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    public boolean registerUser(User user) {
        if (isEmailTaken(user.getEmail())) {
            return false;
        }
 
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        
        return userDao.registerUser(user);
    }

    @Override
    public User authenticate(String email, String password) {
        User user = userDao.getUserByEmail(email);
        
   
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public boolean isEmailTaken(String email) {
        return userDao.getUserByEmail(email) != null;
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }
}