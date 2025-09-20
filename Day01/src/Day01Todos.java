import java.io.*;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Day01Todos {

    static ArrayList<Todo> todoList = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            loadDataFromFile();
            while (true) {
                System.out.println("\nPlease make a choice [0-4]: ");
                System.out.println("1. Add a todo");
                System.out.println("2. List all todos (numbered)");
                System.out.println("3. Delete a todo");
                System.out.println("4. Modify a todo");
                System.out.println("0. Exit");
                System.out.println("Your choice is:");

                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        addTodo();
                        break;
                    case 2:
                        listAllTodos();
                        break;
                    case 3:
                        deleteTodo();
                        break;
                    case 4:
                        modifyTodo();
                        break;
                    case 0:
                        saveDataToFile();
                        System.out.println("Exiting. Good bye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Try again.");
            return;
        }
    }

    private static void addTodo() {
        try {
            System.out.println("Enter task description:");
            String task = input.nextLine();

            System.out.println("Enter due Date (yyyy/mm/dd):");
            String dueDateString = input.nextLine();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
            Date dueDate = sdf.parse(dueDateString);

            System.out.println("Enter hours of work (integer):");
            int hoursOfWork = input.nextInt();
            input.nextLine();

            Todo todo = new Todo(task, dueDate, hoursOfWork);
            todoList.add(todo);
            System.out.println("You've created the following todo:");
            System.out.println(todo.toString());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input.");
        } catch (ParseException e) {
            System.out.println("Invalid date format.");
        }
    }

    private static void listAllTodos() {
        System.out.println("Listing all todos.");
        if (todoList.isEmpty()) {
            System.out.println("There are no todos.");

        } else {
            for (int i = 0; i < todoList.size(); i++) {
                Todo t = todoList.get(i);
                System.out.println("#" + (i + 1) + ":" + t.toString());
            }
        }
    }

    private static void deleteTodo() {

        if (todoList.isEmpty()) {
            System.out.println("There are no todos to delete.");
            return;
        }

        System.out.println("Deleting a todo. Which todo # would you like to delete?");
        try {
            int n = input.nextInt();
            input.nextLine();

            todoList.remove(n - 1);
            System.out.println("Deleted todo #" + n + " successfully.");
        } catch (Exception e) {
            System.out.println("Invalid number.");
        }
    }

    private static void modifyTodo() {

        if (todoList.isEmpty()) {
            System.out.println("There are no todos to modify.");
            return;
        }

        System.out.println("Modifying a todo. Which todo # would you like to modify?");
        try {
            int n = input.nextInt();
            input.nextLine();

            Todo todo = todoList.get(n - 1);
            System.out.println("Modifying todo #" + n + ": " + todo.toString());

            System.out.println("Enter new task description:");
            String task = input.nextLine();
            todo.setTask(task);

            System.out.println("Enter new due Date (yyyy/mm/dd):");
            String dueDateString = input.nextLine();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
            Date dueDate = sdf.parse(dueDateString);
            todo.setDueDate(dueDate);

            System.out.println("Enter new hours of work (integer):");
            int hoursOfWork = input.nextInt();
            input.nextLine();
            todo.setHoursOfWork(hoursOfWork);

            System.out.println("You've modified todo #" + n + " as follows:");
            System.out.println(todo.toString());
        } catch (InputMismatchException e) {
            System.out.println("Invalid number.");
        } catch (ParseException e) {
            System.out.println("Invalid date format.");
        }
    }

    static void loadDataFromFile() {
        String todosFile = "todos.txt";

        try (Scanner sc = new Scanner(new File(todosFile))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                Todo todo = new Todo(line);
                todoList.add(todo);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }

    }

    static void saveDataToFile () {
        String todosFile = "todos.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(todosFile))) {
            for (int i=0; i<todoList.size(); i++) {
                Todo todo = todoList.get(i);
                writer.write(todo.toDataString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}

