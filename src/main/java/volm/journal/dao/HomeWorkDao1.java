package volm.journal.dao;

import volm.journal.config.DBConfiguration;
import volm.journal.model.HomeWork;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeWorkDao1 {

    public List<HomeWork> findByLessonsIds(Long lessonId) {
        String sqlQuery = "SELECT id, lesson_id, student_id, done, hw_description " +
                "FROM homework WHERE lesson_id = ?";

        List<HomeWork> homeWorks = new ArrayList<>();

        try (Connection connection = DBConfiguration.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setLong(1, lessonId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                HomeWork homeWork = new HomeWork(rs.getLong(1),
                        rs.getLong(2),
                        rs.getLong(3),
                        rs.getBoolean(4),
                        rs.getString(5)
                );
                homeWorks.add(homeWork);
            }
            return homeWorks;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return homeWorks;
    }

    public HomeWork findById(long hwId) {

        String sqlFindByIdQuery = "SELECT id, lesson_id, student_id, done, hw_description " +
                "FROM homework WHERE id = ?";

        try (Connection connection = DBConfiguration.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlFindByIdQuery);

            preparedStatement.setLong(1, hwId);


            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return new HomeWork(rs.getLong(1),
                        rs.getLong(2),
                        rs.getLong(3),
                        rs.getBoolean(4),
                        rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(HomeWork hw) {

        String sqlSaveQuery = "UPDATE homework SET hw_description = ? WHERE id = ?";

        try (Connection connection = DBConfiguration.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSaveQuery);

            preparedStatement.setString(1, hw.getHw_description());
            preparedStatement.setLong(2, hw.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
