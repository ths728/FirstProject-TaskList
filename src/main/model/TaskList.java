package model;

import persistence.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a list of tasks
public class TaskList implements Writable {
    private int date;
    private List<Task> taskList;

    //Effects: Initialize new taskList without any tasks
    public TaskList(Integer date) {
        this.date = date;
        this.taskList = new ArrayList<>();
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
    // EFFECTS: Mark unfinished task as finished with given index of task in list;
    public void markIndexTask(int index) {
        taskList.get(index).setStatus("Finished");
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
        return taskList.size() == 0;
    }


    public int length() {
        return taskList.size();
    }

    public List<Task> getTasks() {
        return Collections.unmodifiableList(taskList);
    }

    public int getDate() {
        return date;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("date", date);
        json.put("task", taskListToJson());
        return json;
    }

    // EFFECTS: return tasks in this tasklist as a JSON array
    private JSONArray taskListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Task t : taskList) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }
}
