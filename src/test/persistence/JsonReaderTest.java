package persistence;

import model.Task;
import model.TaskList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            TaskList list = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyTaskList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTaskList.json");
        try {
            TaskList list = reader.read();
            assertEquals(20210304, list.getDate());
            assertEquals(0, list.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralTaskList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTaskList.json");
        try {
            TaskList list = reader.read();
            assertEquals(20210304, list.getDate());
            List<Task> tasks = list.getTasks();
            assertEquals(2, list.length());
            checkTask("CPSC exam", 20210304, "Finished", tasks.get(0));
            checkTask("CPSC project", 20210304, "Unfinished", tasks.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
