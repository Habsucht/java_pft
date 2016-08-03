package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.apache.http.client.fluent.Request.Get;
import static org.apache.http.client.fluent.Request.Post;

public class RestHelper {
    private static Executor getExecutor() {
        return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
    }

    public static Set<Issue> getIssues() throws IOException {
        String json = getExecutor()
                .execute(Get("http://demo.bugify.com/api/issues.json"))
                .returnContent().asString();

        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");

        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    private Set<Issue> getIssuesAssured() throws IOException {
        String json = RestAssured.get("http://demo.bugify.com/api/issues.json").asString();

        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");

        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    public static int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor()
                .execute(Post("http://demo.bugify.com/api/issues.json").bodyForm(
                        new BasicNameValuePair("subject", newIssue.getSubject()),
                        new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();

        JsonElement parsed = new JsonParser().parse(json);

        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    private int createIssueAssured(Issue newIssue) throws IOException {
        String json = RestAssured.given()
                .parameter("subject", newIssue.getSubject())
                .parameter("description", newIssue.getDescription())
                .post("http://demo.bugify.com/api/issues.json").asString();

        JsonElement parsed = new JsonParser().parse(json);

        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    public static void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private static boolean isIssueOpen(int issueId) throws IOException {
        String json = getExecutor()
                .execute(Request.Get("http://demo.bugify.com/api/issues/" + issueId + ".json?attachments=false&comments=false&followers=false&history=false"))
                .returnContent().asString();

        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");

        Set<Issue> fromJson = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
        String issueState = fromJson.iterator().next().getState();

        System.out.println(issueState);
        return issueState.equals("0") || issueState.equals("1") ;
    }
}
