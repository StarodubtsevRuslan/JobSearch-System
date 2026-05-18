package ua.kpi.jobsearch.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import ua.kpi.jobsearch.model.User;
import ua.kpi.jobsearch.service.UserService;
import ua.kpi.jobsearch.service.UserServiceImpl;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
        if (userService == null) {
            userService = new UserServiceImpl();
            getServletContext().setAttribute("userService", userService);
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String login = request.getParameter("login"); 
        String roleStr = request.getParameter("role"); 
        
        User.Role role = User.Role.valueOf(roleStr);
        
    
        String companyName = null;
        if (role == User.Role.EMPLOYER) {
            companyName = request.getParameter("companyName");
        }

 
        User newUser = new User(0, login, password, role, email, companyName);

     
        boolean isRegistered = userService.registerUser(newUser);

        if (isRegistered) {
          
            request.setAttribute("successMessage", "Реєстрація успішна! Тепер ви можете увійти.");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        } else {
           
            request.setAttribute("errorMessage", "Користувач з таким Email вже існує!");
            request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
        }
    }
}