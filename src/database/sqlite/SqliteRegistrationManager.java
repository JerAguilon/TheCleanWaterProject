package database.sqlite;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/*
 * Created by jeremy on 10/17/16.
 */
@Deprecated
public class SqliteRegistrationManager extends Connectable {
    public User getUser(String name) {
        User user = new User();

        Connection connection = SqliteConnection.connect();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * FROM userlist WHERE username = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                String username = resultSet.getString(1);
                String password  = resultSet.getString(2);
                String authorization = resultSet.getString(3);
                String email = resultSet.getString(4);
                String address = resultSet.getString(5);
                String title= resultSet.getString(6);

                Profile prof = new Profile(email, address, title);
                AuthorizationLevel auth = AuthorizationLevel.match(authorization);
                user = new User(username, password, auth, prof);

                preparedStatement.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

                return user;
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
