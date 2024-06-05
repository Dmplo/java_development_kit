package JavaDevelopmentKit.Homework.Lesson_2.logger;

import JavaDevelopmentKit.Homework.Lesson_2.exception.ErrorWriteFileException;

import javax.swing.*;

public interface ILogger {

    void closeLog() throws ErrorWriteFileException;

    void log(String message) throws ErrorWriteFileException;

    void createLog() throws ErrorWriteFileException;
    String readFile();

}
