package com.mohammed.taskmanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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