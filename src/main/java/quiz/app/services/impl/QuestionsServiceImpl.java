package quiz.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import quiz.app.entity.Question;
import quiz.app.exceptions.CSVWriterException;
import quiz.app.services.interfaces.QuestionsService;
import quiz.app.utils.CSVReader;

import java.util.ArrayList;
import java.util.List;

@Component("questionsService")
public class QuestionsServiceImpl implements QuestionsService {

    @Autowired
    private CSVReader questionsCSVReader;

    @Override
    public List<Question> getQuestions() throws CSVWriterException {
        ArrayList<Question> questions = new ArrayList<>();
        questionsCSVReader.parse();
        CSVReader.ResultSet resultSet = questionsCSVReader.getResult();
        while (resultSet.next()) {
            questions.add(new Question(resultSet.get("question"), Integer.parseInt(resultSet.get("correct")),
                    resultSet.get("answer1"), resultSet.get("answer2"), resultSet.get("answer3")));
        }
        return questions;
    }
}
