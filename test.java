
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        // System.out.println(mystery(976, 9235));
        // System.out.println(mystery(348, 34298));
        // System.out.println(mystery(2974, 2184));
        // System.out.println(mystery(82, 19));
        // 5System.out.println(mystery(2348, 239));
        // Scanner console = new Scanner(System.in);
        // int ret = tester(25);

        // test(console);
    }

    public static int tester(int x) {
        int y = 1;
        int z = 0;
        // point A
        while (x > y) {
            // point B
            z = z + x - y;
            x /= 2;
            // point C
            y *= 2;
            // point D
        }
        // point E
        return z;
    }

    public static int test1(Scanner console) {
        int prev = 0;
        int count = 0;
        int next = console.nextInt();

        while (next != 0) {
            if (next == prev) {
                count++;
            }
            prev = next;
            next = console.nextInt();

        }
        return count;

    }

    public static void ifElseMystery(int a, int b) {
        int c = 2;
        if (a + c < b) {
            c = c + 8;
        } else {
            b = b + 10;
        }
        if (a + c < b) {
            c = c + 8;
        } else {
            b = b + 10;
        }
        System.out.println(b + " " + c);
    }

    public static int mystery(int x, int y) {
        while (x != y) {
            if (x < y) {
                y /= 10;
            } else {
                x /= 10;
            }
        }
        return x;
    }

    public static boolean hasOddEven(int a, int b, int c) {

        if (a % 2 != 0 || b % 2 != 0 || c % 2 != 0) {

            if (a % 2 == 0 || b % 2 == 0 || c % 2 == 0) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }

    }
}
