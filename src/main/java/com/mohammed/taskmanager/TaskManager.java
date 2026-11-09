package com.mohammed.taskmanager;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TaskManager {

    private ArrayList<Task> tasks;
    private int nextId = 1;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void add(String  description, Priority priority, LocalDate dueDate) {

        Task task = new Task(this.nextId, description, priority, dueDate);
        this.tasks.add(task);
        this.nextId++;
    }

    public List<Task> getTasks() {
        return new ArrayList<>(this.tasks);
    }

    public void loadTasksFromFile(){
        // Implement loading tasks from a file
        try (Scanner scan = new Scanner(Paths.get("tasks.txt"))) {
            while (scan.hasNextLine()) {
                String row = scan.nextLine();

                if (row.equals("")) {
                    break;
                }

                String[] parts = row.split("\\|");

                String description = parts[1];

                int tempPriority = Integer.valueOf(parts[2]);
                Priority priority = null;
                if (tempPriority == 0) {
                    priority = Priority.valueOf("HIGH");
                } else if (tempPriority == 1) {
                    priority = Priority.valueOf("MEDIUM");
                } else if (tempPriority == 2) {
                    priority = Priority.valueOf("LOW");
                }

                LocalDate dueDate = LocalDate.parse(parts[3]);

                int tempIsCompleted = Integer.valueOf(parts[4]);
                boolean isCompleted = false;
                if (tempIsCompleted == 1) {
                    isCompleted = true;
                }

                int tempIsHabit = Integer.valueOf(parts[5]);
                boolean isHabit = false;
                if (tempIsHabit == 1) {
                    isHabit = true;
                }

                int streak = Integer.valueOf(parts[6]);

                Task task = new Task(this.nextId, description, priority, dueDate, isCompleted, isHabit, streak);
                this.tasks.add(task);
                this.nextId++;
            }
        } catch (Exception e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    public void saveTasksToFile() {
        // Implement saving tasks to a file

    }

    public boolean completeTaskById(int id) {
        for (Task t : this.tasks){
            if (t.getId() == id) {
                t.isCompleted();
                return true;
            }
        }
        return false;
    }

    public boolean deleteTask(int id) {
        return this.tasks.removeIf(task -> task.getId() == id);
    }

    public void sortByPriority() {
        int size = this.tasks.size();
        for (int i = 0; i < size; i++) {
             boolean swapped = false;
            for (int j = 0; j < size - i - 1; j++) {
                if (this.tasks.get(j).getPriority().compareTo(this.tasks.get(j + 1).getPriority()) > 0) {
                    Task tempTask = this.tasks.get(j);
                    this.tasks.set(j, this.tasks.get(j + 1));
                    this.tasks.set(j + 1, tempTask);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public void sortByDueDate() {
        int size = this.tasks.size();
        for (int i = 0; i < size; i++) {
             boolean swapped = false;
            for (int j = 0; j < size - i - 1; j++) {
                if (this.tasks.get(j).getDueDate().isAfter(this.tasks.get(j + 1).getDueDate())) {
                    Task tempTask = this.tasks.get(j);
                    this.tasks.set(j, this.tasks.get(j + 1));
                    this.tasks.set(j + 1, tempTask);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public void sortByCompletion() {
        int size = this.tasks.size();
        for (int i = 0; i < size; i++) {
             boolean swapped = false;
            for (int j = 0; j < size - i - 1; j++) {
                if (this.tasks.get(j).getCompleted() && !this.tasks.get(j + 1).getCompleted()) {
                    Task tempTask = this.tasks.get(j);
                    this.tasks.set(j, this.tasks.get(j + 1));
                    this.tasks.set(j + 1, tempTask);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public List<Task> searchTask(String word) {
        return this.tasks.stream()
        .filter(task -> task.getDescription().contains(word))
        .collect(Collectors.toList());
    }
}