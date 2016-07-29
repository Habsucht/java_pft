/**
 *  Class to implement new user authentication data
 */

package ru.stqa.pft.mantis.data;

import ru.stqa.pft.mantis.generator.UserDataGenerator;

public class UserData {
    private final String userName;
    private final String login;
    private final String password;
    private final String email;

    public UserData(String userName, String login, String password, String email) {
        this.login = login;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public UserData() {
        this.userName = UserDataGenerator.generateFirstName();
        this.login = UserDataGenerator.generateNickName(this.userName);
        this.password = UserDataGenerator.generatePassword(this.userName);
        this.email = UserDataGenerator.generateEmailAddress(this.login);
    }

    public String getUserName() {
        return userName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "userName='" + userName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
