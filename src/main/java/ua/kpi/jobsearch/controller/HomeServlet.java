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
import ua.kpi.jobsearch.service.VacancyServiceImpl;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private VacancyService vacancyService;

    @Override
    public void init() throws ServletException {
    
        this.vacancyService = new VacancyServiceImpl();
        
      
        getServletContext().setAttribute("vacancyService", this.vacancyService);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
      
        List<Vacancy> vacancies = vacancyService.getAllVacancies();
        
      
        request.setAttribute("vacanciesList", vacancies);
        
        request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
    }
}