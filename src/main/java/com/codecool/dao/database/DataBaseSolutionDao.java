package com.codecool.dao.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class DataBaseSolutionDao extends AbstractDao {

    public DataBaseSolutionDao(Connection connection) {
        super(connection);
    }

    public void addSolution(String solution_id, String user_id, String title, String answer, LocalDateTime time) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO solution (solution_id, user_id, title, answer, submission_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, solution_id);
            statement.setString(2, user_id);
            statement.setString(3, title);
            statement.setString(4, answer);
            statement.setObject(5, time);
            executeInsert(statement);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }

    }

    public void updateSolution(String solution_id, int currGrade) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "update solution set grade=? where solution_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setInt(1, currGrade);
            statement.setString(2, solution_id);
            executeInsert(statement);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }
}
