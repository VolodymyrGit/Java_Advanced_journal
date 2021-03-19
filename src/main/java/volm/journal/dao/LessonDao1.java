package volm.journal.dao;

import volm.journal.config.DBConfiguration;
import volm.journal.enums.Role;
import volm.journal.model.Lesson;
import volm.journal.model.Usr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonDao1 {

    public List<Lesson> findByGroupId(Long group_id) {
        String sqlQuery = "SELECT id, group_id, create_date FROM lesson WHERE group_id = ?";

        List<Lesson> lessons = new ArrayList<>();

        try (final Connection connection = DBConfiguration.getConnection()) {
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

}
