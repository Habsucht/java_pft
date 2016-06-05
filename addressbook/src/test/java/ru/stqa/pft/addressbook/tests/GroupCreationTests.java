package ru.stqa.pft.addressbook.tests;

import ru.stqa.pft.addressbook.data.GroupData;

import org.testng.annotations.Test;

public class GroupCreationTests extends BaseTests {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData("test1_", "info1_", "info1_"));
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().returnToGroupPage();
    }
}
