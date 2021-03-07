package persistence;

import model.Task;
import model.TaskList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads tasklist from JSON data stored in file.
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads and returns tasklist from file
    // throws IOException if an error occurs reading data from file
    public TaskList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTaskList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses tasklist from JSON object and returns it
    private TaskList parseTaskList(JSONObject jsonObject) {
        int date = jsonObject.getInt("date");
        TaskList list = new TaskList(date);
        addTasks(list, jsonObject);
        return list;
    }

    // MODIFIES: list
    // EFFECTS: parse task from JSON object and adds them to list
    private void addTasks(TaskList list, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("task");
        for (Object json : jsonArray) {
            JSONObject nextTask = (JSONObject) json;
            addTask(list, nextTask);
        }
    }

    // MODIFIES: taskList
    // EFFECTS: parses task from JSON object and adds it to list
    private void addTask(TaskList list, JSONObject jsonObject) {
        String goal = jsonObject.getString("goal");
        int date = jsonObject.getInt("date");
        String status = jsonObject.getString("status");
        Task task = new Task(goal, date, status);
        list.addTask(task);
    }
}
