/**
 *  A class to test the creation of a new group
 */

package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import ru.stqa.pft.addressbook.activescenarios.InitializationSaveGeneratedData;
import ru.stqa.pft.addressbook.data.GroupData;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.generator.BaseGenerator;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class GroupCreationTests extends BaseTests {
    @DataProvider
    public Iterator<Object[]> generateValidGroups() {
        List<Object[]> list = new ArrayList<>();
        for (int i = 0; i < BaseGenerator.generateRandom(5); i++) {
            list.add(new Object[]{new GroupData()});
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loadValidGroupsXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/groups.xml"))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> loadValidGroupsJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/groups.json"))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
            }.getType());
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @BeforeMethod
    public void ensurePrecondition() {
        app.getNavigationHelper().gotoGroupPage();
    }

    @Test(dataProvider = "generateValidGroups")
    // Base test creation group
    public static void testGroupCreationVer1(GroupData group) {
        Set<GroupData> beforeGroupSet = app.getGroupHelper().getGroupSet();

        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitModification();

        app.getNavigationHelper().returnToGroupPage();

        // Check on the number of elements
        Assert.assertEquals(app.getGroupHelper().getGroupCount(), beforeGroupSet.size() + 1);

        Set<GroupData> afterGroupSet = app.getGroupHelper().getGroupSet();

        // Find the maximum Id and assign the created element
        for (GroupData g : afterGroupSet) {
            if (g.getGroupId() > group.getGroupId()) {
                group.setGroupId(g.getGroupId());
            }
        }
        //group.setGroupId(afterGroupSet.stream().mapToInt((g) -> g.getGroupId()).max().getAsInt());

        // Attaching a created element
        beforeGroupSet.add(group);

        // Check elements for identity verification
        Assert.assertEquals(beforeGroupSet, afterGroupSet);
    }

    @Test(dataProvider = "loadValidGroupsXml")
    // Test creation group fluent implementation
    public static void testGroupCreationVer2(GroupData group) {
        Groups beforeGroupSet = app.getGroupHelper().all();

        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitModification();

        app.getNavigationHelper().returnToGroupPage();

        // Check on the number of elements
        assertThat(app.getGroupHelper().getGroupCount(), equalTo(beforeGroupSet.size() + 1));

        Groups afterGroupSet = app.getGroupHelper().all();

        // Check elements for identity verification
        assertThat(afterGroupSet, equalTo(beforeGroupSet.withAdded(group.setGroupId(afterGroupSet.stream().mapToInt((g) -> g.getGroupId()).max().getAsInt()))));
    }

    @Test
    // Test creation group with the unacceptable title
    public static void testBadGroupCreationVer1() {
        Set<GroupData> beforeGroupSet = app.getGroupHelper().getGroupSet();

        GroupData group = new GroupData().setGroupName("BadName'");

        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitModification();

        app.getNavigationHelper().returnToGroupPage();

        // Check on the number of elements
        Assert.assertEquals(app.getGroupHelper().getGroupCount(), beforeGroupSet.size());

        Set<GroupData> afterGroupSet = app.getGroupHelper().getGroupSet();

        // Check elements for identity verification
        Assert.assertEquals(beforeGroupSet, afterGroupSet);
    }
}
