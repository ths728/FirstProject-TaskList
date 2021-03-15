package ui;

import model.Task;
import model.TaskList;
import persistence.JsonWriter;
import persistence.JsonReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

// Represent the tasklist application
public class TaskListApp {
    private static final String JSON_STORE = "./data/tasklist.json";
    private Scanner input;
    private TaskList appTaskList;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    // EFFECTS: constructs tasklist and runs application
    public TaskListApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        appTaskList = new TaskList(20210304);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runTaskList();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTaskList() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add task");
        System.out.println("\td -> delete task");
        System.out.println("\tm -> mark task");
        System.out.println("\tf -> number of finished task");
        System.out.println("\tu -> number of unfinished task");
        System.out.println("\ts -> save tasklist to file");
        System.out.println("\tl -> load tasklist from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addTask();
        } else if (command.equals("d")) {
            deleteTask();
        } else if (command.equals("m")) {
            markIndexTask();
        } else if (command.equals("f")) {
            displayNumberOfFinishedTask();
        } else if (command.equals("u")) {
            displayNumberOfUnFinishedTask();
        } else if (command.equals("s")) {
            saveTaskList();
        } else if (command.equals("l")) {
            loadTaskList();
        } else {
            System.out.println("Invalid selection!");
        }
    }

    // MODIFIES: this
    // EFFECTS: prompt user for goal, date and status of task and adds to tasklist
    private void addTask() {
        System.out.println("Please enter task goal");
        String goal = input.next();
        System.out.println("Please enter task date");
        int date = input.nextInt();
        System.out.println("Please enter task status");
        String status = input.next();
        appTaskList.addTask(new Task(goal, date, status));
        System.out.println("Task added successfully");
    }

    // MODIFIES: this
    // EFFECTS: prompt user for date of task and remove from tasklist
    private void deleteTask() {
        System.out.println("Which task to delete?");
        int num = input.nextInt();
        appTaskList.deleteTask(num);
    }

    private void markIndexTask() {
        System.out.println("Which task is finished?");
        int index = input.nextInt();
        appTaskList.markIndexTask(index);
    }

    // EFFECTS: Display number of finished tasks in tasklist
    private void displayNumberOfFinishedTask() {
        System.out.println(appTaskList.numberOfFinishedTask());
    }

    // EFFECTS: Display number of unfinished task in tasklist
    private void displayNumberOfUnFinishedTask() {
        System.out.println(appTaskList.numberOfUnFinishedTask());
    }

    // EFFECTS: save the tasklist to file
    private void saveTaskList() {
        try {
            jsonWriter.open();
            jsonWriter.write(appTaskList);
            jsonWriter.close();
            System.out.println("Saved " + appTaskList.getDate() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads tasklist from file
    private void loadTaskList() {
        try {
            appTaskList = jsonReader.read();
            System.out.println("Loaded " + appTaskList.getDate() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file " + JSON_STORE);
        }
    }
}
