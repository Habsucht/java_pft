/**
 *  The class implements to the site
 */

package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.data.GroupData;

public class NavigationHelper extends BaseHelper {

    NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoAllGroupsListPage() {
        if (!isElementPresent(By.tagName("h1")) || !wd.findElement(By.tagName("h1")).getText().equals("Groups") || !isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }

    public void gotoSelectGroupPage(GroupData group) {
        if (isElementPresent(By.id("maintable"))) {
            selectElementFromList("group", group.getGroupName());
        }
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void gotoHomePage() {
        if (!isElementPresent(By.id("maintable"))) {
            click(By.linkText("home"));
        }
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }
}
