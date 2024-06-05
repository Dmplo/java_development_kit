package JavaDevelopmentKit.Homework.Lesson_2.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class ClientGUI extends JFrame implements IClientView {
    private static final int WIDTH = 555;
    private static final int HEIGHT = 400;
    private JButton btnLogin, btnSend;
    private JTextArea centerField;
    private JPanel topPanel;
    private JTextField ip, port, field, userName;
    private JPasswordField password;
    ClientController client;

    public ClientGUI() {
        setting();
        renderElements();
        setVisible(true);
    }

    private void setting() {
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat client");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        calcPosition();
    }

    private void renderElements() {
        field = new JTextField();
        btnSend = new JButton("send");
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.connect();
            }
        });

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
        });
        add(createBottomPanel(), BorderLayout.SOUTH);
        add(createTopPanel(), BorderLayout.NORTH);
        createCenterPanel();
        add(centerField);
    }

    public void setClient(ClientController client) {
        this.client = client;
        setValues();
    }

    private void setValues() {
        userName.setText(client.getName());
        ip.setText(client.getIp());
        port.setText(client.getPort());
        password.setText(client.getPassword());
    }

    public void disconnect() {
        centerField.append("Вы были отключены от сервера!\n");
        topPanel.setVisible(true);
    }

    @Override
    public void connect() {
        centerField.setText("Вы успешно подключились!\n");
        topPanel.setVisible(false);
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            client.disconnectById();
        }
    }

    private void calcPosition() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - new Random().nextInt(getWidth(), getWidth() * 2);
        int y = new Random().nextInt(getHeight());
        this.setLocation(x, y);
    }

    private void sendMessage() {
        String message = field.getText();
        if (message.length() > 0) {
            client.sendMessage(message);
            field.setText("");
        }
    }

    private JPanel createTopPanel() {
        topPanel = new JPanel(new GridLayout(2, 3));
        userName = new JTextField("");
        ip = new JTextField("");
        port = new JTextField("");
        password = new JPasswordField("");
        topPanel.add(ip);
        topPanel.add(port);
        topPanel.add(new JLabel());
        topPanel.add(userName);
        topPanel.add(password);
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

    private void createCenterPanel() {
        centerField = new JTextArea();
        centerField.setEditable(false);
    }

    @Override
    public void renderResponse(String text) {
        centerField.append(text);
    }
}

