package ru.plotnikov;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.*;

public class Game {

    private int countGames = 1;
    private final List<Door> list = new ArrayList<>(3);
    private boolean isWin = false;
    private final Random rnd = new Random();
    Map<Integer, Boolean> score = new HashMap<>();
    String finalMessage;

    public void start() {
        list.add(new Door(0));
        list.add(new Door(1));
        list.add(new Door(2));

        while (countGames <= 1000) {
            System.out.printf("\nВедущий: Начинаем раунд №%d\n\n", countGames);
            int prizeDoorNumber = getNumber(0, 3);
            hidePrize(prizeDoorNumber);
            System.out.println("Ведущий: Выберите номер двери");
            int playerChoiceDoor = getNumber(0, 3);
            while (playerChoiceDoor == prizeDoorNumber) {
                playerChoiceDoor = getNumber(0, 3);
            }
            System.out.printf("Игрок: Выбираю дверь №%d \n", playerChoiceDoor);
            System.out.println("Ведущий: Открываю дверь из двух оставшихся");
            int openDoor = getDoorNumber(prizeDoorNumber, playerChoiceDoor);
            System.out.printf("Ведущий: Открылась дверь №%d, за ней %s \n", openDoor, showPrize(openDoor));
            System.out.printf("Ведущий: Вы выбрали дверь №%d, будете менять свой выбор?\n", playerChoiceDoor);
            boolean isChangeDoor = rnd.nextBoolean();
            if (isChangeDoor) {
                System.out.println("Игрок: Да!");
                playerChoiceDoor = getDoorNumber(openDoor, playerChoiceDoor);
                System.out.printf("Ведущий: Игрок изменил выбор двери на №%d\n", playerChoiceDoor);
            } else {
                System.out.println("Игрок: Нет!");
            }
            System.out.printf("Ведущий: Открываю дверь №%d, которую выбрал игрок, за ней %s\n", playerChoiceDoor, showPrize(playerChoiceDoor));
            System.out.printf("Ведущий: Игрок %s\n", finalMessage);
            score.put(countGames, isWin);
            countGames++;
            setDefault();
        }
        calcStat();
    }

    private void calcStat() {
        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();
        for (Map.Entry<Integer, Boolean> integerBooleanEntry : score.entrySet()) {
            double result = integerBooleanEntry.getValue() ? 1 : 0;
            descriptiveStatistics.addValue(result);
        }
        double mean = descriptiveStatistics.getMean();
        System.out.printf("\nСтатистика за %d раундов\n", countGames - 1);
        System.out.printf("Среднее значение выигрышей: %.2f\n", mean);
    }

    private int getNumber(int to, int from) {
        return rnd.nextInt(to, from);
    }

    private int getDoorNumber(int doorOne, int doorTwo) {
        int currentDoorNumber = getNumber(0, 3);
        while (!(currentDoorNumber != doorOne && currentDoorNumber != doorTwo)) {
            currentDoorNumber = getNumber(0, 3);
        }
        return currentDoorNumber;
    }


    private void hidePrize(int number) {
        Door door = list.get(number);
        door.putPrize();
    }

    private String showPrize(int number) {
        Door door = list.get(number);
        return getPrizeName(door.getPrize());
    }

    private void setDefault() {
        for (Door door : list) {
            door.setDefault();
        }
    }

    private String getPrizeName(Door.Prize prize) {
        String result = null;
        switch (prize) {
            case CAR:
                result = "Машина";
                isWin = true;
                finalMessage = "выиграл";
                break;
            case DONKEY:
                result = "Осёл";
                isWin = false;
                finalMessage = "проиграл";
                break;
        }
        return result;
    }


}
