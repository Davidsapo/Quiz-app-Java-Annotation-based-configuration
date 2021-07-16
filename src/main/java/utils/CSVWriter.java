package utils;

import exceptions.CSVWriterException;
import exceptions.ResourceReaderException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {

    private final String csvFile;

    public CSVWriter(String csvFile) {
        this.csvFile = csvFile;
    }

    public void write(String... elements) throws CSVWriterException {
        try {
            FileWriter fileWriter = new FileWriter(ResourceReader.getResourceFile(csvFile), true);
            fileWriter.write("\n" + String.join(",", elements));
            fileWriter.close();
        } catch (IOException | ResourceReaderException exception) {
            throw new CSVWriterException("Exception occurs while writing to " + csvFile);
        }
    }
}
