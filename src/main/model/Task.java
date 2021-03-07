package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a task with goal, date and status
public class Task implements Writable {
    private String goal;
    // 8 digits represent the date, first four letters represent year, 5th & 6th represent month, last two represent day
    // 20210228 represent February 28th, 2021.
    private int date;
    private String status;

    public Task(String goal, int date, String status) {
        this.goal = goal;
        this.date = date;
        this.status = status;
    }

    public String getGoal() {
        return goal;
    }

    public int getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("goal", goal);
        json.put("date", date);
        json.put("status", status);
        return json;
    }
}
