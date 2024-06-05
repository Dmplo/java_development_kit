package JavaDevelopmentKit.Homework.Lesson_2.logger;



import JavaDevelopmentKit.Homework.Lesson_2.exception.ErrorWriteFileException;

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

    public String readFile() {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
        } catch (IOException e) {
            stringBuilder.append("Aрхив сообщений не найден\n");
        }
        return String.valueOf(stringBuilder);
    }

}
