package ru.stqa.pft.addressbook.tests.bd;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.sql.*;

public class MysqlConnectionTests {

    @Test
    public void testDbConnectionForJDBC() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?useSSL=false&serverTimezone=UTC&user=root&password=");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list");

            Groups groups = new Groups();
            while (resultSet.next()) {
                groups.add(new GroupData()
                        .setGroupId(resultSet.getInt("group_id"))
                        .setGroupName(resultSet.getString("group_name"))
                        .setGroupHeader(resultSet.getString("group_header"))
                        .setGroupFooter(resultSet.getString("group_footer")));
            }
            resultSet.close();
            statement.close();
            connection.close();

            System.out.println(groups);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
