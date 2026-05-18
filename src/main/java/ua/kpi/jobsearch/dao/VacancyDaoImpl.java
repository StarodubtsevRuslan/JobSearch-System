package ua.kpi.jobsearch.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ua.kpi.jobsearch.database.DatabaseConnector;
import ua.kpi.jobsearch.model.Application;
import ua.kpi.jobsearch.model.Vacancy;

public class VacancyDaoImpl implements VacancyDao {

    @Override
    public void addVacancy(Vacancy vacancy) {
        String sql = "INSERT INTO vacancies (title, category, description, requirements, experience, " +
                     "employment_type, keywords, salary, location, creation_date, employer_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vacancy.getTitle());
            pstmt.setString(2, vacancy.getCategory());
            pstmt.setString(3, vacancy.getDescription());
            pstmt.setString(4, vacancy.getRequirements());
            pstmt.setString(5, vacancy.getExperience());
            pstmt.setString(6, "Повна зайнятість");
            pstmt.setString(7, vacancy.getKeywords());
            pstmt.setInt(8, vacancy.getSalary());
            pstmt.setString(9, vacancy.getLocation());
            pstmt.setDate(10, java.sql.Date.valueOf(LocalDate.now()));
            pstmt.setInt(11, vacancy.getEmployerId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public void updateVacancy(Vacancy vacancy) {
        String sql = "UPDATE vacancies SET title=?, category=?, description=?, requirements=?, " +
                     "experience=?, keywords=?, salary=?, location=? WHERE id=?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vacancy.getTitle());
            pstmt.setString(2, vacancy.getCategory());
            pstmt.setString(3, vacancy.getDescription());
            pstmt.setString(4, vacancy.getRequirements());
            pstmt.setString(5, vacancy.getExperience());
            pstmt.setString(6, vacancy.getKeywords());
            pstmt.setInt(7, vacancy.getSalary());
            pstmt.setString(8, vacancy.getLocation());
            pstmt.setInt(9, vacancy.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public void deleteVacancy(int id) {
        String sql = "DELETE FROM vacancies WHERE id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public Vacancy getVacancyById(int id) {
        String sql = "SELECT * FROM vacancies WHERE id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToVacancy(rs);
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }

    @Override
    public List<Vacancy> getVacanciesByEmployerId(int employerId) {
        List<Vacancy> result = new ArrayList<>();
        String sql = "SELECT * FROM vacancies WHERE employer_id = ? ORDER BY creation_date DESC";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    result.add(mapResultSetToVacancy(rs));
                }
            }
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public List<Vacancy> getAllVacancies() {
        List<Vacancy> result = new ArrayList<>();
        String sql = "SELECT * FROM vacancies ORDER BY creation_date DESC";
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                result.add(mapResultSetToVacancy(rs));
            }
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public Map<String, Integer> getCategoryCounts() {
        Map<String, Integer> counts = new HashMap<>();
        String sql = "SELECT category, COUNT(*) as cnt FROM vacancies GROUP BY category";
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                counts.put(rs.getString("category"), rs.getInt("cnt"));
            }
        } catch (SQLException e) {
        }
        return counts;
    }

    @Override
    public List<Vacancy> advancedSearch(String keyword, String category, int minSalary, String sortBy, boolean isAscending) {
        List<Vacancy> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM vacancies WHERE salary >= ?");
        boolean hasKeyword = keyword != null && !keyword.trim().isEmpty();
        
        
        boolean hasCategory = category != null && !category.trim().isEmpty() && !category.equals("Усі категорії");

        if (hasKeyword) {
            sql.append(" AND (LOWER(title) LIKE ? OR LOWER(description) LIKE ?)");
        }
        if (hasCategory) {
            sql.append(" AND category = ?");
        }
        if (sortBy != null && !sortBy.isEmpty()) {
            if (sortBy.equals("salary")) {
                sql.append(" ORDER BY salary ");
            } else if (sortBy.equals("date")) {
                sql.append(" ORDER BY creation_date ");
            }
            sql.append(isAscending ? "ASC" : "DESC");
        } else {
            sql.append(" ORDER BY creation_date DESC");
        }

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            int paramIndex = 1;
            pstmt.setInt(paramIndex++, minSalary);
            if (hasKeyword) {
                String searchPattern = "%" + keyword.toLowerCase() + "%";
                pstmt.setString(paramIndex++, searchPattern);
                pstmt.setString(paramIndex++, searchPattern);
            }
            if (hasCategory) {
                pstmt.setString(paramIndex++, category);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    result.add(mapResultSetToVacancy(rs));
                }
            }
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public void addFavorite(int userId, int vacancyId) {
        String sql = "INSERT IGNORE INTO user_favorites (user_id, vacancy_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, vacancyId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public void removeFavorite(int userId, int vacancyId) {
        String sql = "DELETE FROM user_favorites WHERE user_id = ? AND vacancy_id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, vacancyId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public List<Vacancy> getFavoritesByUserId(int userId) {
        List<Vacancy> result = new ArrayList<>();
        String sql = "SELECT v.* FROM vacancies v INNER JOIN user_favorites f ON v.id = f.vacancy_id WHERE f.user_id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    result.add(mapResultSetToVacancy(rs));
                }
            }
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public void applyForVacancy(Application application) {
        String sql = "INSERT INTO applications (vacancy_id, candidate_name, candidate_email, cover_letter) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, application.getVacancyId());
            pstmt.setString(2, application.getCandidateName());
            pstmt.setString(3, application.getCandidateEmail());
            pstmt.setString(4, application.getCoverLetter());
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public List<Application> getApplicationsForVacancy(int vacancyId) {
        List<Application> result = new ArrayList<>();
        String sql = "SELECT * FROM applications WHERE vacancy_id = ? ORDER BY apply_date DESC";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, vacancyId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    result.add(new Application(
                            rs.getInt("id"), rs.getInt("vacancy_id"),
                            rs.getString("candidate_name"), rs.getString("candidate_email"), rs.getString("cover_letter")
                    ));
                }
            }
        } catch (SQLException e) {
        }
        return result;
    }

    private Vacancy mapResultSetToVacancy(ResultSet rs) throws SQLException {
        return new Vacancy(
                rs.getInt("id"), rs.getString("title"), rs.getString("category"),
                rs.getString("description"), rs.getString("requirements"), rs.getString("experience"),
                rs.getString("employment_type"), rs.getString("keywords"), rs.getInt("salary"),
                rs.getString("location"), rs.getDate("creation_date").toLocalDate(), rs.getInt("employer_id")
        );
    }
}