package ru.stqa.pft.addressbook.tests;

import ru.stqa.pft.addressbook.data.GroupData;

import org.testng.annotations.Test;

public class GroupCreationTests extends BaseTests {

    @Test
    public static void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData());
        app.getGroupHelper().submitModification();
        app.getGroupHelper().returnToGroupPage();
    }
}
