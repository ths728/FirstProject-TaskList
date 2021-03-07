package persistence;

import model.Task;
import model.TaskList;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class JsonWriterTest extends JsonTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            TaskList list = new TaskList(20200304);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTaskList() {
        try {
            TaskList list = new TaskList(20210304);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTaskList.json");
            writer.open();
            writer.write(list);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTaskList.json");
            list = reader.read();
            assertEquals(20210304, list.getDate());
            assertEquals(0, list.length());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralTaskList() {
        try {
            TaskList list = new TaskList(20210304);
            list.addTask(new Task("CPSC 210 midterm", 20210304, "Finished"));
            list.addTask(new Task("CPSC 210 project", 20210304, "Unfinished"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTaskList.json");
            writer.open();
            writer.write(list);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTaskList.json");
            list = reader.read();
            assertEquals(20210304, list.getDate());
            List<Task> tasks = list.getTasks();
            assertEquals(2, list.length());
            checkTask("CPSC 210 midterm", 20210304, "Finished", tasks.get(0));
            checkTask("CPSC 210 project", 20210304, "Unfinished", tasks.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
