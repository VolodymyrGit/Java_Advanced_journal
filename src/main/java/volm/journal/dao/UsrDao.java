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
import java.util.Optional;

public class UsrDao {


    public List<Usr> findByGroupId(Long group_id) {
        String sqlQuery = "SELECT id, u_name, email, phone_number, group_id, role, password " +
                "FROM usr WHERE group_id = ?";

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
                        Role.valueOf(rs.getString(6)),
                        rs.getString(7));

            users.add(usr);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    public Optional<Usr> findByEmail(String email) {

        String sqlFindByEmailQuery =
                "SELECT id, u_name, email, phone_number, group_id, role, password From usr WHERE email = ?";

        try (Connection connection = DBConfiguration.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlFindByEmailQuery);

            preparedStatement.setString(1, email);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
             Usr user = new Usr(rs.getLong(1),
                     rs.getString(2),
                     rs.getString(3),
                     rs.getString(4),
                     rs.getLong(5),
                     Role.valueOf(rs.getString(6)),
                     rs.getString(7));
                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    public void save(Usr user) {

        String sqlSaveQuery = "INSERT INTO usr (u_name, email, phone_number, group_id, role, password) " +
                "VALUES (?,?,?,?,?,?)";

        try (Connection connection = DBConfiguration.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSaveQuery);

            preparedStatement.setString(1, user.getU_name());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPhone_number());
            preparedStatement.setLong(4, user.getGroup_id());
            preparedStatement.setString(5, user.getRole().toString());
            preparedStatement.setString(6, user.getPassword());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkExist(String email, String password) {

        String sqlFindByEmailQuery =
                "SELECT count(*) as count From usr WHERE email = ? AND password = ?";

        try (Connection connection = DBConfiguration.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlFindByEmailQuery);

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                long count = rs.getLong(1);
                if(count == 0) {
                    return false;
                } else if(count == 1) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
