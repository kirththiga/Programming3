import java.util.Random;
import java.util.Scanner;

public class Day01Randoms {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        try {
            System.out.println("How many random integers do you want to generate?");
            int num = sc.nextInt();
            if (num < 0) {
                System.out.println("Invalid number. Please enter a positive integer.");
                System.exit(0);
            }
            sc.nextLine();

            System.out.println("What is your name?");
            String name = sc.nextLine();
            if (name.length() == 0) {
                System.out.println("Empty string. Please enter a valid name.");
                System.exit(0);
            }

            System.out.println("Enter minimum:");
            int min = sc.nextInt();

            System.out.println("Enter maximum:");
            int max = sc.nextInt();
            if (min > max) {
                System.out.println("Minimum cannot be greater than maximum.");
                System.exit(0);
            }

            for (int i = 0; i < num; i++) {
                int j = rand.nextInt((max - min) + 1) + min;

                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(j);
            }
            System.out.println();
            System.out.println("Did I do well, " + name + "?");
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter an integer.");
            System.exit(0);
        } finally {
            sc.close();
        }
    }
}
