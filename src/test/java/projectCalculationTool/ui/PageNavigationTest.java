package projectCalculationTool.ui;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

// Referance til https://github.com/Tine-m/2.semUITestAutomation/blob/main/SystemTestAutomation/src/test/java/GoogleDemoTest.java
// Ordered Test Reference til https://www.vogella.com/tutorials/JUnit/article.html#test-execution-order
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PageNavigationTest {

  private static WebDriver selenium;
  private static String url;

  @BeforeAll
  public static void setUp() {
    // Load selenium driver
    // download chromeDriver.exe fra http://chromedriver.storage.googleapis.com/index.html?path=96.0.4664.45/

    // angiv url om det er heroku eller localhost
    url = "https://projectcalculationtool.herokuapp.com/";

    //Windows chromedriver
    System.setProperty("webdriver.chrome.driver", "src/test/java/webDriver/chromedriverWindows.exe");

    //Mac chromedriver
    //System.setProperty("webdriver.chrome.driver", "src/test/java/webDriver/chromedriverMac");

    selenium = new ChromeDriver();

    selenium.navigate().to(url);
    WebElement email = selenium.findElement(By.name("email"));
    email.sendKeys("test@yes.com");
    WebElement password = selenium.findElement(By.name("password"));
    password.sendKeys("123");
    // når du submitter et element i en form submittes det hele (ergo nednestående trykkes enter)
    password.submit();
  }

  @Test
  @Order(1)
  public void testNavigationValidtProject() {
    WebElement projectName = selenium.findElement(By.name("projectname"));
    projectName.sendKeys("SeleniumProjectTest");

    projectName.submit();

    assertEquals("SeleniumProjectTest", selenium.findElement(By.name("SeleniumProjectTest")).getText());
  }

  @Test
  @Order(2)
  public void testNavigationInvalidtProject() {
    WebElement projectName = selenium.findElement(By.name("projectname"));
    projectName.sendKeys("Project name cannot be longer then 45 characters this is the test");

    projectName.submit();

    assertEquals("Profile", selenium.getTitle());
  }

  @Test
  @Order(3)
  public void testNavigationValidtSubProject() {
    WebElement project = selenium.findElement(By.name("SeleniumProjectTest"));
    project.click();

    WebElement subprojectName = selenium.findElement(By.name("subprojectname"));
    subprojectName.sendKeys("SeleniumSubprojectTest");

    subprojectName.submit();

    assertEquals("SeleniumSubprojectTest", selenium.findElement(By.name("SeleniumSubprojectTest")).getText());
  }

  @Test
  @Order(4)
  public void testNavigationInvalidtSubproject() {
    WebElement subprojectName = selenium.findElement(By.name("subprojectname"));
    subprojectName.sendKeys("Subproject name cannot be longer then 45 characters this is the test");

    subprojectName.submit();

    assertEquals("Project", selenium.getTitle());
  }


  @Test
  @Order(5)
  public void testNavigationValidtTask() {
    WebElement taskName = selenium.findElement(By.name("taskName"));
    taskName.sendKeys("SeleniumTaskTest");

    WebElement taskTime = selenium.findElement(By.name("taskTime"));
    taskTime.sendKeys("2");

    taskTime.submit();

    assertEquals("SeleniumTaskTest", selenium.findElement(By.name("SeleniumTaskTest")).getText());
  }


  @ParameterizedTest
  @CsvSource(value = {"Task name cannot be longer then 45 characters this is the test:1",
      "Test Task:Have to be a number"}, delimiter = ':')
  @Order(6)
  public void testNavigationInvalidtTask(String inputTaskName, String inputTaskTime) {
    WebElement taskName = selenium.findElement(By.name("taskName"));
    taskName.sendKeys(inputTaskName);
    WebElement taskTime = selenium.findElement(By.name("taskTime"));
    taskTime.sendKeys(inputTaskTime);

    taskTime.submit();

    assertEquals("Project", selenium.getTitle());

  }

  @AfterAll
  public static void tearDown() {
    selenium.navigate().to(url + "profile");
    WebElement project = selenium.findElement(By.name("SeleniumProjectTestdelete"));
    project.click();

    // tear down browser
    selenium.quit();
  }
}