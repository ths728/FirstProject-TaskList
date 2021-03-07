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
    private static final String JSON_STORE = "./data/tasklist/json";
    private Scanner input;
    private TaskList taskList;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    // EFFECTS: constructs tasklist and runs application
    public TaskListApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        taskList = new TaskList(20210304);
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
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add task");
        System.out.println("\td -> delete task");
        System.out.println("\tm -> mark task");
        System.out.println("\tnf -> number of finished task");
        System.out.println("\tnuf -> number of unfinished task");
        System.out.println("\ts -> save taskliat to file");
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
            markTask();
        } else if (command.equals("nf")) {
            numberOfFinishedTask();
        } else if (command.equals("nuf")) {
            numberOfUnFinishedTask();
        } else if (command.equals("s")) {
            saveTaskList();
        } else if (command.equals("l")) {
            loadTaskList();
        }
    }

    // MODIFIES: this
    // EFFECTS: prompt user for goal, date and status of task and adds to tasklist
    private void addTask() {
        String goal = input.next();
        int date = input.nextInt();
        String status = input.next();
        taskList.addTask(new Task(goal, date, status));
    }

    // MODIFIES: this
    // EFFECTS: prompt user for date of task and remove from tasklist
    private void deleteTask() {
        int date = input.nextInt();
        taskList.deleteTask(date);
    }

    // MODIFIES: this
    // EFFECTS: prompt user for index of task in list and mark status as finished if the status is unfinished
    private void markTask() {
        int index = input.nextInt();
        List<Task> tasks = taskList.getTasks();
        List newTasks = new ArrayList(tasks);
        for (Object t: newTasks) {
            Task task = (Task) newTasks.get(index - 1);
            String goal = task.getGoal();
            int date = task.getDate();
            newTasks.set(index - 1, new Task(goal, date, "Finished"));
        }
    }

    // MODIFIES： this
    // EFFECTS: show number of finished tasks in tasklist
    private void numberOfFinishedTask() {
        taskList.numberOfFinishedTask();
    }

    // MODIFIES: this
    // EFFECTS: show number of unfinished task in tasklist
    private void numberOfUnFinishedTask() {
        taskList.numberOfUnFinishedTask();
    }

    // EFFECTS: save the tasklist to file
    private void saveTaskList() {
        try {
            jsonWriter.open();
            jsonWriter.write(taskList);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            // pass
        }
    }

    // MODIFIES: this
    // EFFECTS: loads tasklist from file
    private void loadTaskList() {
        try {
            taskList = jsonReader.read();
        } catch (IOException e) {
            // pass
        }
    }
}
