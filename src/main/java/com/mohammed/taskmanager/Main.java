package com.mohammed.taskmanager;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Main 
{
    public static void main( String[] args )
    {
        Scanner scan = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        int choice = 0;

        while(true) {

            System.out.println("\n=====TASKLOOM=====\n");
            System.out.println("1. Add Tasks");
            System.out.println("2. List Tasks");
            System.out.println("3. Complete Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Sort Task");
            System.out.println("6. Save Task");
            System.out.println("7. Search Task");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            try {

                choice = scan.nextInt();

                if (!(choice >= 1 && choice <= 8)) {
                    System.out.println("Error: Number is out of Range");
                } 
            } catch (InputMismatchException e) {
                System.out.println("Invalid response. Please enter Number.");
                scan.next();
            }

            if (choice == 1) {
                scan.nextLine();
                System.out.print("Task description: ");
                String decription = scan.nextLine();

                System.out.print("Priority (High, Medium, Low): ");
                String tempPriority = scan.nextLine();
                Priority priority = null;
                try {
                    priority = Priority.valueOf(tempPriority.trim().toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid priority. (High, Medium, Low).");
                }

                System.out.print("Due Date (YYYY-MM-DD): ");
                String tempDueDate = scan.nextLine();
                LocalDate dueDate = null;
                try{
                    dueDate = LocalDate.parse(tempDueDate);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid Due Date format. use YYYY-MM-DD.");
                }
                
                taskManager.add(decription, priority, dueDate);
            } else if (choice == 2) {
                printTask(taskManager.getTasks());
            } else if (choice == 8) {
                break;
            }
        }
        scan.close();
    }

    public static void printTask(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No task found");
            return;
        }
        System.out.println(Task.header());
        tasks.stream().forEach(task -> System.out.print(task));
    }
}
