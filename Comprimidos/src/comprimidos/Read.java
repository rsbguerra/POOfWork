package comprimidos;

import java.io.*;
import java.util.*;

public class Read implements Serializable {

    public static String String() {
        String s = "";

        try {
            Scanner sc = new Scanner(System.in);
            s = sc.nextLine();
        } catch (Exception e) {
            System.out.println("Error: Not a String");
        }
        return s;
    }

    public static int Int() {
        int i = 0;
        try {
            Scanner sc = new Scanner(System.in);
            i = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Error: Not an Int");
        }

        return i;
    }

    public static char Char() {
        char c = ' ';

        try {
            Scanner sc = new Scanner(System.in);

            c = sc.next().charAt(0);
        } catch (Exception e) {
            System.out.println("Error: Not a Char");
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
