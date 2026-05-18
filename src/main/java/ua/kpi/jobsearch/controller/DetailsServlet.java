package ua.kpi.jobsearch.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import ua.kpi.jobsearch.model.Vacancy;
import ua.kpi.jobsearch.service.VacancyService;

@WebServlet("/details")
public class DetailsServlet extends HttpServlet {

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
                int id = Integer.parseInt(idStr);
                Vacancy vacancy = vacancyService.getVacancyById(id);
                
                if (vacancy != null) {
                    request.setAttribute("vacancy", vacancy);
                    request.getRequestDispatcher("/WEB-INF/jsp/vacancy_details.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
              
            }
        }
        
   
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}