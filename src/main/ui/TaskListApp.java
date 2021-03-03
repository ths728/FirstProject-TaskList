package ui;

import model.Task;
import model.TaskList;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskListApp {
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();

        System.out.println("What do you want to do next?");
        System.out.println("Input a, if you want to add a task.");
        String userInput = scanner.next();

        if(userInput.equals("a")) {
            taskList.addTask(new Task("a", 2,"b"));
        }
    }
}
