package quiz.app.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import quiz.app.exceptions.CSVWriterException;
import quiz.app.exceptions.ResourceReaderException;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class CSVWriter {

    @Value("${students}")
    private String csvFile;

    public CSVWriter() {
    }

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
