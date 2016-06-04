package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

import org.openqa.selenium.*;

public class GroupDeletionTests extends GroupBasicTestsMethod {

    @Test
    public void testGroupDeletion() {
        gotoGroupPage();
        selectGroup();
        deleteGroup();
        returnToGroupPage();
    }

    private void selectGroup() {
        if (!wd.findElement(By.xpath("//div[@id='content']/form/span[1]/input")).isSelected()) {
            wd.findElement(By.xpath("//div[@id='content']/form/span[1]/input")).click();
        }
    }

    private void deleteGroup() {
        wd.findElement(By.name("delete")).click();
    }
}
