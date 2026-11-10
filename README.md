# Taskloom (Java)

## Description
Taskloom is a command-line task and habit tracker. It is a Java port of my CS50 final project, [originally written in C](<https://github.com/Mohammedsob/taskloom>).
I rebuilt it in Java to learn the language by translating a project I already understood, so I could compare the two approaches directly.

## Features
- **Add tasks** with a description, priority (High/Medium/Low), and due date.
- **List tasks** in a formatted table.
- **Complete tasks** by **ID**, which increases a streak counter for habits.
- **Delete tasks** by **ID**.
- **Sort tasks** by priority, due date, or completion status using bubble sort.
- **Search tasks** by keyword in the description. The search function is case-insensitive.
- **Habit tracking**: any task marked as a habit. When a habit task is completed, its streak counter increases.
- Tasks are saved to `tasks.txt` on save/exit and reloaded automatically on startup.
- **Input validation**: the menu and every numeric prompt reject non-number input or out of range input and re-prompt instead of crashing or looping forever.
- Real date validation (`LocalDate` only accepts dates in the format of YYYY-MM-DD and rejects impossible dates like 2026-02-31).

## Project Structure
- `Priority.java`: enum for HIGH, MEDIUM, LOW.
- `Task.java`: the task data and its table row formatting.
- `TaskManager.java`: holds the list; add, complete, delete, search, sort, load, and save.
- `Main.java`: menu loop and input validation.
- `tasks.txt`: data file, with the format below and example lines.
- `pom.xml`: Maven build file (Java 11).
- `README.md`: This file.

## Data file format

Each line of `tasks.txt` is one task:

    id|description|priority|due_date|completed|is_habit|streak

Priority is 0 (High), 1 (Medium) or 2 (Low). Completed and habit are 0 or 1.

## Design decisions
- `ArrayList<Task>` instead of a fixed `Task[100]` array to remove the size limits.
- `Task` objects hold their behaviour (completed()) instead of passing `Task*` to functions.
- `enum Priority` instead of 0/1/2 magic numbers. The file still stores numbers.
- `LocalDate` instead of date strings compared with `strcmp`.
- `TaskManager` returns data and only `Main` prints. I made this decision to follow the single responsibility principle.
- Sorting: I used bubble sort as a refresher. Performance is not an issue at this scale.

## Known limitations and future improvements
- Task IDs are assigned at creation time and are never reused after deletion. This is intentional, but it means IDs are not guaranteed to be sequential after deletions.
- Streak logic is intentionally simple: completing a habit increases its streak by 1. It doesn't use actual calendar dates to detect missed days and reset the streak. This was an intentional scope decision to keep the feature achievable within the project timeline.
- Exiting the program suddenly e.g., Ctrl+C does not save; only selecting "Save tasks" or selecting "Exit" through the menu writes to `tasks.txt`.
- Possible next steps: JavaFX interface, database such as PostgreSQL or H2.
- Switching logic for the sort logic from bubble sort to list.sort(...) with a Comparator, which scales better as the number of tasks grows.

## Requirements
- Java 11
- Maven

## How to run
```
mvn compile
java -cp target/classes com.mohammed.taskmanager.Main
```
- Run these from the project root so tasks.txt is found.
