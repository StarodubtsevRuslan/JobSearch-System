package ua.kpi.jobsearch.service;

import java.util.List;
import java.util.Map;
import ua.kpi.jobsearch.dao.VacancyDao;
import ua.kpi.jobsearch.dao.VacancyDaoImpl;
import ua.kpi.jobsearch.model.Application;
import ua.kpi.jobsearch.model.Vacancy;

public class VacancyServiceImpl implements VacancyService {

    private final VacancyDao vacancyDao;

    public VacancyServiceImpl() {
        this.vacancyDao = new VacancyDaoImpl();
    }

    @Override
    public void addVacancy(Vacancy vacancy) { vacancyDao.addVacancy(vacancy); }

    @Override
    public void updateVacancy(Vacancy vacancy) { vacancyDao.updateVacancy(vacancy); }

    @Override
    public void deleteVacancy(int id) { vacancyDao.deleteVacancy(id); }

    @Override
    public Vacancy getVacancyById(int id) { return vacancyDao.getVacancyById(id); }

    @Override
    public List<Vacancy> getVacanciesByEmployerId(int employerId) { return vacancyDao.getVacanciesByEmployerId(employerId); }

    @Override
    public List<Vacancy> getAllVacancies() { return vacancyDao.getAllVacancies(); }

    @Override
    public Map<String, Integer> getCategoryCounts() { return vacancyDao.getCategoryCounts(); }

    @Override
    public List<Vacancy> advancedSearch(String keyword, String category, int minSalary, String sortBy, boolean isAscending) {
        return vacancyDao.advancedSearch(keyword, category, minSalary, sortBy, isAscending);
    }

    @Override
    public void addFavorite(int userId, int vacancyId) { vacancyDao.addFavorite(userId, vacancyId); }

    @Override
    public void removeFavorite(int userId, int vacancyId) { vacancyDao.removeFavorite(userId, vacancyId); }

    @Override
    public List<Vacancy> getFavoritesByUserId(int userId) { return vacancyDao.getFavoritesByUserId(userId); }

    @Override
    public void applyForVacancy(Application application) { vacancyDao.applyForVacancy(application); }

    @Override
    public List<Application> getApplicationsForVacancy(int vacancyId) { return vacancyDao.getApplicationsForVacancy(vacancyId); }
}