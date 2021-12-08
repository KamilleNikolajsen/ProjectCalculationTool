package projectCalculationTool.ui;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.web.context.request.WebRequest;
import projectCalculationTool.employee.Employee;

import static org.junit.jupiter.api.Assertions.*;

// Referance til https://github.com/Tine-m/2.semUITestAutomation/blob/main/SystemTestAutomation/src/test/java/GoogleDemoTest.java
public class CreateProjectTest {

  private static WebDriver selenium;

  //Det viker ikke med min path...
  // Så måske vi lige skal afprøve det med en anden ellers er det måske bare helt forkert...

  @BeforeAll
  public static void setUp(){
    // Load selenium driver
    // download chromeDriver.exe fra http://chromedriver.storage.googleapis.com/index.html?path=96.0.4664.45/

    System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver_1.exe");
    selenium = new ChromeDriver();

    selenium.navigate().to("http://localhost:8080");
    WebElement email = selenium.findElement(By.name("email"));
    email.sendKeys("test@yes.com");
    WebElement password = selenium.findElement(By.name("password"));
    password.sendKeys("123");
    // når du submitter et element i en form submittes det hele (ergo nednestående trykkes enter)
    password.submit();
  }

  @Test
  public void canCreateProject(){
    selenium.navigate().to("http://localhost:8080/profile");
    WebElement projectName = selenium.findElement(By.name("projectname"));
    projectName.sendKeys("SeleniumProjectTest");

    projectName.submit();

    assertEquals("Profile", selenium.getTitle());
  }

  @ParameterizedTest
  @CsvSource(value = {"Project name cannot be longer then 45 characters this is the test"})
  public void cannotCreateProject(String inputProjectName){

    selenium.navigate().to("http://localhost:8080/profile");
    WebElement projectName = selenium.findElement(By.name("projectname"));
    projectName.sendKeys(inputProjectName);

    projectName.submit();

    assertEquals("Profile", selenium.getTitle());
  }

  @Test
  public void canCreateSubProject(){
    selenium.navigate().to("http://localhost:8080/project?id=46");
    WebElement subprojectName = selenium.findElement(By.name("subprojectname"));
    subprojectName.sendKeys("SeleniumSubprojectTest");

    subprojectName.submit();

    assertEquals("Project", selenium.getTitle());
  }

  @ParameterizedTest
  @CsvSource(value = {"Subproject name cannot be longer then 45 characters this is the test"})
  public void cannotCreateSubproject(String inputSubprojectName){

    selenium.navigate().to("http://localhost:8080/project?id=46");
    WebElement subProjectName = selenium.findElement(By.name("subprojectname"));
    subProjectName.sendKeys(inputSubprojectName);

    subProjectName.submit();

    assertEquals("Project", selenium.getTitle());
  }

  @Test
  public void canCreateTask(){
    selenium.navigate().to("http://localhost:8080/project?id=46");
    WebElement taskName = selenium.findElement(By.name("taskName"));
    taskName.sendKeys("SeleniumTaskTest");
    WebElement taskTime = selenium.findElement(By.name("taskTime"));
    taskTime.sendKeys("2");

    taskTime.submit();

    assertEquals("Project", selenium.getTitle());
  }

  @ParameterizedTest
  @CsvSource(value = {"Task name cannot be longer then 45 characters this is the test:1",
      "Test Task:Have to be a number"}, delimiter = ':')
  public void cannotCreateTask(String inputTaskName, String inputTaskTime){

    selenium.navigate().to("http://localhost:8080/project?id=46");
    WebElement taskName = selenium.findElement(By.name("taskName"));
    taskName.sendKeys(inputTaskName);
    WebElement taskTime = selenium.findElement(By.name("taskTime"));
    taskTime.sendKeys(inputTaskTime);

    taskTime.submit();

    assertEquals("Project", selenium.getTitle());
  }

  @AfterAll
  public static void tearDown(){
    selenium.quit();
  }

}