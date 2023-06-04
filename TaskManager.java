import java.io.*;
import java.util.*;

public class TaskManager {
    private static final String FILE_PATH = "tasks.txt";

    public static void main(String[] args) {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }

            Scanner scanner = new Scanner(System.in);
            int choice;
            do {
                System.out.println("╔══════════════════════════════════╗");
                System.out.println("║          Task Manager            ║");
                System.out.println("╠══════════════════════════════════╣");
                System.out.println("║ 1. Add a task                    ║");
                System.out.println("║ 2. View all tasks                ║");
                System.out.println("║ 3. Exit                          ║");
                System.out.println("╚══════════════════════════════════╝");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Clear the newline character

                switch (choice) {
                    case 1:
                        addTask();
                        break;
                    case 2:
                        viewTasks();
                        break;
                    case 3:
                        System.out.println("Exiting Task Manager...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }

                System.out.println();
            } while (choice != 3);

            scanner.close();
        } catch (IOException e) {
            System.out.println("An error occurred while accessing the file.");
            e.printStackTrace();
        }
    }

    private static void addTask() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════╗");
        System.out.print("║ Enter task name: ");
        String taskName = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(taskName);
            writer.newLine();
            System.out.println("║ Task added successfully!");
        }

        scanner.close();
    }

    private static void viewTasks() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            System.out.println("╔══════════════════════════════════╗");
            System.out.println("║            Tasks                 ║");
            System.out.println("╠══════════════════════════════════╣");
            String line;
            int count = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println("║ " + count + ". " + line);
                count++;
            }
            System.out.println("╚══════════════════════════════════╝");
        }
    }
}
