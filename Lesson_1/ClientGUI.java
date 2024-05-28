package JavaDevelopmentKit.Homework.Lesson_1;

import JavaDevelopmentKit.Homework.Lesson_1.exceptions.ErrorWriteFileException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ClientGUI extends JFrame {
    private static final int WIDTH = 555;
    private static final int HEIGHT = 400;
    private static int id = 1;
    private int clientID;
    private JButton btnLogin, btnSend;
    private JTextArea centerField;
    private JTextField userName, field;
    private JPanel topPanel;
    private ServerWindow serverWindow;
    private boolean connection = false;

    public ClientGUI(ServerWindow serverWindow) {
        btnSend = new JButton("send");
        field = new JTextField();

        clientID = id++;
        this.serverWindow = serverWindow;
        setSize(WIDTH, HEIGHT);
        calcPosition();
        setTitle("Chat client");
        setResizable(false);
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!serverWindow.isState()) {
                    centerField.append("Подключение к серверу не удалось!\nСначала запустите сервер!\n");
                } else {
                    centerField.append("Вы успешно подключились!\n");
                    showOldMessages();
                    topPanel.setVisible(false);
                    connection = true;
                }
            }
        });

        serverWindow.btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (connection) {
                    centerField.append("Вы были отключены от сервера!\n");
                    topPanel.setVisible(true);
                    connection = false;
                }
            }
        });

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAction();
            }
        });


        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    saveAction();
                }
            }
        });


        serverWindow.getCenterField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (connection) {
                if (serverWindow.isState()) {
                    if (serverWindow.getSendId() != clientID) {
                        centerField.append(serverWindow.getSendMessage());
                    } else if (serverWindow.getSendId() == clientID && serverWindow.getErrorMessage() != null) {
                        centerField.append(serverWindow.getErrorMessage());
                        serverWindow.setErrorMessage(null);
                        serverWindow.setSendId(0);
                    }
                }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        add(createBottomPanel(), BorderLayout.SOUTH);
        add(createTopPanel(), BorderLayout.NORTH);
        centerField = new JTextArea();
        centerField.setEditable(false);
        add(centerField);
        setVisible(true);
    }

    private void calcPosition() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - this.getWidth();
        int y = 0;
        this.setLocation(x, y);
    }

    private void saveAction() {
        if (!serverWindow.isState() || !connection) {
            centerField.append("Ошибка. Требуется подключение к серверу!\n Нажмите кнопку Login\n");
        } else {
            serverWindow.setSendMessage("");
            serverWindow.setSendId(0);
            String message = field.getText();
            if (message.length() > 0) {
                serverWindow.saveMessage(message, userName.getText(), clientID);
                centerField.append(serverWindow.getSendMessage());
                field.setText("");
            }
        }
    }

    void showOldMessages() {
        try {
            serverWindow.fillMessages(centerField);
            centerField.append("\n");
        } catch (ErrorWriteFileException e) {
            centerField.append(e.getMessage());
        }
    }

    private JPanel createTopPanel() {
        userName = new JTextField("Petya");
        topPanel = new JPanel(new GridLayout(2, 3));
        topPanel.add(new JTextField("127.0.0.1"));
        topPanel.add(new JTextField("8189"));
        topPanel.add(new JLabel());
        topPanel.add(userName);
        topPanel.add(new JTextField("*****"));
        topPanel.add(btnLogin);
        return topPanel;
    }

    private JPanel createBottomPanel() {
        JPanel jPanel = new JPanel();
        field.setColumns(45);
        jPanel.add(field);
        jPanel.add(btnSend);
        return jPanel;
    }
}

