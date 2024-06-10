package JavaDevelopmentKit.Homework.Lesson_4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Catalog {
   private List<Worker> workers;

    public Catalog() {
        this.workers = new ArrayList<>();
    }

    public void add(Worker worker) {
        workers.add(worker);
    }

    public List<Worker> show() {
        return workers;
    }

    public List<Worker> findByExp(int exp) {
       return workers.stream().filter(n -> n.getExperience() == exp).collect(Collectors.toList());
    }
    public List<Worker> findPhoneByName(String name) {
       return workers.stream().filter(n -> n.getName().equals(name)).collect(Collectors.toList());
    }
    public List<Worker> findByPersonalNumber(int personalNumber) {
       return workers.stream().filter(n -> n.getPersonnelNumber() == personalNumber).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "workers=" + workers +
                '}';
    }
}
