package testSuiteUi;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class to check logout function via UI.
 *
 * This UI function is logging out from user account
 * by clicking on logout button.
 */
public class TestUiLogout extends BaseUiTest {

    /**
     * After login, click the logout button,
     * and check that the current page is login page.
     *
     * @param username - username to login
     * @param password - password to login
     * @param expectedResult - expected result - the login page
     * @throws InterruptedException
     */
    @Test(dataProvider = "data_logout_ui_test", dataProviderClass = DataProviderUI.class)
    public void testLogoutUi(String username, String password, String expectedResult) throws InterruptedException {
        loginUi(username, password);
        String actualResult = logoutUi();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
