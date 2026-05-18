package ua.kpi.jobsearch.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import ua.kpi.jobsearch.model.User;
import ua.kpi.jobsearch.model.Vacancy;
import ua.kpi.jobsearch.service.VacancyService;
import ua.kpi.jobsearch.service.VacancyServiceImpl;

@WebServlet("/favorites")
public class FavoritesServlet extends HttpServlet {

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
        List<Vacancy> favs = vacancyService.getFavoritesByUserId(user.getId());

        request.setAttribute("favoritesList", favs);
        request.getRequestDispatcher("/WEB-INF/jsp/favorites.jsp").forward(request, response);
    }
}