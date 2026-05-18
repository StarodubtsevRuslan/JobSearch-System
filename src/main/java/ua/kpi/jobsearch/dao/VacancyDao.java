package ua.kpi.jobsearch.dao;

import java.util.List;
import java.util.Map;
import ua.kpi.jobsearch.model.Application;
import ua.kpi.jobsearch.model.Vacancy;

public interface VacancyDao {
    void addVacancy(Vacancy vacancy);
    void updateVacancy(Vacancy vacancy);
    void deleteVacancy(int id);
    Vacancy getVacancyById(int id);
    List<Vacancy> getVacanciesByEmployerId(int employerId);
    List<Vacancy> getAllVacancies();
    Map<String, Integer> getCategoryCounts();
    List<Vacancy> advancedSearch(String keyword, String category, int minSalary, String sortBy, boolean isAscending);
    void addFavorite(int userId, int vacancyId);
    void removeFavorite(int userId, int vacancyId);
    List<Vacancy> getFavoritesByUserId(int userId);
    void applyForVacancy(Application application);
    List<Application> getApplicationsForVacancy(int vacancyId);
}