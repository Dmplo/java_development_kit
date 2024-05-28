package JavaDevelopmentKit.Homework.Lesson_1;

import JavaDevelopmentKit.Homework.Lesson_1.Logger.Logger;

public class Main {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow(new Logger(getPath()));
        new ClientGUI(serverWindow);
        new ClientGUI(serverWindow);
    }

    private static String getPath() {
        String pathProject = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        String path = pathProject + separator + "src";
        String fileLogName = separator.concat("log.txt");
        return path.concat(fileLogName);
    }
}