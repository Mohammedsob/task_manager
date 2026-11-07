package com.mohammed.taskmanager;

import java.time.LocalDate;

public enum Priority {
    HIGH, MEDIUM, LOW;
}

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

    public void makeAhabit() {
        this.isHabit = true;
        this.streak = 0;
    }

    public void completed() {
        this.completed = true;
        if (this.isHabit) {
            this.streak++;
        }
    }

    @Override
    public String toString() {
        return "";
    }
}