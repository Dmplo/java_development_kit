package JavaDevelopmentKit.Homework.Lesson_1.Logger;

import JavaDevelopmentKit.Homework.Lesson_1.exceptions.ErrorWriteFileException;

import javax.swing.*;

public interface ILogger {

    void closeLog() throws ErrorWriteFileException;

    void log(String message) throws ErrorWriteFileException;

    void createLog() throws ErrorWriteFileException;
    void readFile(JTextArea field) throws ErrorWriteFileException;

}
