package ru.stqa.pft.mantis.tests.service;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.tests.BaseTests;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class SoapTests extends BaseTests{

    @Test
    public void testGetProject() throws MalformedURLException, ServiceException, RemoteException {
        //System.setProperty("http.proxyHost", "localhost");
        //System.setProperty("http.proxyPort", "8888");

        ProjectData[] projects = app.getSoapHelper().getMantisProjects();

        // Print all projects
        System.out.println(projects.length);
        for (ProjectData project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        ProjectData[] projects = app.getSoapHelper().getMantisProjects();
        String[] categories = app.getSoapHelper().getCategoriesProject(app.admin, projects[0].getId());

        IssueData newIssue = new IssueData();
        newIssue.setSummary("test1");
        newIssue.setDescription("test1");
        newIssue.setProject(new ObjectRef(projects[0].getId(), projects[0].getName()));
        newIssue.setCategory(categories[0]);

        BigInteger createIssueId = app.getSoapHelper().addIssue(app.admin, newIssue);

        IssueData loadNewIssue = app.getSoapHelper().viewIssue(app.admin, createIssueId);

        System.out.println(newIssue.toString());
        System.out.println(loadNewIssue.toString());

        app.getSoapHelper().isIssueOpen(createIssueId);

        app.getSoapHelper().isIssueOpen(BigInteger.valueOf(0000001));
        app.getSoapHelper().isIssueOpen(BigInteger.valueOf(0000002));
        app.getSoapHelper().isIssueOpen(BigInteger.valueOf(0000003));
    }
}
