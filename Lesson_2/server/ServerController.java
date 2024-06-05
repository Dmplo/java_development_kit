package JavaDevelopmentKit.Homework.Lesson_2.server;

import JavaDevelopmentKit.Homework.Lesson_2.client.ClientController;
import JavaDevelopmentKit.Homework.Lesson_2.exception.ErrorWriteFileException;
import JavaDevelopmentKit.Homework.Lesson_2.logger.ILogger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ServerController {
    private Map<Integer, ClientController> clients;
    private boolean state;
    private ILogger logger;
    private IServerView server;

    public ServerController(ILogger logger, IServerView server) {
        this.logger = logger;
        this.server = server;
        clients = new HashMap<>();
        state = false;
    }

    private void disconnectAllClients() {
        for (ClientController client : clients.values()) {
            client.disconnect();
            renderMessage(String.format("Отключение пользователя %s\n", client.getName()));
        }
        clients.clear();
    }

    public void stopServer() {
        if (state) {
            state = false;
            disconnectAllClients();
            renderClientsCount("Север остановлен!\n");
            renderMessage("Север остановлен!\n");
        }
    }

    public void disconnectById(ClientController client) {
        if (findClient(client.getClientID()) != null) {
            clients.remove(client.getClientID());
            client.stopConnection();
            showInfo(client.getName(), "отключился");
        }
    }

    private ClientController findClient(int id) {
        return clients.get(id);
    }

    public void startServer() {
        if (state) {
            renderMessage("Сервер уже запущен!\n");
        } else {
            renderClientsCount("Подключенных клиентов: " + clients.size());
            renderMessage("Запуск сервера!\n");
            state = true;
        }
    }

    public boolean connect(ClientController client) {
        if (state && findClient(client.getClientID()) == null) {
            clients.put(client.getClientID(), client);
            showInfo(client.getName(), "подключился");
            return true;
        }
        return false;
    }

    private void showInfo(String name, String action) {
        renderMessage(String.format("Пользователь %s %s \n", name, action));
        renderClientsCount("Подключенных клиентов: " + clients.size());
    }

    public String getLog() {
       return logger.readFile();
    }

    public void sendMessage(ClientController client, String message) {
        String msg = createMsgText(client.getName(), message);
        try {
            logger.createLog();
            logger.log(msg);
            logger.closeLog();
            renderMessage(msg);
            getResponse(msg);
        } catch (ErrorWriteFileException e) {
            String error = e.getMessage() + msg;
            renderMessage(String.format(error));
            if (findClient(client.getClientID()) != null) {
                client.showResponse(error);
            }
        }
    }

    public void renderMessage(String text) {
        server.renderMessage(text);
    }
    public void renderClientsCount(String text) {
        server.renderClientsCount(text);
    }

    private void getResponse(String message) {
        for (ClientController client : clients.values()) {
            client.showResponse(message);
        }
    }

    private String createMsgText(String name, String message) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String timeStamp = dateFormat.format(new Date());
        return timeStamp + " - " + name + ": " + message + "\n";
    }
}
