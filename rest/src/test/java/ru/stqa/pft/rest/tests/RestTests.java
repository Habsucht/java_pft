package ru.stqa.pft.rest.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.rest.RestHelper;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests {

    @Test
    public void testCreateIssueVer1() throws IOException {
        Set<Issue> oldIssues = RestHelper.getIssues();
        Issue newIssue = new Issue().setSubject("Test issue").setDescription("New test issue");

        int issueId = RestHelper.createIssue(newIssue);

        Set<Issue> newIssues = RestHelper.getIssues();

        oldIssues.add(newIssue.setId(issueId));

        assertEquals(newIssues, oldIssues);
    }

    @Test
    public void testForCheckingIssue() throws IOException {
        Set<Issue> issues = RestHelper.getIssues();

        RestHelper.skipIfNotFixed(randomIssue(issues));
        System.out.println("Somebody test");
    }

    private int randomIssue(Set<Issue> issues) {
        for (Issue issue : issues) {
            System.out.println(issue.toString());
        }

        Issue issue = issues.iterator().next();
        System.out.println(issue.toString());

        return issue.getId();
    }
}
