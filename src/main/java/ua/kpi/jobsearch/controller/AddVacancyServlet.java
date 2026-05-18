package ua.kpi.jobsearch.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import ua.kpi.jobsearch.model.User;
import ua.kpi.jobsearch.model.Vacancy;
import ua.kpi.jobsearch.service.VacancyService;

@WebServlet("/add_vacancy")
public class AddVacancyServlet extends HttpServlet {

    private VacancyService vacancyService;

    @Override
    public void init() throws ServletException {
        vacancyService = (VacancyService) getServletContext().getAttribute("vacancyService");
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

        request.getRequestDispatcher("/WEB-INF/jsp/add_vacancy.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        
      
        User user = (session != null) ? (User) session.getAttribute("loggedUser") : null;
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
         
            String title = request.getParameter("title");
            String category = request.getParameter("category");
            String description = request.getParameter("description");
            String requirements = request.getParameter("requirements");
            String experience = request.getParameter("experience");
            String employmentType = request.getParameter("employmentType");
            String keywords = request.getParameter("keywords");
            
           
            int salary = 0;
            String salaryStr = request.getParameter("salary");
            if (salaryStr != null && !salaryStr.isEmpty()) {
                salary = Integer.parseInt(salaryStr);
            }
            
            String location = request.getParameter("location");

       
            Vacancy newVacancy = new Vacancy(0, title, category, description, requirements, 
                    experience, employmentType, keywords, salary, location, LocalDate.now(), user.getId());

      
            vacancyService.addVacancy(newVacancy);
            
    
            response.sendRedirect(request.getContextPath() + "/dashboard");
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/dashboard?error=db_error");
        }
    }
}