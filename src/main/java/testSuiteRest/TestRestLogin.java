package testSuiteRest;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class to check login function via REST.
 *
 * This REST function is logging in to user account
 * by post the request "/api/login", the JSON body is
 * {"password":"password", "username":"username"}
 */
public class TestRestLogin extends BaseRestTest {

    /**
     * Check the function login.
     * Use the POST with request "/api/login".
     * Check that return status code is 200,
     * and the response contains expected value.
     *
     * @param username - username to login
     * @param password - password to login
     * @param expectation - part of the response that determines the correct result
     */
    @Test(dataProvider = "data_login_rest_test", dataProviderClass = DataProviderRest.class)
    public void testLoginRest(String username, String password, String expectation) {
        Response response = loginRest(username, password);
        String respBody = response.getBody().asString();

        Assert.assertTrue(respBody.contains(expectation));
    }
}
