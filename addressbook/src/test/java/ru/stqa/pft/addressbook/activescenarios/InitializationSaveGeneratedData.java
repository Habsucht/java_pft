/**
 *  A class to save data
 */
package ru.stqa.pft.addressbook.activescenarios;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.data.ContactData;
import ru.stqa.pft.addressbook.data.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class InitializationSaveGeneratedData {

    @Parameter(names = "-b", description = "The number of generated GroupData")
    private int numberGroups;

    @Parameter(names = "-c", description = "The number of generated ContactData")
    private int numberContacts;

    @Parameter(names = "-f", description = "Target directory")
    private String directory;

    @Parameter(names = "-d", description = "Format save data")
    private String format;

    private static String savedGroups;
    private static String savedContacts;

    public static String getSavedGroups() {
        return savedGroups;
    }

    private void setSavedGroups(String savedGroups) {
        this.savedGroups = savedGroups;
    }

    public static String getSavedContacts() {
        return savedContacts;
    }

    private void setSavedContacts(String savedContacts) {
        this.savedContacts = savedContacts;
    }

    public static void main(String[] args) throws IOException {
        InitializationSaveGeneratedData initializationSaveGeneratedData = new InitializationSaveGeneratedData();
        JCommander jCommander = new JCommander(initializationSaveGeneratedData);

        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        initializationSaveGeneratedData.run();
    }

    void run() throws IOException {
        if (directory == null) { directory = "addressbook/src/test/resources/"; }
        if (format == null) { format = "json"; }

        List<Object> listGroups = new ArrayList<>();
        List<Object> listContacts = new ArrayList<>();

        for (int i = 0; i < numberGroups; i++) { listGroups.add(new GroupData()); }
        for (int i = 0; i < numberContacts; i++) { listContacts.add(new ContactData()); }

        save(listGroups, directory + "/groups." + format);
        setSavedGroups(directory + "/groups." + format);

        save(listContacts, directory + "/contacts." + format);
        setSavedContacts(directory + "/contacts." + format);
    }

    private void save(List<Object> list, String pathToTheFile) throws IOException {
        File file = new File(pathToTheFile);
        if (!file.exists()) { file.createNewFile(); }

        try (Writer writer = new FileWriter(pathToTheFile)) {
            switch (format) {
                case "Csv":
                    writer.write(convertAsCsv(list));
                    break;
                case "xml":
                    writer.write(convertAsXml(list));
                    break;
                case "json":
                    writer.write(convertAsJson(list));
                    break;
                default:
                    System.out.println("Unrecognized format " + format);
                    break;
            }
        }
    }

    private String convertAsCsv(List<Object> list) {
        return list.toString();
    }

    private String convertAsXml(List<Object> list) {
        return new XStream().toXML(list);
    }

    private String convertAsJson(List<Object> list) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(list);
    }
}
