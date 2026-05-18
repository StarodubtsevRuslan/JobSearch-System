package ua.kpi.jobsearch.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import ua.kpi.jobsearch.model.User;
import ua.kpi.jobsearch.model.Vacancy;
import ua.kpi.jobsearch.service.VacancyService;


@WebServlet("/edit_vacancy")
public class EditVacancyServlet extends HttpServlet {

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

        String idStr = request.getParameter("id");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Vacancy vacancy = vacancyService.getVacancyById(id);
                
           
                if (vacancy != null && vacancy.getEmployerId() == user.getId()) {
                    request.setAttribute("vacancy", vacancy);
                    request.getRequestDispatcher("/WEB-INF/jsp/edit_vacancy.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {}
        }
        
      
        response.sendRedirect(request.getContextPath() + "/dashboard");
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("loggedUser");

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Vacancy existingVacancy = vacancyService.getVacancyById(id);

       
            if (existingVacancy != null && existingVacancy.getEmployerId() == user.getId()) {
                
      
                Vacancy updatedVacancy = new Vacancy(
                        id,
                        request.getParameter("title"),
                        request.getParameter("category"),
                        request.getParameter("description"),
                        request.getParameter("requirements"),
                        request.getParameter("experience"),
                        "Повна зайнятість", 
                        request.getParameter("keywords"),
                        Integer.parseInt(request.getParameter("salary")),
                        request.getParameter("location"),
                        existingVacancy.getCreationDate(),
                        user.getId()
                );

           
                vacancyService.updateVacancy(updatedVacancy); 
            }
        } catch (NumberFormatException e) {}

     
        response.sendRedirect(request.getContextPath() + "/dashboard");
    }
}