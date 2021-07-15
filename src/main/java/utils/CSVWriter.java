package utils;

import exceptions.CSVWriterException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {

    private File csv;

    public CSVWriter(File file) {
        csv = file;
    }

    public void write(String... elements) throws CSVWriterException {
        try {
            FileWriter fileWriter = new FileWriter(csv,true);
            fileWriter.write(String.join(",", elements) + "\n");
            fileWriter.close();
        } catch (IOException exception) {
            throw new CSVWriterException("Exception occurs while writing to " + csv.getName());
        }
    }
}
