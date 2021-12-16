package projectCalculationTool.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectCalculationTool.employee.Employee;
import projectCalculationTool.testData.TestData;
import projectCalculationTool.util.exception.ProjectException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProjectRepositoryTest {

  private ProjectRepository projectRepository;
  private Project project;
  private Employee employee;

  @BeforeEach
  public void setUp() {

    //Arrange
    TestData testData = new TestData();
    testData.setUp();

    //Arrange
    projectRepository = new ProjectRepository();
    project = new Project();
    employee = new Employee();

    //Act
    project.setProjectID(1);

    employee.setEmployeeID(1);
    employee.setEmail("test@yes.com");
    employee.setPassword("123");
  }


  //-----------------------READ tests-----------------------
  @Test
  public void readProject() throws ProjectException {
    //Act
    Project project = projectRepository.readProject(1);

    //Assert
    assertEquals(1, project.getProjectID());
    assertEquals("testData_Project", project.getName() );

    // KILDE: https://github.com/Tine-m/2.semLoginSample/blob/testautomation/loginsample/src/test/java/login/repositories/UserRepositoryTest2.java
  }

  @Test
  public void readProjectWithException() {
    //Assert
    assertThrows(ProjectException.class, () -> projectRepository.readProject(-1));
  }


  @Test
  public void readAllProjectNotNull() throws ProjectException {

    //Act + Arrange
    ArrayList<Project> projects = projectRepository.readAllProjects(employee);

    //Assert
    assertNotNull(projects);
  }

  @Test
  public void createProject() throws ProjectException {

    Project project1 = new Project();
    project1.setName("JUnitTest");
    project1.setEmployee(employee);

   projectRepository.createProject(project1);

   assertTrue(project1.getProjectID() != 0);

   projectRepository.deleteProject(project1.getProjectID());
  }

  @Test
  public void createProjectToLongName(){

    Project project1 = new Project();
    project1.setName("ggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg");
    project1.setEmployee(employee);

    assertThrows(ProjectException.class, () -> projectRepository.createProject(project1));
  }

  @Test
  public void deleteProjectSuccessfully(){
    assertDoesNotThrow(() -> projectRepository.deleteProject(project.getProjectID()));
  }

  @Test
  public void updateProjectSuccessfully(){
    project.setName("testData_Project");

    String newName = "newTestName";
    project.setName(newName);

    assertDoesNotThrow(() -> projectRepository.updateProject(project));
    assertEquals(newName, project.getName());
  }

  @Test
  public void updateProjectWithOldName(){
    String oldName = "testData_Project";
    project.setName(oldName);

    project.setName("newTestName");

    assertDoesNotThrow(() -> projectRepository.updateProject(project));
    assertNotEquals(oldName, project.getName());
  }

}