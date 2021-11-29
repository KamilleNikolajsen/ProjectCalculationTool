package projectCalculationTool.project;

import projectCalculationTool.employee.Employee;
import projectCalculationTool.subproject.SubProjectRepository;
import projectCalculationTool.util.DBManager;

import java.sql.*;
import java.util.ArrayList;

public class ProjectRepository implements ProjectRepositoryInterface {

    private static Connection connection = DBManager.getConnection();

    public void create(Project project) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("CALL create_project(?,?)");
            preparedStatement.setString(1, project.getName());
            preparedStatement.setInt(2, project.getEmployee().getEmployeeID());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); //MAKE OWN EXCEPTION???
        }
    }

    public ArrayList<Project> readProjects(Employee employee) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("CALL read_project(?)");
            preparedStatement.setInt(1, employee.getEmployeeID());

            ResultSet resultSet = preparedStatement.executeQuery();


            ArrayList<Project> projects = new ArrayList<>();

            while (resultSet.next()) {
                Project project = new Project();

                project.setProjectID(resultSet.getInt("project_ID"));
                project.setName(resultSet.getString("project_name"));

                projects.add(project);
            }

          return projects;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
