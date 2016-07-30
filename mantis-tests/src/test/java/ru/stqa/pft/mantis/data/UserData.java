/**
 *  Class to implement new user authentication data
 */

package ru.stqa.pft.mantis.data;

import ru.stqa.pft.mantis.generator.UserDataGenerator;

public class UserData {
    private String userName;
    private String login;
    private String password;
    private String email;

    public UserData(String userName, String login, String password, String email) {
        this.userName = userName;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public UserData() {
        this.userName = UserDataGenerator.generateFirstName();
        this.login = UserDataGenerator.generateNickName(this.userName);
        this.password = UserDataGenerator.generatePassword(this.login);
        this.email = UserDataGenerator.generateEmailAddress(this.login);
    }

    public String getUserName() {
        return userName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        if (password == null) {
            setPassword(UserDataGenerator.generatePassword(this.userName) + "_");
        }
        return password;
    }

    public String getEmail() {
        return email;
    }

    public UserData setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserData setLogin(String login) {
        this.login = login;
        return this;
    }

    public UserData setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserData setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return "UserData{" +
                ", userName='" + userName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
