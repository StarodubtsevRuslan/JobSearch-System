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

@WebServlet("/add_favorite")
public class AddFavoriteServlet extends HttpServlet {

    private final VacancyService vacancyService = new VacancyServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        User user = (User) session.getAttribute("loggedUser");
        String vacancyIdStr = request.getParameter("id");

        if (vacancyIdStr != null) {
            try {
                int vacancyId = Integer.parseInt(vacancyIdStr);
                vacancyService.addFavorite(user.getId(), vacancyId); 
            } catch (NumberFormatException e) {
            }
        }

        String referer = request.getHeader("referer");
        response.sendRedirect(referer != null ? referer : request.getContextPath() + "/search");
    }
}