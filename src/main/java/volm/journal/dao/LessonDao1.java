package volm.journal.dao;

import volm.journal.config.DBConfiguration;
import volm.journal.enums.Role;
import volm.journal.model.Lesson;
import volm.journal.model.Usr;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LessonDao1 {

    public List<Lesson> findByGroupId(Long group_id) {
        String sqlQuery = "SELECT id, group_id, create_date FROM lesson WHERE group_id = ?";

        List<Lesson> lessons = new ArrayList<>();

        try (Connection connection = DBConfiguration.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setLong(1, group_id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Lesson lesson = new Lesson(rs.getLong(1),
                        rs.getLong(2),
                        rs.getDate(3)
                );
                lessons.add(lesson);
            }
            return lessons;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }


    public Lesson save(Lesson lesson) {
        String sqlSaveQuery = "INSERT INTO lesson (group_id, create_date) VALUES (?, ?)";

        String sqlGetQuery = "SELECT id, group_id, create_date FROM lesson WHERE id = (SELECT MAX(id) FROM lesson)";

        try (Connection connection = DBConfiguration.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSaveQuery);
            preparedStatement.setLong(1, lesson.getGroup_id());
            preparedStatement.setDate(2, new Date(lesson.getCreate_date().getTime()));

            preparedStatement.executeUpdate();

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlGetQuery);

            if (rs.next()) {
                return new Lesson(rs.getLong(1),
                        rs.getLong(2),
                        rs.getDate(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
