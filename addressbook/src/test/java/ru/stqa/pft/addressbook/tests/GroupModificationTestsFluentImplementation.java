/**
 *  A class to test the change of group data
 */

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.data.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static ru.stqa.pft.addressbook.generator.BaseGenerator.generateRandom;

public class GroupModificationTestsFluentImplementation extends BaseTests{
    @BeforeMethod
    public void ensurePrecondition() {
        app.getNavigationHelper().gotoGroupPage();

        //  Checking for the presence of at least one group with the subsequent creation
        if (!app.getGroupHelper().isThereAGroup()) {
            GroupCreationTests.testGroupCreation();
        }
    }

    @Test
    public void testGroupModification() {
        Groups beforeGroupSet = app.getGroupHelper().all();

        GroupData modifiedGroup = beforeGroupSet.iterator().next();
        GroupData group = new GroupData();

        app.getGroupHelper().selectGroupById(modifiedGroup.getGroupId());
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitModification();

        app.getNavigationHelper().returnToGroupPage();

        Groups afterGroupSet = app.getGroupHelper().all();

        // Check on the number of elements
        Assert.assertEquals(afterGroupSet.size(), beforeGroupSet.size());

        // Assign Id the modification element
        group.setGroupId(modifiedGroup.getGroupId());

        // Check elements for identity verification
        assertThat(afterGroupSet, equalTo(beforeGroupSet.withOut(modifiedGroup).withAdded(group)));
    }
}
