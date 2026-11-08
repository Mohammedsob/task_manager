package com.mohammed.taskmanager;

import java.time.LocalDate;

public enum Priority {
    HIGH, MEDIUM, LOW;
}

private static final String FORMAT = "%-4s %-25s %-10s %-12s %-10s %-10s %-10s%n"

public class Task {
    
    private int id;
    private String description;
    private Priority priority;
    private LocalDate dueDate;
    private boolean completed;
    private int streak;
    private boolean isHabit;

    public Task(int id, String description, Priority priority, LocalDate dueDate) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.completed = false;
        this.isHabit = false;
    }

    public Task(int id, String description, Priority priority, LocalDate dueDate,
            boolean completed, boolean isHabit, int streak) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.completed = false;
        this.isHabit = false;
    }

    public int getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public int getStreak() {
        return this.streak;
    }

    public void changePriority(Priority priority) {
        this.priority = priority;
    }

    public void changeDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void makeHabit() {
        this.isHabit = true;
        this.streak = 0;
    }

    public void isCompleted() {
        this.completed = true;
        if (this.isHabit) {
            this.streak++;
        }
    }

    public static String header() {
        return String.format(FORMAT, "ID", "Task", "Priority", "Due Date",
                         "Status", "Type", "Streak");
    }

    @Override
    public String toString() {
        return String.format(FORMAT,
            this.id,
            this.description,
            this.priority,
            this.dueDate,
            this.completed,
            this.isHabit,
            this.streak);
    }
}