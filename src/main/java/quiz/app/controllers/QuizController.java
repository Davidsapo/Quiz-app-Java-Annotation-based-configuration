package quiz.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import quiz.app.entity.*;
import quiz.app.exceptions.*;
import quiz.app.services.interfaces.*;
import quiz.app.ui.*;
import quiz.app.utils.ApplicationContext;

import java.awt.event.*;
import java.util.List;

@Component
public class QuizController implements ActionListener {

    @Autowired
    private QuizView quizView;
    @Autowired
    private StudentService studentsService;
    @Autowired
    private QuestionsService questionsService;

    private List<Question> questions;
    private Student currentStudent;
    private int currentQuestion;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == quizView.getExitButton())
                exit();
            else if (e.getSource() == quizView.getSubmitButton())
                submit();
            else if (e.getSource() == quizView.getBoardButton())
                showLeaderboard();
        } catch (CSVWriterException | ResourceReaderException exception) {
            quizView.showMessage(exception.getMessage());
        }
    }

    public void init(Student student) throws ResourceReaderException, CSVWriterException {
        currentStudent = student;
        questions = questionsService.getQuestions();
        currentQuestion = -1;
        quizView.setVisible(true);
        if (questions.size() < 1) {
            quizView.showMessage("Question file in empty!");
            return;
        }
        quizView.getSubmitButton().setEnabled(true);
        showQuestion();
    }

    private void exit() {
        quizView.dispose();
        ApplicationContext.getBean("authorizationView", AuthorizationView.class).setVisible(true);
    }

    private void submit() throws CSVWriterException {
        int selectedAnswer = quizView.getSelectedIndex();
        if (selectedAnswer == 0) {
            quizView.showMessage("Select answer!");
            return;
        } else if (selectedAnswer == questions.get(currentQuestion).getCorrectAnswer()) {
            currentStudent.score++;
            quizView.showMessage("Correct!");
        } else {
            quizView.showMessage("Wrong answer!");
        }
        showQuestion();
    }

    private void showQuestion() throws CSVWriterException {
        if (currentQuestion < questions.size() - 1) {
            currentQuestion++;
            Question question = questions.get(currentQuestion);
            quizView.getQuestionLabel().setText(question.getQuestion());
            quizView.getjRadioButton1().setText(question.getAnswers()[0]);
            quizView.getjRadioButton2().setText(question.getAnswers()[1]);
            quizView.getjRadioButton3().setText(question.getAnswers()[2]);
        } else {
            quizView.getSubmitButton().setEnabled(false);
            quizView.showMessage("Score: " + currentStudent.score + "/" + questions.size());
            studentsService.writeStudent(currentStudent);
        }
    }

    private void showLeaderboard() throws CSVWriterException, ResourceReaderException {
        LeaderboardView leaderboardView = ApplicationContext.getBean("leaderboardView", LeaderboardView.class);
        leaderboardView.fillList(studentsService.getStudents().toArray());
        leaderboardView.setVisible(true);
    }
}