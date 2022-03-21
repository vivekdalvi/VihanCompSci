
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

    public Color getColor() {
        return Color.BLACK;
    }

    public boolean eat() {
        if (isHungry == true) {
            isHungry = false;
            return true;
        }
        if (isHungry == false && hasFought == true) {
            isHungry = true;
            return true;
        }
        return false;
    }
}
