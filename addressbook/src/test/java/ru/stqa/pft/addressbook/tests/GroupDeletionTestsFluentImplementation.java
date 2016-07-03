/**
 *  A class to test the group removal
 */

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class GroupDeletionTestsFluentImplementation extends BaseTests {
    @BeforeMethod
    public void ensurePrecondition() {
        app.getNavigationHelper().gotoGroupPage();

        //  Checking for the presence of at least one group with the subsequent creation
        if (!app.getGroupHelper().isThereAGroup()) {
            GroupCreationTests.testGroupCreation();
        }
    }

    @Test
    public void testGroupDeletion() {
        Groups beforeGroupSet = app.getGroupHelper().all();

        GroupData deletedGroup = beforeGroupSet.iterator().next();

        app.getGroupHelper().selectGroupById(deletedGroup.getGroupId());
        app.getGroupHelper().deleteGroup();

        app.getNavigationHelper().returnToGroupPage();

        Groups afterGroupSet = app.getGroupHelper().all();

        // Check on the number of elements
        Assert.assertEquals(afterGroupSet.size(), beforeGroupSet.size() - 1);

        // Check elements for identity verification
        assertThat(afterGroupSet, equalTo(beforeGroupSet.withOut(deletedGroup)));
    }
}