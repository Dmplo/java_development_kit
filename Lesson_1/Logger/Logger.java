package JavaDevelopmentKit.Homework.Lesson_1.Logger;


import JavaDevelopmentKit.Homework.Lesson_1.exceptions.ErrorWriteFileException;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Logger implements ILogger {
    String path;

    private static FileWriter fileWriter;

    public Logger(String path) {
        this.path = path;
    }

    public void createLog() throws ErrorWriteFileException {
        try {
            fileWriter = new FileWriter(path, true);
        } catch (IOException e) {
            throw new ErrorWriteFileException("Ошибка открытия БД при попытке записи сообщения: ");
        }
    }

    public void log(String message) throws ErrorWriteFileException {
        try {
            fileWriter.write(message);
        } catch (IOException e) {
            throw new ErrorWriteFileException("Ошибка, не удалось обработать сообщение: ");
        }
    }

    public void closeLog() throws ErrorWriteFileException {
        try {
            fileWriter.close();
        } catch (IOException e) {
            throw new ErrorWriteFileException("Ошибка в БД при попытке записи сообщения: ");
        }
    }

    public void readFile(JTextArea field) throws ErrorWriteFileException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                field.append(line + "\n");
            }
        } catch (IOException e) {
            throw new ErrorWriteFileException("\nAрхив сообщений не найден\n");
        }
    }

}
