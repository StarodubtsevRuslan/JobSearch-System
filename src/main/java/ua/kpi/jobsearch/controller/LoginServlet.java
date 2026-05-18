package ua.kpi.jobsearch.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import ua.kpi.jobsearch.model.User;
import ua.kpi.jobsearch.service.UserService;
import ua.kpi.jobsearch.service.UserServiceImpl;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

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
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

    
        User user = userService.authenticate(email, password);

        if (user != null) {
         
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser", user);
            
          
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
          
            request.setAttribute("errorMessage", "Невірний Email або пароль!");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }
}