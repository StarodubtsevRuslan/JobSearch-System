package ua.kpi.jobsearch.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie; 
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import ua.kpi.jobsearch.model.Vacancy;
import ua.kpi.jobsearch.service.VacancyService;
import ua.kpi.jobsearch.service.VacancyServiceImpl;

@WebServlet("/search") 
public class SearchServlet extends HttpServlet {

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
        
      
        String keyword = request.getParameter("keyword");
        String category = request.getParameter("category");
        String minSalaryStr = request.getParameter("minSalary");
        String sortBy = request.getParameter("sortBy");
        String ascStr = request.getParameter("asc");

      
        if (keyword != null && !keyword.trim().isEmpty()) {
            Cookie lastSearchCookie = new Cookie("lastSearch", keyword); 
            lastSearchCookie.setMaxAge(60 * 60 * 24); 
            response.addCookie(lastSearchCookie); 
        }

      
        int minSalary = 0;
        if (minSalaryStr != null && !minSalaryStr.isEmpty()) {
            try {
                minSalary = Integer.parseInt(minSalaryStr);
            } catch (NumberFormatException e) {
                minSalary = 0; 
            }
        }
        
    
        boolean isAscending = false; 
        if (ascStr != null) {
            isAscending = Boolean.parseBoolean(ascStr);
        }

       
        List<Vacancy> results = vacancyService.advancedSearch(keyword, category, minSalary, sortBy, isAscending);

      
        request.setAttribute("vacanciesList", results);
        request.setAttribute("categoryCounts", vacancyService.getCategoryCounts());

      
        request.getRequestDispatcher("/WEB-INF/jsp/vacancies_list.jsp").forward(request, response);
    }
}