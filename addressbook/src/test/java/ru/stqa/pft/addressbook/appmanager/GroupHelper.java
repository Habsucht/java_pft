/**
 *  The class implements the action with groups
 */

package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ru.stqa.pft.addressbook.data.GroupData;

public class GroupHelper extends BaseHelper {

    GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void fillGroupForm(GroupData groupData) {
        if (!wd.findElement(By.name("group_name")).getText().equals(groupData.groupName)) {
            System.out.println("groupName: " + groupData.groupName);

            type(By.name("group_name"), groupData.groupName);
            type(By.name("group_header"), groupData.header);
            type(By.name("group_footer"), groupData.footer);
        }
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void selectGroup(int numGroup) {
        click(By.name("selected[]"));
        /*
        if (!wd.findElement(By.xpath("//div[@id='content']/form/span[" + numGroup + "]/input")).isSelected()) {
           click(By.xpath("//div[@id='content']/form/span[" + numGroup + "]/input"));
        }
        */
    }

    public void deleteGroup() {
        click(By.name("delete"));
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }
}
