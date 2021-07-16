package quiz.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import quiz.app.entity.Student;
import quiz.app.exceptions.CSVWriterException;
import quiz.app.exceptions.ResourceReaderException;
import quiz.app.ui.AuthorizationView;
import quiz.app.utils.ApplicationContext;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class AuthorizationController implements ActionListener {

    @Autowired
    private AuthorizationView authorizationView;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == authorizationView.getOkButton())
            okButtonActionPerformed();
    }

    private void okButtonActionPerformed() {
        String name = authorizationView.getNameField().getText();
        String surname = authorizationView.getSurnameField().getText();
        if (name.isEmpty() || surname.isEmpty()) {
            authorizationView.showMessage("Fields can not be empty!");
            return;
        }
        Student student = new Student(name, surname);
        authorizationView.dispose();
        try {
            ApplicationContext.getBean("quizController", QuizController.class).init(student);
        } catch (ResourceReaderException | CSVWriterException e) {
            authorizationView.showMessage(e.getMessage());
        }
    }
}