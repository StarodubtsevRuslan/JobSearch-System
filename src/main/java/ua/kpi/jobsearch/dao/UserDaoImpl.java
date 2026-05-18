package ua.kpi.jobsearch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ua.kpi.jobsearch.database.DatabaseConnector;
import ua.kpi.jobsearch.model.User;

public class UserDaoImpl implements UserDao {

    @Override
    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (login, password, role, email, company_name) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getLogin());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getRole().name());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getCompanyName());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Помилка реєстрації в БД: " + e.getMessage());
            return false;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        User.Role.valueOf(rs.getString("role")),
                        rs.getString("email"),
                        rs.getString("company_name")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Помилка пошуку по email: " + e.getMessage());
        }
        return null;
    }

    @Override
    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        User.Role.valueOf(rs.getString("role")),
                        rs.getString("email"),
                        rs.getString("company_name")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Помилка пошуку по ID: " + e.getMessage());
        }
        return null;
    }
}