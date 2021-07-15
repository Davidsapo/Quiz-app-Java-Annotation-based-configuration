import exceptions.CSVReaderException;
import exceptions.CSVWriterException;
import exceptions.ResourceReaderException;
import utils.CSVReader;
import utils.CSVWriter;
import utils.ResourceReader;

public class Main {

    public static void main(String[] args) throws CSVReaderException, ResourceReaderException, CSVWriterException {
        CSVWriter writer = new CSVWriter(ResourceReader.getResourceFile("classpath:data/leaderboard.csv"));
        System.out.println(ResourceReader.getResourceFile("classpath:data/leaderboard.csv"));
        writer.write("David", "Sapozhik", "10");
        writer.write("David", "Sapozhik", "15");
        writer.write("David", "Sapozhik", "14");

        /*CSVReader reader = new CSVReader();
        reader.setCsv(ResourceReader.readFileToString("data/leaderboard.csv"));
        reader.parse();
        CSVReader.ResultSet resultSet = reader.getResult();
        while (resultSet.next())
            System.out.println(resultSet.get(2));*/
    }
}
