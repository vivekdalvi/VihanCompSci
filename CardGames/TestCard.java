/*
 * TestCard.java
 * by w p osborne
 * 2/11/2022
 * Period 1
 */
package CardGames;

import java.util.Random;

public class TestCard {
    public static void main(String[] args) {

        // creates array of cards to store
        Card[] stack = new Card[20];
        Random gen = new Random();

        // loop creates each of 20 cards
        for (int i = 0; i < stack.length; i++) {
            boolean numberexists = true;
            // loop runs till unique value is generated
            while (numberexists == true) {
                numberexists = false;
                int randomnumber = gen.nextInt(51) + 1;
                Card temp = new Card(randomnumber);
                // checks if value exists in card already
                for (int j = 0; j < i; j++) {
                    if (stack[j].getIndex() == temp.getIndex()) {
                        numberexists = true;
                    }
                }
                if (numberexists == false) {
                    stack[i] = temp;
                }
            }
        }

        // prints each object using foreach loop
        for (Card print : stack) {
            System.out.println(print.toString());
        }
    }
}
