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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

// Referance til https://github.com/Tine-m/2.semUITestAutomation/blob/main/SystemTestAutomation/src/test/java/GoogleDemoTest.java
public class LoginControllerTest {

    private static WebDriver selenium;

    @BeforeAll
    public static void setUp() {
        // Load selenium driver
        // download chromeDriver.exe fra http://chromedriver.storage.googleapis.com/index.html?path=96.0.4664.45/

        //Windows chromedriver
        System.setProperty("webdriver.chrome.driver", "src/test/java/webDriver/chromedriverWindows.exe");

        //Mac chromedriver
        //System.setProperty("webdriver.chrome.driver", "src/test/java/webDriver/chromedriverMac");

        selenium = new ChromeDriver();
    }

    @Test
    public void canLogin() throws InterruptedException {
        // Launch
        selenium.navigate().to("http://localhost:8080");
        WebElement email = selenium.findElement(By.name("email"));
        email.sendKeys("test@yes.com");
        WebElement password = selenium.findElement(By.name("password"));
        password.sendKeys("123");
        // når du submitter et element i en form submittes det hele (ergo nednestående trykkes enter)
        password.submit();

        assertEquals("Profile", selenium.getTitle());
    }

    @ParameterizedTest
    @CsvSource(value = {"invalid@yes.com:123", "test@yes.com:345", "test:123", ":4", "test:", ":"}, delimiter = ':')
    public void cannotLogin(String inputEmail, String inputPassword) {
        // Launch
        selenium.navigate().to("http://localhost:8080");
        WebElement email = selenium.findElement(By.name("email"));
        if (inputEmail != null) {
            email.sendKeys(inputEmail);
        }
        WebElement password = selenium.findElement(By.name("password"));
        if (inputPassword != null) {
            password.sendKeys(inputPassword);
        }
        // når du submitter et element i en form submittes det hele (ergo nednestående trykkes enter)
        password.submit();

        assertNotEquals("Profile", selenium.getTitle());
    }

    @AfterAll
    public static void tearDown() {
        // close browser
        selenium.quit();
    }
}
