package testSuiteUi;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Test class to check login function via UI.
 *
 * This UI function is logging in on site, by using
 * correct user information.
 */
public class TestUiLogin extends BaseUiTest {

    @AfterMethod
    public void logout() {

        try {
            logoutUi();
        } catch (NoSuchElementException | InterruptedException e) {
            return;
        }
    }

    /**
     * Positive scenario
     * Input correct values of username and password and click the button login.
     * Check that current page is main page.
     * Negative scenario
     * Input incorrect values of username and password and click the button login.
     * Check that current page still login page.
     *
     * @param username - username to login
     * @param password - password to login
     * @param expectedResult - expected result - the main page
     * @throws InterruptedException
     */
    @Test(dataProvider = "data_login_ui_test", dataProviderClass = DataProviderUI.class)
    public void testLogin(String username, String password, String expectedResult) throws InterruptedException {
        String actualResult = loginUi(username, password);

        Assert.assertEquals(actualResult, expectedResult);
    }
}
