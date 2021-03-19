package volm.journal.dao;

import volm.journal.config.DBConfiguration;
import volm.journal.enums.Role;
import volm.journal.model.Usr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsrDao {

    public List<Usr> findByGroupId(Long group_id) {
        String sqlQuery = "SELECT id, u_name, email, phone_number, group_id, role FROM usr WHERE group_id = ?";

        List<Usr> users = new ArrayList<>();

        try (final Connection connection = DBConfiguration.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setLong(1, group_id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Usr usr = new Usr(rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getLong(5),
                        Role.valueOf(rs.getString(6))
                );
            users.add(usr);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
