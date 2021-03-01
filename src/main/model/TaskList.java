package model;

import model.Task;
import java.util.LinkedList;
import java.util.ArrayList;

// Represents a list of tasks
public class TaskList {
    private String goal;
    private int date;
    private String status;
    private ArrayList<Task> taskList;

    //Effects: Initialize new taskList without any tasks
    public TaskList() {
        this.goal = goal;
        this.date = date;
        this.status = status;
        taskList = new ArrayList<>();
    }

    // Modifies: this
    // Effects: add a task at the end of list.
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    // MODIFIES: this
    // EFFECTS: Delete a task from the list, if list if empty then return false, if list is not empty, delete the task.
    public boolean deleteTask(int n) {
        if (!taskList.isEmpty()) {
            taskList.remove(n);
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: Mark unfinished task as complete(finished)
    public void markTask(Task task) {
        if (task.getStatus() == "Unfinished") {
            task.setStatus("Finished");
        }
    }

    // EFFECTS: return the number of task with status of finished
    public int numberOfFinishedTask() {
        int count = 0;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getStatus() == "Finished") {
                count++;
            }
        }
        return count;
    }

    // EFFECTS: return the number of task with status of unfinished
    public int numberOfUnFinishedTask() {
        int count = 0;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getStatus() == "Unfinished") {
                count++;
            }
        }
        return count;
    }

    //Effects: if list is empty return true, otherwise return false.
    public boolean isEmpty() {
        if (taskList.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int length() {
        return taskList.size();
    }
}
