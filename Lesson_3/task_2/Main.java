package JavaDevelopmentKit.Homework.Lesson_3.task_2;

//Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true,
//если они одинаковые, и false в противном случае. Массивы могут быть любого типа данных,
//но должны иметь одинаковую длину и содержать элементы одного типа по парно по индексам.



import JavaDevelopmentKit.Homework.Lesson_3.task_1.Calculator;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = new Integer[] {1, 2, 3};
        Double[] arr2 = new Double[] {1.5, 2.7, 3.3};
        System.out.println(compareArrays(arr, arr2));

        Integer[] arr3 = new Integer[] {1, 2, 3};
        Integer[] arr4 = new Integer[] {1, 2, 3};
        System.out.println(compareArrays(arr3, arr4));
    }

    private static <T> boolean compareArrays(T[] arr1, T[] arr2) {
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].getClass().getSimpleName().equals(arr2[i].getClass().getSimpleName())) {
                return false;
            }
        }
        return true;
    }

}
