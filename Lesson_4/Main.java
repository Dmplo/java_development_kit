package JavaDevelopmentKit.Homework.Lesson_4;

//Создать справочник сотрудников
//        Необходимо:
//        Создать класс справочник сотрудников, который содержит внутри
//        коллекцию сотрудников - каждый сотрудник должен иметь следующие атрибуты:
//        Табельный номер
//        Номер телефона
//        Имя
//        Стаж
//        Добавить метод, который ищет сотрудника по стажу (может быть список)
//        Добавить метод, который возвращает номер телефона сотрудника по имени (может быть список)
//        Добавить метод, который ищет сотрудника по табельному номеру
//        Добавить метод добавления нового сотрудника в справочник


import java.util.List;

public class Main {

    private static Catalog catalog;


    public static void main(String[] args) {
        Worker worker1 = new Worker();
        Worker worker2 = new Worker();
        Worker worker3 = new Worker(123, 1234567, "Peter", 4);
        Worker worker4 = new Worker(123, 7654321, "Jack", 8);

        catalog = new Catalog();
        catalog.add(worker1);
        catalog.add(worker2);
        catalog.add(worker3);
        catalog.add(worker4);

        System.out.println(catalog);
        findByExp(4);
        findPhoneByName("Vasya");
        findByPersonalNumber(123);
    }


    private static void findByExp(int exp) {
        printResult(catalog.findByExp(exp), "exp");
    }
    private static void findByPersonalNumber(int personal) {
        printResult(catalog.findByPersonalNumber(personal), "personal");
    }
    private static void findPhoneByName(String name) {
        printResult(catalog.findPhoneByName(name), "phone");
    }

    private static void printResult(List<Worker> workers, String flag) {
        if (workers.size() == 0) {
            System.out.println("Не найдено");
            return;
        }
        for (Worker worker : workers) {
            switch (flag) {
                case "exp", "personal" -> System.out.println(worker.getName());
                case "phone" -> System.out.println(worker.getPhone());
            }
        }
    }
}
