/**
 *  Class to implement authentication data
 */

package ru.stqa.pft.mantis.data;

public class LoginData {
    private final String userName;
    private final String password;

    public LoginData(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public LoginData() {
        this.userName = "admin";
        this.password = "admin";
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}

