package utils;

import exceptions.CSVWriterException;
import exceptions.ResourceReaderException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {

    private final String csvFile;
    private List<String> titles;
    private List<String[]> parsedRows;

    public CSVReader(String csvFile) {
        this.csvFile = csvFile;
    }

    public void parse() throws CSVWriterException {
        try {
            String csv = ResourceReader.readFileToString(csvFile);
            parsedRows = new ArrayList<>();
            String[] rows = csv.split("\n");
            for (int i = 0; i < rows.length; i++) {
                String[] elements = rows[i].trim().split(",");
                if (i == 0) {
                    titles = Arrays.asList(elements);
                    continue;
                }
                parsedRows.add(elements);
            }
        } catch (ResourceReaderException e) {
            throw new CSVWriterException("Exception occurs while reading csv file " + csvFile);
        }
    }

    public ResultSet getResult() {
        return new ResultSet();
    }

    public class ResultSet {
        int pointer = -1;

        public boolean next() {
            pointer++;
            return pointer < parsedRows.size();
        }

        public String get(int index) {
            return parsedRows.get(pointer)[index];
        }

        public String get(String name) {
            return parsedRows.get(pointer)[titles.indexOf(name)];
        }
    }
}