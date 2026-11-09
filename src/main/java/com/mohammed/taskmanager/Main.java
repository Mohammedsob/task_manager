package com.mohammed.taskmanager;

import java.io.IOException;
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
        taskManager.loadTasksFromFile();
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

                choice = Integer.valueOf(scan.nextLine());

                if (!(choice >= 1 && choice <= 8)) {
                    System.out.println("Error: Number is out of Range");
                } 
            } catch (InputMismatchException e) {
                System.out.println("Invalid response. Please enter Number.");
                scan.next();
            }

            if (choice == 1) {
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
            } else if (choice == 3) {
                int id = 0;
                System.out.print("Enter Task ID: ");
                try {
                    id = Integer.valueOf(scan.nextLine());
                } catch (InputMismatchException e) {
                    System.out.println("Invalid response. Please enter Number.");
                }

                boolean completed = taskManager.completeTaskById(id);

                if (completed) {
                    System.out.println("task " + id + " completed.");
                } else {
                    System.out.println("task " + id + " Not found");
                }

            } else if (choice == 4) {
                int id = 0;
                System.out.print("Enter Task ID: ");
                try {
                    id = Integer.valueOf(scan.nextLine());
                } catch (InputMismatchException e) {
                    System.out.println("Invalid response. Please enter Number.");
                }

                boolean deleted = taskManager.deleteTask(id);

                if (deleted) {
                    System.out.println("task " + id + " deleted.");
                } else {
                    System.out.println("task " + id + " Not found");
                }

            } else if (choice == 5) {
                int sortChoice = 0;
                System.out.print("Sort by: 1) Priority 2) Due Date 3) Completion: ");
                try {
                    sortChoice = Integer.valueOf(scan.nextLine());

                    if (!(choice >= 1 && choice <= 3)) {
                        System.out.println("Error: Number is out of Range 1 to 3");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid response. Please enter Number.");
                }

                if (sortChoice == 1) {
                    taskManager.sortByPriority();
                } else if (sortChoice == 2) {
                    taskManager.sortByDueDate();
                } else if (sortChoice == 3) {
                    taskManager.sortByCompletion();
                } else {
                    System.out.println("Invalid choice.");
                }

            } else if (choice == 6) {
                
            } else if (choice == 7) {
                System.out.print("Enter search keyword: ");
                String word = scan.nextLine();
                printTask(taskManager.searchTask(word));

            } else if (choice == 8) {
                break;
            } else {
                System.out.println("Invalid Choice.");
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
