package controllers;

import entity.Student;
import exceptions.CSVWriterException;
import exceptions.ResourceReaderException;
import ui.AuthorizationView;
import utils.ApplicationContext;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthorizationController implements ActionListener {

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

    public void setAuthorizationView(AuthorizationView authorizationView) {
        this.authorizationView = authorizationView;
    }
}