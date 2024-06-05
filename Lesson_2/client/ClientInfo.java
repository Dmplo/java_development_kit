package JavaDevelopmentKit.Homework.Lesson_2.client;

import JavaDevelopmentKit.Homework.Lesson_2.Names;

import java.util.Random;

public class ClientInfo {
    private String name;
    private String ip;
    private String port;
    private String password;


    public ClientInfo() {
        this.name = String.valueOf(Names.values()[new Random().nextInt(Names.values().length)]);;
        this.ip = "127.0.0.1";
        this.port = "8189";
        this.password = "1234";
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    public String getPassword() {
        return password;
    }
}
