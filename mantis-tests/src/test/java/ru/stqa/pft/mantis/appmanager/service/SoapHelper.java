package ru.stqa.pft.mantis.appmanager.service;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.data.UserData;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class SoapHelper {

    private ApplicationManager app;

    public SoapHelper(ApplicationManager app) {
        this.app = app;
    }

    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator().getMantisConnectPort(new URL(app.webBaseUrl + "/api/soap/mantisconnect.php"));
    }

    public ProjectData[] getMantisProjects() throws RemoteException, MalformedURLException, ServiceException {
        return getMantisConnect().mc_projects_get_user_accessible(app.admin.getLogin(), app.admin.getPassword());
    }

    public String[] getCategoriesProject(UserData user, BigInteger id) throws MalformedURLException, ServiceException, RemoteException {
        return getMantisConnect().mc_project_get_categories(user.getLogin(), user.getPassword(), id);
    }

    public BigInteger addIssue(UserData user, IssueData issue) throws MalformedURLException, ServiceException, RemoteException {
        return getMantisConnect().mc_issue_add(user.getLogin(), user.getPassword(), issue);
    }

    public IssueData viewIssue(UserData user, BigInteger issueId) throws MalformedURLException, ServiceException, RemoteException {
        return getMantisConnect().mc_issue_get(user.getLogin(), user.getPassword(), issueId);
    }

    public boolean isIssueOpen(BigInteger issueId) throws MalformedURLException, ServiceException, RemoteException {
        IssueData issue = getMantisConnect().mc_issue_get(app.admin.getLogin(), app.admin.getPassword(), issueId);
        System.out.println(issue.getId() + " " + issue.getSummary() + " " + issue.getStatus().getName());
        return !((issue.getStatus().getName().equals("resolved")) || (issue.getStatus().getName().equals("closed")));
    }
}
