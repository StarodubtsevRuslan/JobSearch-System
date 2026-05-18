package ua.kpi.jobsearch.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import ua.kpi.jobsearch.model.User;
import ua.kpi.jobsearch.service.VacancyService;
import ua.kpi.jobsearch.service.VacancyServiceImpl;

@WebServlet("/remove_favorite")
public class RemoveFavoriteServlet extends HttpServlet {

    private final VacancyService vacancyService = new VacancyServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("loggedUser") != null) {
            User user = (User) session.getAttribute("loggedUser");
            String vacancyIdStr = request.getParameter("id");
            
            if (vacancyIdStr != null) {
                try {
                    int vacancyId = Integer.parseInt(vacancyIdStr);
                    vacancyService.removeFavorite(user.getId(), vacancyId); 
                } catch (NumberFormatException e) {
                }
            }
        }
    
        response.sendRedirect(request.getContextPath() + "/favorites");
    }
}