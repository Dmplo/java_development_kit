package JavaDevelopmentKit.Homework.Lesson_3.task_1;

//Напишите класс Калькулятор (необобщенный), который содержит обобщенные статические методы: sum(), multiply(),
//divide(), subtract(). Параметры этих методов – два числа разного типа, над которыми должна быть произведена операция.
//Методы должны возвращать результат своей работы.


public class Main {
    public static void main(String[] args) {
        System.out.println(Calculator.sum(1, 5.0f));
        System.out.println(Calculator.multiply(4, 123.45));
        System.out.println(Calculator.divide(6.0f, 3));
        System.out.println(Calculator.divide(10, 4));
        System.out.println(Calculator.subtract(2.7f, 2));
        System.out.println(Calculator.divide(6.0f, 0));
    }
}
