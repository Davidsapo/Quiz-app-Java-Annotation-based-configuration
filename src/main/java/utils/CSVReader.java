package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {

    private String csv;
    private List<String> titles;
    private List<String[]> parsedRows;

    public void parse() {
        parsedRows = new ArrayList<>();
        String[] rows = csv.split("\n");
        for (int i = 0; i < rows.length; i++) {
            String[] elements = rows[i].strip().split(",");
            if (i == 0) {
                titles = Arrays.asList(elements);
                continue;
            }
            parsedRows.add(elements);
        }
    }

    public void setCsv(String csv) {
        this.csv = csv;
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