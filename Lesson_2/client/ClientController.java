package JavaDevelopmentKit.Homework.Lesson_2.client;

import JavaDevelopmentKit.Homework.Lesson_2.server.ServerController;

import javax.swing.*;

public class ClientController extends JFrame {
    private static int id = 1;
    private int clientID;
    private ServerController serverController;
    private ClientInfo clientInfo;
    private IClientView clientView;
    private boolean connection;

    public ClientController(ServerController serverController, ClientInfo clientInfo, IClientView clientView) {
        this.serverController = serverController;
        this.clientInfo = clientInfo;
        this.clientView = clientView;
        clientID = id++;
        connection = false;
    }

    public void showResponse(String res) {
        if (connection) {
            renderResponse(res);
        }
    }

    public void sendMessage(String message) {
        if (!connection) {
            renderResponse("Ошибка. Требуется подключение к серверу!\n");
            return;
        }
        serverController.sendMessage(this, message);
    }

    public void disconnect() {
        if (connection) {
            clientView.disconnect();
            stopConnection();
        }
    }

    public void stopConnection() {
        connection = false;
    }

    public void connect() {
        if (serverController.connect(this)) {
            clientView.connect();
            if (serverController.getLog() != null) {
                renderResponse(serverController.getLog());
            }
            connection = true;
        } else {
            renderResponse("Подключение к серверу не удалось!\n");
        }
    }

    public void disconnectById() {
        serverController.disconnectById(this);
    }

    private void renderResponse(String text) {
        clientView.renderResponse(text);
    }

    public int getClientID() {
        return clientID;
    }

    public String getName() {
        return clientInfo.getName();
    }

    public String getIp() {
        return clientInfo.getIp();
    }

    public String getPort() {
        return clientInfo.getPort();
    }

    public String getPassword() {
        return clientInfo.getPassword();
    }
}

