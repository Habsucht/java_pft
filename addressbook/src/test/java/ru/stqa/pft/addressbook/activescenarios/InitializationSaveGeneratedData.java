package ru.stqa.pft.addressbook.activescenarios;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class InitializationSaveGeneratedData {

    @Parameter(names = "-c", description = "count")
    public int count;

    @Parameter(names = "-f", description = "file")
    public String file;

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

    private void run() throws IOException {
        save(new Object(), count, new File(file));
    }

    public static void save(Object object, int count, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (int i = 0; i < count; i++) {
            writer.write(object.toString());
        }
        writer.close();
    }
}
