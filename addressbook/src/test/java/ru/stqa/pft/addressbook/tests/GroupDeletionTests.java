package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends BaseTests {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup(2);
        app.getGroupHelper().deleteGroup();
        app.getGroupHelper().returnToGroupPage();
    }
}
