package comprimidos;

import java.io.Serializable;
import java.util.Scanner;

public class Read implements Serializable {

    public static String String() {
        String s = "";

        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                s = sc.nextLine();

                break;
            } catch (Exception e) {
                System.out.println("Error: Not a String");
            }

        }
        return s;
    }

    public static int Int() {
        int i = 0;

        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                i = sc.nextInt();

                break;
            } catch (Exception e) {
                System.out.println("Error: Not an Int");
            }
        }
        return i;
    }

    public static char Char() {
        char c = ' ';
        while (true) {

            try {
                Scanner sc = new Scanner(System.in);

                c = sc.next().charAt(0);
                break;
            } catch (Exception e) {
                System.out.println("Error: Not a Char");
            }
        }
        return c;
    }

    public static double Double() {
        double d = 0.0;

        try {
            Scanner sc = new Scanner(System.in);
            d = sc.nextDouble();

        } catch (Exception e) {
            System.out.println("Error: Not a Double");
        }
        return d;
    }

    public static boolean Bool() {
        boolean b = false;

        try {
            Scanner sc = new Scanner(System.in);
            b = sc.nextBoolean();
        } catch (Exception e) {
            System.out.println("Error: Not a Boolean");
        }
        return b;
    }
}
