package JavaDevelopmentKit.Homework.Lesson_2.exception;

import java.io.IOException;

public class ErrorWriteFileException extends IOException {
    String message;

    public ErrorWriteFileException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}