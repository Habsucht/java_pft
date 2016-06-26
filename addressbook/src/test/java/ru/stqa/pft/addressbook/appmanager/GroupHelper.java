/**
 *  The class implements the action with groups
 */

package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.data.GroupData;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends BaseHelper {

    GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void fillGroupForm(GroupData groupData) {
        if (!wd.findElement(By.name("group_name")).getText().equals(groupData.getGroupName())) {
            System.out.println("groupName: " + groupData.getGroupName());

            type(By.name("group_name"), groupData.getGroupName());
            type(By.name("group_header"), groupData.getHeader());
            type(By.name("group_footer"), groupData.getFooter());
        }
    }

    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
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

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<GroupData> getGroupList() {
        List<GroupData> groupList = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            String groupId = element.findElement(By.tagName("input")).getAttribute("value");
            groupList.add(new GroupData(groupId, name, null, null));
        }
        return groupList;
    }
}
