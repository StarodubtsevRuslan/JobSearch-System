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

@WebServlet("/delete_vacancy")
public class DeleteVacancyServlet extends HttpServlet {

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
                int vacancyId = Integer.parseInt(idStr);
                Vacancy vacancyToDelete = vacancyService.getVacancyById(vacancyId);
                
             
                if (vacancyToDelete != null && vacancyToDelete.getEmployerId() == user.getId()) {
                    vacancyService.deleteVacancy(vacancyId);
                }
            } catch (NumberFormatException e) {}
        }
        
        
        response.sendRedirect(request.getContextPath() + "/dashboard");
    }
}