package JavaDevelopmentKit.Homework.Lesson_3.task_1;

public class Calculator {

    public static <T extends Number> double sum(T one, T two) {
        return one.doubleValue() + two.doubleValue();
    }
    public static <T extends Number> double multiply(T one, T two) {
        return one.doubleValue() * two.doubleValue();
    }
    public static <T extends Number> double divide(T one, T two) {
        if (two.doubleValue() == 0) {
            throw new RuntimeException("На ноль делить нельзя!");
        }
        return one.doubleValue() / two.doubleValue();
    }
    public static <T extends Number> double subtract(T one, T two) {
        return one.doubleValue() - two.doubleValue();
    }


}
