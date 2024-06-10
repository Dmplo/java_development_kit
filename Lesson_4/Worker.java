package JavaDevelopmentKit.Homework.Lesson_4;

import JavaDevelopmentKit.Homework.Lesson_2.Names;

import java.util.Random;

public class Worker {
    private int personnelNumber;
    private int phone;
    private String name;
    private int experience;

    private Random rnd = new Random();

    public Worker() {
        this.personnelNumber = rnd.nextInt(111, 999);;
        this.phone = rnd.nextInt(9999, 999999);
        this.name = String.valueOf(Names.values()[rnd.nextInt(Names.values().length)]);
        this.experience = rnd.nextInt( 20);
    }

    public Worker(int personnelNumber, int phone, String name, int experience) {
        this.personnelNumber = personnelNumber;
        this.phone = phone;
        this.name = name;
        this.experience = experience;
    }

    public int getPersonnelNumber() {
        return personnelNumber;
    }

    public int getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    @Override
    public String toString() {
        return "\nWorker{" +
                "personnelNumber=" + personnelNumber +
                ", phone=" + phone +
                ", name='" + name + '\'' +
                ", experience=" + experience +
                "}";
    }
}
