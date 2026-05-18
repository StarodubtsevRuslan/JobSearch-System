package ua.kpi.jobsearch.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import ua.kpi.jobsearch.model.Vacancy;
import ua.kpi.jobsearch.service.VacancyService;

@WebServlet("/employer")
public class EmployerServlet extends HttpServlet {

    private VacancyService vacancyService;

    @Override
    public void init() throws ServletException {
        vacancyService = (VacancyService) getServletContext().getAttribute("vacancyService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idStr = request.getParameter("id");
        
        if (idStr != null && !idStr.isEmpty()) {
            try {
                int employerId = Integer.parseInt(idStr);
                List<Vacancy> list = vacancyService.getVacanciesByEmployerId(employerId);
                
                request.setAttribute("empVacancies", list);
                request.setAttribute("empId", employerId);
                request.getRequestDispatcher("/WEB-INF/jsp/employer_profile.jsp").forward(request, response);
                return;
                
            } catch (NumberFormatException e) {
            }
        }
        
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}