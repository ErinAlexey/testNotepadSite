package testSuiteUi;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Test class to check remove function via UI.
 *
 * This UI function removing one line from notebook-site
 * by clicking at the remove button on line, that you need to delete.
 */
public class TestUiRemove extends BaseUiTest {

    @AfterMethod
    public void logout() {

        try {
            logoutUi();
        } catch (NoSuchElementException | InterruptedException e) {
            return;
        }
    }

    /**
     * Check correct result of the remove function.
     * Find the line by number (start from 1) and click the remove button.
     * To check, that function is correct - try to find the line with same text,
     * and check the count of lines should be decremented by 1.
     *
     * @param username - username to login
     * @param password - password to login
     * @param lineNumber - number of removing line
     * @throws InterruptedException
     */
    @Test(dataProvider = "data_remove_ui_test", dataProviderClass = DataProviderUI.class)
    public void testRemoveUi(String username, String password, String lineNumber) throws InterruptedException {
        loginUi(username, password);

        String todoText = driver.findElement(By.cssSelector(
                "#main-page > div > main > div > div.view-todo-block > div.view-container " +
                        "> div:nth-child("+lineNumber+") > div.todo-description")).getText();
        WebElement todoButton = driver.findElement(By.cssSelector(
                "#main-page > div > main > div > div.view-todo-block > div.view-container " +
                        "> div:nth-child("+lineNumber+") > button"));
        long todoCount = driver.findElements(By.className("todo-description")).stream().count();

        todoButton.click();
        Thread.sleep(1000);
        boolean todo;

        try {
            if (!driver.findElement(By.cssSelector(
                    "#main-page > div > main > div > div.view-todo-block > div.view-container " +
                            "> div:nth-child("+lineNumber+") > div.todo-description")).getText().equals(todoText)
            || driver.findElements(By.className("todo-description")).stream().count() == todoCount-1)
                todo = true;
            else
                todo = false;
        }
        catch (NoSuchElementException ex) {
            todo = true;
        }

        Assert.assertTrue(todo);
    }
}
