package quiz.app.services.interfaces;

import quiz.app.entity.Question;
import quiz.app.exceptions.CSVWriterException;
import quiz.app.exceptions.ResourceReaderException;

import java.util.List;

public interface QuestionsService {

    List<Question> getQuestions() throws ResourceReaderException, CSVWriterException;
}
