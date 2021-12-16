package projectCalculationTool.task;

import org.junit.jupiter.api.Test;
import projectCalculationTool.util.exception.ValidateException;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {
    private final TaskService taskService = new TaskService(null);

    @Test
    void validateTaskTime() throws ValidateException {
        String tasktime = "2";

        int actual = taskService.validateTaskTime(tasktime);

        assertEquals(2, actual);
    }

    @Test
    void exceptionforNullTime(){
        String tasktime = null;

        Throwable exception = assertThrows(ValidateException.class, () -> taskService.validateTaskTime(tasktime));

        assertEquals("Task time cannot be empty and has to be a number in hours.",exception.getMessage());
    }


    @Test
    void invalidInputNotNumber()  {
        String tasktime = "test";

        Throwable exception = assertThrows(ValidateException.class, () -> taskService.validateTaskTime(tasktime));

        assertEquals("Task time has to be a number in hours.", exception.getMessage());
    }

    @Test
    void nullName()  {
        String name = null;

        Throwable exception = assertThrows(ValidateException.class,()-> taskService.validateTaskName(name));

        assertEquals("Task name cannot be null or longer than 45 characters.",exception.getMessage());
    }

}