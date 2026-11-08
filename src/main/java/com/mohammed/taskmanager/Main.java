package com.mohammed.taskmanager;

import java.util.List;

import com.mohammed.taskmanager.Priority.Task;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public static void printTask(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No task found");
            return;
        }
        System.out.println(Task.header());
        tasks.stream().forEach(task -> System.out.print(task););
    }
}
