package JavaDevelopmentKit.Homework.Lesson_3.task_3;

//Напишите обобщенный класс Pair, который представляет собой пару значений разного типа.
//Класс должен иметь методы getFirst(), getSecond() для получения значений каждого из составляющих пары,
//а также переопределение метода toString(), возвращающее строковое представление пары.

public class Main {
    public static void main(String[] args) {
        Pair<Integer, String> pair = new Pair<>(5, "hello");
        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());
        System.out.println(pair);
    }
}