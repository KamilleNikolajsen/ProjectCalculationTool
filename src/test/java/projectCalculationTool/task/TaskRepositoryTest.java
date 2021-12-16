package projectCalculationTool.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectCalculationTool.subproject.SubProject;
import projectCalculationTool.util.exception.TaskException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskRepositoryTest {

  TaskRepository taskRepository;

  @BeforeEach
  void setUp() {
    taskRepository = new TaskRepository();
  }

  @Test
  public void testCreateTask() throws TaskException {
    // Arrange
    SubProject subProject = new SubProject("Fødselsdagssnacks");
    subProject.setSubProjectID(1);

    ArrayList<Task> tasks = new ArrayList<>();
    Task bakeTask = new Task(4, "Bake cake");

    tasks.add(bakeTask);

    subProject.setTasks(tasks);

    // Act
    taskRepository.createTask(subProject);

    // Assert
    assertTrue(bakeTask.getTaskID() != 0);
  }

}