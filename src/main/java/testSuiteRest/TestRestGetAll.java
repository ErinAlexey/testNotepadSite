package testSuiteRest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * Test class to check Get All function via REST.
 *
 * This REST function is displayed all notes of user
 * by post the request "/api/getAll", without any body info.
 */
public class TestRestGetAll extends BaseRestTest {

    /**
     * Positive check of getAll function.
     * POST the request "/api/getAll".
     * Check that return status code is 200, and the response
     * contains expected value.
     *
     * @param username - username to login
     * @param password - password to login
     * @param expectation - part of the response that determines the correct result
     */
    @Test(dataProvider = "data_getall_rest_test", dataProviderClass = DataProviderRest.class)
    public void testRestGetAll(String username, String password, String expectation) {
        Response response = loginRest(username, password);

        response = given()
                .baseUri("http://localhost:7844")
                .cookie(response.getDetailedCookie("Authorization"))
                .when().get("/api/getAll")
                .then().statusCode(200).extract().response();
        String respBody = response.getBody().asString();

        Assert.assertTrue(respBody.contains(expectation));
    }

    /**
     * Negative check of getAll function.
     * POST the request "/api/getAll" without login.
     * Check that response contains expected value.
     */
    @Test
    public void testRestInsertNegative() {
        Response response = given()
                .baseUri("http://localhost:7844")
                .contentType(ContentType.JSON)
                .when().get("/api/getAll")
                .then().statusCode(200).extract().response();
        String respBody = response.getBody().asString();

        Assert.assertTrue(respBody.contains("login-page"));
    }
}
