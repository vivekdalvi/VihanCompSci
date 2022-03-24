package Critters;

import java.util.Random;
import java.awt.*;
import java.time.*;

public class Husky extends Critter {

    private Random gen;
    private int randomnum;
    private Breed breed;
    private int hungerPieces;

    public Husky() {
        gen = new Random();
        randomnum = gen.nextInt(3);
        if (randomnum == 0) {
            breed = Breed.ALASKAN;
            hungerPieces = 5;
        }
        if (randomnum == 1) {
            breed = Breed.SIBERIAN;
            hungerPieces = 5;
        }
        if (randomnum == 2) {
            breed = Breed.SAMOYED;
        }
    }

    public Color getColor() {
        if (breed == Breed.ALASKAN) {
            return Color.GRAY;

        } else if (breed == Breed.SAMOYED) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }

    // different breeds get hungry at different times
    public boolean eat() {
        LocalTime time = LocalTime.now();
        if (breed == Breed.ALASKAN) {
            // hungry between noon-1PM and 7-8 PM
            if ((time.getHour() == 12 || time.getHour() == 19) && (hungerPieces > 0)) {
                hungerPieces--;
                return true;
            }
        } else if (breed == Breed.SAMOYED) {
            if ((time.getHour() == 11 || time.getHour() == 20) && (hungerPieces > 0)) {
                hungerPieces--;
                return true;
            }
        } else {
            if ((time.getHour() == 13 || time.getHour() == 21) && (hungerPieces > 0)) {
                hungerPieces--;
                return true;
            }
        }
        return false;
    }

    // they are all figers but they fight different way
    public Attack fight(String opponent) {
        if (breed == Breed.ALASKAN) {
            return Attack.POUNCE;

        } else if (breed == Breed.SAMOYED) {
            return Attack.SCRATCH;
        } else {
            return Attack.ROAR;
        }
    }

    // all huskies walk around the house so i chose behavior that is same as
    public Direction getMove() {
        if (breed == Breed.ALASKAN) {
            return Color.GRAY;

        } else if (breed == Breed.SAMOYED) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }

    public String toString() {
        if (breed == Breed.ALASKAN) {
            return Color.GRAY;

        } else if (breed == Breed.SAMOYED) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }

    // constants for fighting
    public static enum Breed {
        ALASKAN, SIBERIAN, SAMOYED
    };

}