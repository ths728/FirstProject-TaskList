package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    private TaskList emptyList;
    private TaskList taskList;
    private Task taskA;
    private Task taskB;
    private Task taskC;
    private Task taskD;
    private Task taskE;

    @BeforeEach
    public void runBefore() {
        emptyList = new TaskList(20200304);
        taskList = new TaskList(20200304);
        taskA = new Task("CPSC 210 In term exam", 20210224, "Finished");
        taskB = new Task("STAT 302 Midterm exam", 20210302, "Unfinished");
        taskC = new Task("MATH 221 WebWork 5", 20210227, "Finished");
        taskD = new Task("Badminton exercise", 20210303, "Unfinished");
        taskE = new Task("CPSC 210 Project", 20200228, "Finished");
        taskList.addTask(taskA);
        taskList.addTask(taskB);
    }

    @Test
    public void testConstructor() {
        TaskList taskListC = new TaskList(20200404);
        assertTrue (taskListC.isEmpty());
        assertEquals(0,taskListC.length());
    }

    @Test
    public void testAddTaskToEmpty() {
        assertTrue(emptyList.isEmpty());
        assertEquals(0,emptyList.length());
        emptyList.addTask(taskA);
        assertFalse(emptyList.isEmpty());
        assertEquals(1,emptyList.length());
        emptyList.addTask(taskB);
        assertFalse(emptyList.isEmpty());
        assertEquals(2,emptyList.length());
    }

    @Test
    public void testAddTaskToNonEmpty(){
        assertFalse(taskList.isEmpty());
        assertEquals(2, taskList.length());
        taskList.addTask(taskC);
        assertFalse(taskList.isEmpty());
        assertEquals(3,taskList.length());
    }

    @Test
    public void testDeleteTaskFromEmpty() {
        assertEquals(0, emptyList.length());
        assertFalse(emptyList.deleteTask(0));
        assertFalse(emptyList.deleteTask(1));
        assertEquals(0, emptyList.length());
    }

    @Test
    public void testDeleteTaskFromNonEmpty() {
        assertEquals(2,taskList.length());
        assertFalse(taskList.isEmpty());
        taskList.deleteTask(0);
        assertEquals(1, taskList.length());
        assertTrue(taskList.deleteTask(0));
        assertFalse(taskList.deleteTask(1));
        taskList.deleteTask(1);
        assertEquals(0, taskList.length());
        assertFalse(taskList.deleteTask(1));
    }

    @Test
    public void testMarkIndexTask() {
        taskList.markIndexTask(1);
        assertEquals(taskB.getStatus(), "Finished");
        taskList.addTask(taskD);
        taskList.markIndexTask(2);
        assertEquals(taskD.getStatus(), "Finished");
        assertEquals(taskA.getStatus(), "Finished");
    }

    @Test
    public void testNumberOfFinishedTask() {
        assertEquals(0, emptyList.numberOfFinishedTask());
        assertEquals(1, taskList.numberOfFinishedTask());
        taskList.addTask(taskC);
        assertEquals(2, taskList.numberOfFinishedTask());
        taskList.addTask(taskD);
        assertEquals(2, taskList.numberOfFinishedTask());
        taskList.addTask(new Task("CPSC", 1, "Unfinished"));
        assertEquals(2, taskList.numberOfFinishedTask());
        taskList.addTask(taskE);
        assertEquals(3, taskList.numberOfFinishedTask());
    }

    @Test
    public void testNumberOfUnFinishedTask() {
        assertEquals(0, emptyList.numberOfUnFinishedTask());
        assertEquals(1, taskList.numberOfUnFinishedTask());
        taskList.addTask(taskC);
        assertEquals(1, taskList.numberOfUnFinishedTask());
        taskList.addTask(taskD);
        assertEquals(2, taskList.numberOfUnFinishedTask());
        taskList.addTask(taskE);
        assertEquals(2, taskList.numberOfUnFinishedTask());
    }

    @Test
    public void testLength() {
        assertEquals(0, emptyList.length());
        assertEquals(2, taskList.length());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(emptyList.isEmpty());
        assertFalse(taskList.isEmpty());
    }

}
