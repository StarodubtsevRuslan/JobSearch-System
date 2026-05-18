package ua.kpi.jobsearch.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import ua.kpi.jobsearch.model.Application;
import ua.kpi.jobsearch.model.User;
import ua.kpi.jobsearch.model.Vacancy;
import ua.kpi.jobsearch.service.VacancyService;

@WebServlet("/view_applications")
public class ViewApplicationsServlet extends HttpServlet {

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

        String vacancyIdStr = request.getParameter("vacancyId");
        if (vacancyIdStr != null) {
            try {
                int vacancyId = Integer.parseInt(vacancyIdStr);
                Vacancy vacancy = vacancyService.getVacancyById(vacancyId);

               
                if (vacancy != null && vacancy.getEmployerId() == user.getId()) {
                    List<Application> apps = vacancyService.getApplicationsForVacancy(vacancyId);
                    
                    request.setAttribute("vacancy", vacancy);
                    request.setAttribute("applications", apps);
                    request.getRequestDispatcher("/WEB-INF/jsp/view_applications.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {}
        }
        response.sendRedirect(request.getContextPath() + "/dashboard");
    }
}