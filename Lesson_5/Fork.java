package JavaDevelopmentKit.Homework.Lesson_5;

public class Fork {
    private State state;

    public Fork() {
        this.state = State.FREE;
    }

    public State getState() {
        return state;
    }

    public synchronized boolean checkFork() {
        return state == State.FREE;
    }

    public void setState(State state) {
        this.state = state;
    }

    enum State {
        FREE, BUSY
    }

}
