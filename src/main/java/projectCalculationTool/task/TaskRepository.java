package projectCalculationTool.task;

import projectCalculationTool.subproject.SubProject;
import projectCalculationTool.util.DBManager;
import projectCalculationTool.util.exception.TaskException;

import java.sql.*;

public class TaskRepository implements TaskRepositoryInterface {
  private static Connection connection = DBManager.getConnection();

  @Override
  public void createTask(SubProject subProject) throws TaskException {
    try {
      Task task = subProject.getTasks().get(subProject.getTasks().size() - 1);

      PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tasks(task_name, task_hours, fk_subproject_id) VALUE (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);

      preparedStatement.setString(1, task.getName());
      preparedStatement.setDouble(2, task.getTimeHours());
      preparedStatement.setInt(3, subProject.getSubProjectID());

      preparedStatement.executeUpdate();

      ResultSet resultSet = preparedStatement.getGeneratedKeys();

      if (resultSet.next()) {
        task.setTaskID(resultSet.getInt(1));
      }

    } catch (SQLException err) {
      throw new TaskException("Creating Task failed", err);
    }
  }

  @Override
  public Task readTask(int taskID) throws TaskException {

    try {
      PreparedStatement ps = connection.prepareStatement("SELECT * FROM tasks WHERE task_id = ?");
      ps.setInt(1, taskID);
      ResultSet resultSet = ps.executeQuery();

      while (resultSet.next()) {
        String name = resultSet.getString("task_name");
        int time = resultSet.getInt("task_hours");

        Task task = new Task(time, name);
        task.setTaskID(taskID);

        return task;

      }

    } catch (SQLException err) {
      throw new TaskException("could not read task", err);
    }
    return null;
  }

  @Override
  public SubProject readAllTasks(SubProject subProject) throws TaskException {
    try {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tasks WHERE fk_subproject_id = ?;");
      preparedStatement.setInt(1, subProject.getSubProjectID());

      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        Task task = new Task(resultSet.getInt("task_hours"), resultSet.getString("task_name"));
        task.setTaskID(resultSet.getInt("task_id"));
        subProject.addTask(task);
      }

      return subProject;

    } catch (SQLException err) {
      throw new TaskException("Failed reading Tasks", err);
    }
  }


  @Override
  public void updateTask(Task task) throws TaskException {

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tasks SET task_name = ?, task_hours = ? WHERE task_id = ?");

      preparedStatement.setString(1, task.getName());
      preparedStatement.setDouble(2, task.getTimeHours());
      preparedStatement.setInt(3, task.getTaskID());

      preparedStatement.executeUpdate();

    } catch (SQLException err) {
      throw new TaskException("Task wasn't updated", err);
    }
  }

  @Override
  public void deleteTask(int taskID) throws TaskException {
    try {
      PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tasks WHERE task_id = ?;");
      preparedStatement.setInt(1, taskID);

      preparedStatement.executeUpdate();

    } catch (SQLException err) {
      throw new TaskException("Failed deleting Task", err);
    }
  }
}