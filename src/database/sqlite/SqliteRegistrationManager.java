package database.sqlite;

import model.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jeremy on 10/17/16.
 */
@Deprecated
public class SqliteRegistrationManager extends Connectable {
    public User getUser(String name) {
        throw new NotImplementedException();
    }

    public void addUser(User user) throws SQLException {


        String username = user.USERNAME;
        String password = user.PASSWORD;
        int authorization = user.AUTH.ordinal();
        String email = user.PROFILE.EMAIL;
        String address = user.PROFILE.HOME_ADDRESS;
        String title = user.PROFILE.TITLE;

        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO userlist "
                + "(username, password, authorization, email, address, title) VALUES"
                + "(?,?,?,?,?,?)";

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, authorization);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, title);


            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
        }

    }
}
