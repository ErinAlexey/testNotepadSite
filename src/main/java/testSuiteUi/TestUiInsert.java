package testSuiteUi;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Test class to check insert function via UI.
 *
 * This UI function insert one line on notebook-site
 * by writing text in input form and clicking at the insert button.
 */
public class TestUiInsert extends BaseUiTest {

    @AfterMethod
    public void logout() {

        try {
            logoutUi();
        } catch (NoSuchElementException | InterruptedException e) {
            return;
        }
    }

    /**
     * Check correct result of the insert function.
     * Write text in input field and click the add button.
     * To check that function is correct, check count of lines,
     * should be incremented by 1. And check that the line is equal
     * to input value.
     *
     * @param username - username to login
     * @param password - password to login
     * @param noteExample - input text
     * @throws InterruptedException
     */
    @Test(dataProvider = "data_positive_insert_ui_test", dataProviderClass = DataProviderUI.class)
    public void testPositiveInsertUi(String username, String password, String noteExample) throws InterruptedException {
        loginUi(username, password);

        WebElement createField = driver.findElement(By.xpath("//*[@id=\"main-page\"]/div/main/div/div[1]/div[2]/input"));
        WebElement createButton = driver.findElement(By.xpath("//*[@id=\"main-page\"]/div/main/div/div[1]/div[2]/button"));
        long todoCount = driver.findElements(By.className("todo-description")).stream().count();
        boolean todo;

        createField.sendKeys(noteExample);
        createButton.click();
        Thread.sleep(1000);

        List<WebElement> todoList = driver.findElements(By.className("todo-description"));
        if (driver.findElements(By.className("todo-description")).stream().count() == todoCount+1)
            todo = todoList.stream().map((elem) -> elem.getText()).anyMatch(noteExample::equals);
        else
            todo = false;

        Assert.assertTrue(todo);
    }

    /**
     * Check negative scenario of the insert function.
     * Write incorrect text in input field and click the add button.
     * To check try to found input text in list.
     *
     * @param username - username to login
     * @param password - password to login
     * @param noteExample - input text
     * @throws InterruptedException
     */
    @Test(dataProvider = "data_negative_insert_ui_test", dataProviderClass = DataProviderUI.class)
    public void testNegativeInsertUi(String username, String password, String noteExample) throws InterruptedException {
        loginUi(username, password);

        WebElement createField = driver.findElement(By.xpath("//*[@id=\"main-page\"]/div/main/div/div[1]/div[2]/input"));
        WebElement createButton = driver.findElement(By.xpath("//*[@id=\"main-page\"]/div/main/div/div[1]/div[2]/button"));

        createField.sendKeys(noteExample);
        createButton.click();
        Thread.sleep(1000);

        List<WebElement> todoList = driver.findElements(By.className("todo-description"));
        boolean todo = todoList.stream().map((elem) -> elem.getText()).anyMatch(noteExample::equals);

        Assert.assertFalse(todo);
    }
}
