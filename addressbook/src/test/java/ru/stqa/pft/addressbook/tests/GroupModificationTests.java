package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.GroupData;

public class GroupModificationTests extends BaseTests{

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();

        if (!app.getGroupHelper().isThereAGroup()) {
            GroupCreationTests.testGroupCreation();
        }

        app.getGroupHelper().selectGroup(1);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData());
        app.getGroupHelper().submitModification();
        app.getGroupHelper().returnToGroupPage();
    }
}
