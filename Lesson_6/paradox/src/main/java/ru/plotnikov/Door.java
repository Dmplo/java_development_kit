package ru.plotnikov;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Door {

    private int number;
    private Prize prize;

    enum Prize {
        DONKEY, CAR
    }

    public Door(int number) {
        this.number = number;
        this.prize = Prize.DONKEY;
    }

    public void putPrize() {
        prize = Prize.CAR;
    }

   public void setDefault() {
       prize = Prize.DONKEY;
   }

   }
