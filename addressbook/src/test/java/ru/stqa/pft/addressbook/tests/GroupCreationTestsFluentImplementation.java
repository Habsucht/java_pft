/**
 *  A class to test the creation of a new group
 */

package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class GroupCreationTestsFluentImplementation extends BaseTests {
    @BeforeMethod
    public void ensurePrecondition() {
        app.getNavigationHelper().gotoGroupPage();
    }

    @Test
    public static void testGroupCreation() {
        Groups beforeGroupSet = app.getGroupHelper().all();

        GroupData group = new GroupData();

        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitModification();

        app.getNavigationHelper().returnToGroupPage();

        Groups afterGroupSet = app.getGroupHelper().all();

        // Check on the number of elements
        assertThat(afterGroupSet.size(), equalTo(beforeGroupSet.size() + 1));

        // Check elements for identity verification
        assertThat(afterGroupSet, equalTo(beforeGroupSet.withAdded(group.setGroupId(afterGroupSet.stream().mapToInt((g) -> g.getGroupId()).max().getAsInt()))));
    }
}
