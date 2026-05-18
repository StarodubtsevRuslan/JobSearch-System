package ua.kpi.jobsearch.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import ua.kpi.jobsearch.model.Application;
import ua.kpi.jobsearch.model.Vacancy;
import ua.kpi.jobsearch.service.VacancyService;

@WebServlet("/apply")
public class ApplyServlet extends HttpServlet {

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
                    request.getRequestDispatcher("/WEB-INF/jsp/apply_form.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
            }
        }
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
  
        request.setCharacterEncoding("UTF-8");
        
  
        String vacancyIdStr = request.getParameter("vacancyId");
        String vacancyTitle = request.getParameter("vacancyTitle");
        String candidateName = request.getParameter("candidateName");
        String candidateEmail = request.getParameter("candidateEmail");
        String coverLetter = request.getParameter("coverLetter");

 
        if (vacancyIdStr != null) {
            try {
                int vacId = Integer.parseInt(vacancyIdStr);
        
                Application app = new Application(0, vacId, candidateName, candidateEmail, coverLetter);
         
                vacancyService.applyForVacancy(app);
            } catch (NumberFormatException e) {
       
            }
        }
        

        request.setAttribute("candidateName", candidateName);
        request.setAttribute("vacancyTitle", vacancyTitle);
        
        request.getRequestDispatcher("/WEB-INF/jsp/apply_success.jsp").forward(request, response);
    }
}