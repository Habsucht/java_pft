package ru.stqa.pft.mantis.appmanager.service;

import ru.stqa.pft.mantis.data.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.sql.*;

public class DbHelper {
    public Users getUserFromDb() {
        Users users = new Users();
        try {
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/bugtracker?useSSL=false&serverTimezone=UTC&user=root&password=");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT username, realname, email FROM mantis_user_table");
            while (resultSet.next()) {
                users.add(new UserData()
                        .setLogin(resultSet.getString("username"))
                        .setUserName(resultSet.getString("realname"))
                        .setEmail(resultSet.getString("email")));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }
        users.forEach(System.out::println);
        return users;
    }
}
