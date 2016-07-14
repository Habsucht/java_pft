package ru.stqa.pft.addressbook.activescenarios;

import ru.stqa.pft.addressbook.data.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class InitializationSaveGeneratedData {
    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        saveGroups(count, file);
    }

    public static void saveGroups(int count, File file) throws IOException {
        Writer writer = new FileWriter(file);

        for (int i = 0; i < count; i++) {
            writer.write(new GroupData().toString()); }
        writer.close();
    }
}
