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
        taskList = new TaskList();
        emptyList = new TaskList();
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
        TaskList taskListC = new TaskList();
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

    }

    @Test
    public void testDeleteTaskFromNonEmpty() {

    }

    @Test
    public void testMarkTask() {
        assertEquals(taskB.getStatus(), "Unfinished");
        taskList.markTask(taskB);
        assertEquals(taskB.getStatus(), "Finished");
        taskList.markTask(taskA);
        assertEquals(taskB.getStatus(), "Finished");
    }

    @Test
    public void testNumberOfFinishedTask() {
        assertEquals(0, emptyList.numberOfFinishedTask());
        assertEquals(1, taskList.numberOfFinishedTask());
        taskList.addTask(taskC);
        assertEquals(2, taskList.numberOfFinishedTask());
        taskList.addTask(taskD);
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
