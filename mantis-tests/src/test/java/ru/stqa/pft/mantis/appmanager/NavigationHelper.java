/**
 *  The class implements to the site
 */

package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {

    NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoPage(String link) {
        wd.get(link);
    }

    public void gotoMainPage() {
            click(By.cssSelector("a[href='/mantisbt-1.3.0/my_view_page.php']"));
    }

    public void gotoLoginPage(String webBaseUrl) {
            wd.get(webBaseUrl + "login_page.php");
    }

    public void gotoRegistrationPage() {
        if (isElementPresent(By.linkText("зарегистрировать новую учетную запись"))) {
            click(By.cssSelector("a[href='signup_page.php']"));
        }
    }

    public void gotoManageUserPage() {
        click(By.cssSelector("a[href='/mantisbt-1.3.0/manage_user_page.php']"));
    }
}