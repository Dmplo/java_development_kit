package JavaDevelopmentKit.Homework.Lesson_5;

/*
1. Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
2. Вилки лежат на столе между каждой парой ближайших философов.
3. Каждый философ может либо есть, либо размышлять.
4. Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
5. Философ не может есть два раза подряд, не прервавшись на размышления (можно не учитывать)

Описать в виде кода такую ситуацию. Каждый философ должен поесть три раза
 */


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Fork fork1 = new Fork();
        Fork fork2 = new Fork();
        Fork fork3 = new Fork();
        Fork fork4 = new Fork();
        Fork fork5 = new Fork();

        List<Philosopher> philosophers = new ArrayList<>();
        philosophers.add(new Philosopher("philosopher1", fork5, fork1));
        philosophers.add(new Philosopher("philosopher2", fork1, fork2));
        philosophers.add(new Philosopher("philosopher3", fork2, fork3));
        philosophers.add(new Philosopher("philosopher4", fork3, fork4));
        philosophers.add(new Philosopher("philosopher5", fork4, fork5));

        for (Philosopher philosopher : philosophers) {
            philosopher.start();
        }


    }
}
