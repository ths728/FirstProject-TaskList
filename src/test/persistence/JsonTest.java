package persistence;

import model.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTask(String goal, Integer date, String status, Task task) {
        assertEquals(goal, task.getGoal());
        assertEquals(date, task.getDate());
        assertEquals(status, task.getStatus());
    }
}
