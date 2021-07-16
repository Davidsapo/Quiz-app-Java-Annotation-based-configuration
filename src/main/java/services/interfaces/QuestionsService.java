package services.interfaces;

import entity.Question;
import exceptions.CSVWriterException;
import exceptions.ResourceReaderException;

import java.util.List;

public interface QuestionsService {

    List<Question> getQuestions() throws ResourceReaderException, CSVWriterException;
}
