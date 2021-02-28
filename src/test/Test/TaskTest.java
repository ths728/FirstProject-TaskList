package Test;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    private Task taskTest;

    @BeforeEach
    public void runBefore() {
        taskTest = new Task("CPSC 210 In term exam", 20210224, "Finished");
    }

    @Test
    public void testConstructor() {
        assertEquals("CPSC 210 In term Exam", taskTest.getGoal());
        assertEquals(20210224, taskTest.getDate());
        assertEquals("Finished", taskTest.getStatus());
    }

    @Test
    public void testSetGoal() {
        assertEquals("CPSC 210 In term Exam", taskTest.getGoal());
        taskTest.setGoal("CPSC project");
        assertEquals("CPSC project", taskTest.getGoal());
    }

    @Test
    public void testSetDate() {
        assertEquals(20210224, taskTest.getDate());
        taskTest.setDate(20210227);
        assertEquals(20210227, taskTest.getDate());
    }

    @Test
    public void testSetStatus() {
        assertEquals("Finished", taskTest.getStatus());
        taskTest.setStatus("Not finished");
        assertEquals("Not finished", taskTest.getStatus());
    }
}
