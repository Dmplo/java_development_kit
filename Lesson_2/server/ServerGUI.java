package JavaDevelopmentKit.Homework.Lesson_2.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JFrame implements IServerView {
    private static final int WIDTH = 555;
    private static final int HEIGHT = 400;
    public JButton btnStart, btnStop;
    private JTextArea centerField;
    private Label clientsCountLabel;
    ServerController serverController;

    public ServerGUI() {
        setting();
        renderElements();
        setVisible(true);
    }

    private void renderElements() {
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverController.stopServer();
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverController.startServer();
            }
        });
        add(createTopPanel(), BorderLayout.SOUTH);
        centerField = new JTextArea();
        centerField.setEditable(false);
        add(centerField);
    }
    private void setting() {
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Chat server");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel createTopPanel() {
        JPanel panBottom = new JPanel(new GridLayout(2, 2));
        clientsCountLabel = new Label("Сервер остановлен");
        panBottom.add(btnStart);
        panBottom.add(btnStop);
        panBottom.add(clientsCountLabel);
        return panBottom;
    }

    @Override
    public void renderMessage(String text) {
        centerField.append(text);
    }

    @Override
    public void renderClientsCount(String text) {
        clientsCountLabel.setText(text);
    }

    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }
}
