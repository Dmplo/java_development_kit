package JavaDevelopmentKit.Homework.Lesson_1;

import JavaDevelopmentKit.Homework.Lesson_1.Logger.ILogger;
import JavaDevelopmentKit.Homework.Lesson_1.exceptions.ErrorWriteFileException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerWindow extends JFrame {
    private static final int WIDTH = 555;
    private static final int HEIGHT = 400;

    public JButton btnStart, btnStop;
    private JTextArea centerField;
    private String sendMessage;
    private String errorMessage = null;
    private int sendId;

   private boolean state = false;
    private ILogger logger;

    ServerWindow(ILogger logger) {
        this.logger = logger;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        setTitle("Chat server");
        setResizable(false);
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (state) {
                    state = false;
                    centerField.append("Север остановлен!\n");
            }
        }
    });

        btnStart.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed (ActionEvent e){
            if (state) {
                centerField.append("Сервер уже запущен!\n");
            } else {
                centerField.append("Запуск сервера!\n");
                startServer();
            }
        }
    });

    JPanel panBottom = new JPanel(new GridLayout(1, 2));
        panBottom.add(btnStart);
        panBottom.add(btnStop);

    add(panBottom, BorderLayout.SOUTH);

    centerField =new

    JTextArea();
        centerField.setEditable(false);

    add(centerField);

    setVisible(true);

}

    public void fillMessages(JTextArea field) throws ErrorWriteFileException {
        logger.readFile(field);
    }

    public void saveMessage(String message, String name, int id) {
        String msg = createMsgText(name, message);
        sendId = id;
        try {
            logger.createLog();
            logger.log(msg);
            logger.closeLog();
            sendMessage = msg;
            centerField.append(msg);
        } catch (ErrorWriteFileException e) {
            errorMessage = e.getMessage() + msg;
            centerField.append(errorMessage);
        }
    }

    private String createMsgText(String name, String message) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String timeStamp = dateFormat.format(new Date());
        return timeStamp + " - " + name + ": " + message + "\n";
    }

    public JTextArea getCenterField() {
        return centerField;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(String sendMessage) {
        this.sendMessage = sendMessage;
    }

    public int getSendId() {
        return sendId;
    }

    public void setSendId(int sendId) {
        this.sendId = sendId;
    }

    void startServer() {
        state = true;
    }

    public boolean isState() {
        return state;
    }
}
