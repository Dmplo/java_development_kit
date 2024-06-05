package JavaDevelopmentKit.Homework.Lesson_2;

import JavaDevelopmentKit.Homework.Lesson_2.client.ClientController;
import JavaDevelopmentKit.Homework.Lesson_2.client.ClientGUI;
import JavaDevelopmentKit.Homework.Lesson_2.client.ClientInfo;
import JavaDevelopmentKit.Homework.Lesson_2.logger.Logger;
import JavaDevelopmentKit.Homework.Lesson_2.server.ServerController;
import JavaDevelopmentKit.Homework.Lesson_2.server.ServerGUI;

public class Main {
    public static void main(String[] args) {
        ServerGUI serverGUI = new ServerGUI();
        ServerController serverController = new ServerController(new Logger(getPath()), serverGUI);
        serverGUI.setServerController(serverController);

        ClientGUI guiOne = new ClientGUI();
        ClientController clientControllerOne = new ClientController(serverController, new ClientInfo(), guiOne);
        guiOne.setClient(clientControllerOne);

        ClientGUI guiTwo = new ClientGUI();
        ClientController clientControllerTwo = new ClientController(serverController, new ClientInfo(), guiTwo);
        guiTwo.setClient(clientControllerTwo);
    }

    private static String getPath() {
        String pathProject = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        String path = pathProject + separator + "src";
        String fileLogName = separator.concat("log.txt");
        return path.concat(fileLogName);
    }
}