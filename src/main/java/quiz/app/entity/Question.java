package quiz.app.entity;

import java.util.Arrays;

public class Question {

    private final String question;
    private final String[] answers;
    private final int correctAnswer;

    public Question(String question, int correctAnswer, String ...answers) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", answers=" + Arrays.toString(answers) +
                ", correctAnswer=" + correctAnswer +
                '}';
    }
}
