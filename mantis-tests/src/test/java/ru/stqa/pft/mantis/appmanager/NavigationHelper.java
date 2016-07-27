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

    public void gotoMainPage() {
        if (!isElementPresent(By.linkText("myview_boxes_area"))) {
            click(By.cssSelector("a[href='/mantisbt-1.3.0/my_view_page.php']"));
        }
    }

    public void gotoRegistrationPage() {
        if (isElementPresent(By.linkText("зарегистрировать новую учетную запись"))) {
            click(By.cssSelector("a[href='signup_page.php']"));
        }
    }
}