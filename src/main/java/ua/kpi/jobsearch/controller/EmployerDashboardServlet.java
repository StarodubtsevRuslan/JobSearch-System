package ua.kpi.jobsearch.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import ua.kpi.jobsearch.model.User;
import ua.kpi.jobsearch.model.Vacancy;
import ua.kpi.jobsearch.service.VacancyService;
import ua.kpi.jobsearch.service.VacancyServiceImpl;

@WebServlet("/dashboard")
public class EmployerDashboardServlet extends HttpServlet {

    private VacancyService vacancyService;

    @Override
    public void init() throws ServletException {
        
        vacancyService = (VacancyService) getServletContext().getAttribute("vacancyService");
        
      
        if (vacancyService == null) {
            vacancyService = new VacancyServiceImpl(); 
            getServletContext().setAttribute("vacancyService", vacancyService); 
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
 
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("loggedUser") : null;

       
        if (user == null || user.getRole() != User.Role.EMPLOYER) {
            
            response.sendRedirect(request.getContextPath() + "/login");
            return; 
        }

     
        List<Vacancy> myVacancies = vacancyService.getVacanciesByEmployerId(user.getId());


        request.setAttribute("myVacancies", myVacancies);
        
        request.getRequestDispatcher("/WEB-INF/jsp/employer_dashboard.jsp").forward(request, response);
    }
}