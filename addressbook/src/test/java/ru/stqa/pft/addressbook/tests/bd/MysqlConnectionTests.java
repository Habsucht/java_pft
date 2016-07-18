package ru.stqa.pft.addressbook.tests.bd;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.ContactData;
import ru.stqa.pft.addressbook.data.GroupData;
import ru.stqa.pft.addressbook.model.Contacts;
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

            resultSet = statement.executeQuery(
                    "SELECT id, firstname, lastname, nickname, company, address, home, mobile, work, fax, email, email2, email3, bday, bmonth, byear FROM addressbook");
            Contacts contacts = new Contacts();
            while (resultSet.next()) {
                contacts.add(new ContactData()
                        .setContactId(resultSet.getInt("id"))
                        .setFirstName(resultSet.getString("firstname"))
                        .setLastName(resultSet.getString("lastname"))
                        .setNickName(resultSet.getString("nickname"))
                        .setCompanyName(resultSet.getString("company"))
                        .setPostAddress(resultSet.getString("address"))
                        .setHomePhoneNumber(resultSet.getString("home"))
                        .setMobilePhoneNumber(resultSet.getString("mobile"))
                        .setWorkPhoneNumber(resultSet.getString("work"))
                        .setFaxPhoneNumber(resultSet.getString("fax"))
                        .setEmailAddress1(resultSet.getString("email"))
                        .setEmailAddress2(resultSet.getString("email2"))
                        .setEmailAddress3(resultSet.getString("email3"))
                        .setBirthdayDay(resultSet.getString("bday"))
                        .setBirthdayMonth(resultSet.getString("bmonth"))
                        .setBirthdayYear(resultSet.getString("byear")));
            }

            resultSet.close();
            statement.close();
            connection.close();

            System.out.println(groups);
            System.out.println(contacts);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    @Test
    public void testDbConnectionForHibernate() {

    }
}
