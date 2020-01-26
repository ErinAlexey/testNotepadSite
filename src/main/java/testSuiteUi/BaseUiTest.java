package testSuiteUi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class BaseUiTest {

//    protected static Process    process = null;
    protected static WebDriver  driver;

    @BeforeSuite
    public void openConnection() throws IOException, InterruptedException {

//        process = Runtime.getRuntime().exec("java -jar automation-interview-task-1.0-SNAPSHOT.jar");
//        process.waitFor();
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterSuite
    public void closeConnection() {
        driver.quit();
//        process.destroy();
    }

    /**
     * Input the username and password ti according fields and click the login button.
     *
     * @param username - username to login
     * @param password - password to login
     * @return - url of current page
     * @throws InterruptedException
     */
    public String loginUi (String username, String password) throws InterruptedException {
        driver.get("http://localhost:7844/login");
        Thread.sleep(1000);
        WebElement usernameForm = driver.findElement(By.id("username"));
        WebElement passwordForm = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.className("button"));

        usernameForm.sendKeys(username);
        passwordForm.sendKeys(password);
        loginButton.click();

        Thread.sleep(1000);

        return driver.getCurrentUrl();
    }

    /**
     * Click the logout button.
     *
     * @return - URL of current page
     * @throws InterruptedException
     */
    public String logoutUi () throws InterruptedException {
        WebElement logoutButton = driver.findElement(By.xpath("//*[@id=\"main-page\"]/div/header/div/button"));
        logoutButton.click();

        Thread.sleep(1000);

        return driver.getCurrentUrl();
    }
}
