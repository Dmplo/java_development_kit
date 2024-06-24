package JavaDevelopmentKit.Homework.Lesson_5;

import java.util.Random;

public class Philosopher extends Thread {
    private String name;
    private Fork leftFork;
    private Fork rightFork;
    private Random rnd;

    private int countMeals;

    enum State {
        THINK, EAT
    }

    private State state;

    public Philosopher(String name, Fork leftFork, Fork rightFork) {
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.state = State.THINK;
        this.countMeals = 0;
        this.rnd = new Random();
    }

    private void eat() throws InterruptedException {
        if (checkForks()) {
            state = State.EAT;
            countMeals++;
            System.out.println(name + " начал кушать");
            sleep(rnd.nextLong(300, 700));
            System.out.println(name + " закончил кушать");
            System.out.println(name + " покушал: " + countMeals + " раз(а)");
            releaseForks();
        } else {
            sleep(500);
        }
    }

    private void releaseForks() {
        leftFork.setState(Fork.State.FREE);
        rightFork.setState(Fork.State.FREE);
    }

    private Boolean checkForks() {
        if (leftFork.checkFork() && rightFork.checkFork()) {
            leftFork.setState(Fork.State.BUSY);
            rightFork.setState(Fork.State.BUSY);
            return true;
        }
        return false;
    }

    private void think() throws InterruptedException {
        state = State.THINK;
        sleep(2000);
    }

    @Override
    public void run() {
        while (countMeals < 3) {
            try {
                if (state == State.THINK) {
                    eat();
                }
                think();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
