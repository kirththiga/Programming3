import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day01DataFromFile {

    static ArrayList<String> namesList = new ArrayList<>();
    static ArrayList<Double> numsList = new ArrayList<>();
    static ArrayList<String> dupsList = new ArrayList<>();

    public static void main(String[] args) {
        String inputFile = "input.txt";
        String duplicatesFile = "duplicates.txt";

        try (Scanner sc = new Scanner(new File(inputFile))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                try {
                    double num = Double.parseDouble(line);
                    numsList.add(num);
                } catch (NumberFormatException e) {
                    namesList.add(line);
                }
            }

            Collections.sort(namesList);
            Collections.sort(numsList);

            System.out.println("Data loaded.");
            System.out.print("Names sorted: ");

            for (int i = 0; i < namesList.size(); i++) {
                if(i > 0) {
                    System.out.print(", ");
                }
                System.out.print(namesList.get(i));
            }

            System.out.println();
            System.out.print("Numbers sorted: ");

            for (int i = 0; i < numsList.size(); i++) {
                if(i > 0) {
                    System.out.print(", ");
                }
                System.out.print(numsList.get(i));
            }

            // Average length of names in characters (floating point)
            System.out.println();
            double total = 0;
            for(String name : namesList) {
                total += name.length();
            }

            double avg = total / namesList.size();
            System.out.printf("Average length of names: %.2f%n", avg);

            // Duplicates
            for(int i = 1; i < namesList.size(); i++) {
                if(namesList.get(i).equals(namesList.get(i-1))) {
                    if(dupsList.isEmpty() || !dupsList.getLast().equals(namesList.get(i))) {
                        dupsList.add(namesList.get(i));
                    }
                }
            }

            System.out.print("Duplicate names: ");

            for (int i = 0; i < dupsList.size(); i++) {
                if(i > 0) {
                    System.out.print(", ");
                }
                System.out.print(dupsList.get(i));
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(duplicatesFile))) {
                for (String line : dupsList) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.err.println("An error occurred while writing to the file: " + e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
}
