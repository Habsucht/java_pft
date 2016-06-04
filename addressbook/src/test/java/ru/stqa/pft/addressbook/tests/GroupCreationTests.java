package ru.stqa.pft.addressbook.tests;

import ru.stqa.pft.addressbook.data.GroupData;

import org.openqa.selenium.*;
import org.testng.annotations.Test;

public class GroupCreationTests extends GroupBasicTestsMethod {

    @Test
    public void testGroupCreation() {
        gotoGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("test1", "info1", "info1"));
        submitGroupCreation();
        returnToGroupPage();
    }

    private void initGroupCreation() {
        wd.findElement(By.name("new")).click();
    }

    private void fillGroupForm(GroupData groupData) {
        wd.findElement(By.name("group_name")).click();
        wd.findElement(By.name("group_name")).clear();
        wd.findElement(By.name("group_name")).sendKeys(groupData.getGroupName());
        wd.findElement(By.name("group_header")).click();
        wd.findElement(By.name("group_header")).clear();
        wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
        wd.findElement(By.name("group_footer")).click();
        wd.findElement(By.name("group_footer")).clear();
        wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    private void submitGroupCreation() {
        wd.findElement(By.name("submit")).click();
    }
}
