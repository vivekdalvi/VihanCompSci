
/* Vihan Dalvi
   AP CS P1
   Vulture Class
*/
package Critters;

import java.awt.*;

public class Vulture extends Bird {

    // instance variables
    private boolean isHungry;
    private boolean hasFought;

    public Vulture() {
        isHungry = true;
        hasFought = false;
    }

    public Attack fight(String opponent) {
        hasFought = true;
        if (opponent.equals("%")) {
            return Attack.ROAR;
        }
        return Attack.POUNCE;
    }

    public Color getColor() {
        return Color.BLACK;
    }

    public boolean eat() {
        if (isHungry == true | (isHungry == false && hasFought == true)) {
            isHungry = false;
            hasFought = false;
            return true;
        }
        return false;
    }
}
