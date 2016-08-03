package ru.stqa.pft.mantis.appmanager.service;

import org.apache.commons.net.ftp.FTPClient;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FtpSessionHelper {

    private final ApplicationManager app;
    private FTPClient ftp;

    public FtpSessionHelper(ApplicationManager app) {
        this.app = app;
        ftp = new FTPClient();
    }

    public void upload(File file, String target, String backup) throws IOException {
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        ftp.deleteFile(backup);
        ftp.rename(target, backup);
        ftp.enterLocalPassiveMode();
        ftp.storeFile(target, new FileInputStream(file));
        ftp.disconnect();
    }

    public void restore(String backup, String target) throws IOException {
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        ftp.deleteFile(target);
        ftp.rename(backup, target);
        ftp.disconnect();
    }
}