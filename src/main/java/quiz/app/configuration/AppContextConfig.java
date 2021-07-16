package quiz.app.configuration;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import quiz.app.utils.CSVReader;

@Configuration
@PropertySource("classpath:properties/CSVFilesLocation.properties")
@ComponentScan("quiz.app")
public class AppContextConfig {

    @Value("${questions}")
    private String questionsFilePath;
    @Value("${students}")
    private String leaderBoardFilePath;

    @Bean
    public CSVReader studentsCSVReader() {
        return new CSVReader(leaderBoardFilePath);
    }

    @Bean
    public CSVReader questionsCSVReader() {
        return new CSVReader(questionsFilePath);
    }
}
