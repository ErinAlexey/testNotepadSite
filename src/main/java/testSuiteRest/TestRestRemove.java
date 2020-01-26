package testSuiteRest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * Test class to check Remove function via REST.
 *
 * This REST function is removing the note with special id
 * by post the request "/api/remove", with body JSON {"id":id}
 */
public class TestRestRemove extends BaseRestTest {

    /**
     * Positive check of remove function.
     * POST the request "/api/remove", with body JSON {"id":id}
     * Check that return status code is 200, and the response
     * contains "SUCCESS".
     *
     * @param username - username to login
     * @param password - password to login
     * @param id - id of removing nate
     */
    @Test(dataProvider = "data_remove_rest_test", dataProviderClass = DataProviderRest.class)
    public void testRestRemovePositive(String username, String password, String id) {
        Response response = loginRest(username, password);

        response = given()
                .baseUri("http://localhost:7844")
                .cookie(response.getDetailedCookie("Authorization"))
                .contentType(ContentType.JSON)
                .body("{ \"id\": \""+ id +"\" }")
                .when().post("/api/remove")
                .then().statusCode(200).extract().response();

        String respBody = response.getBody().asString();

        Assert.assertTrue(respBody.contains("SUCCESS"));
    }

    /**
     * Negative check of remove function.
     * POST the request "/api/remove" without login.
     * Check that response contains expected value.
     */
    @Test(dataProvider = "data_remove_rest_test", dataProviderClass = DataProviderRest.class)
    public void testRestRemoveNegative(String username, String password, String id) {
        Response response = given()
                .baseUri("http://localhost:7844")
                .contentType(ContentType.JSON)
                .body("{ \"id\": \""+ id +"\" }")
                .when().post("/api/remove")
                .then().statusCode(302).extract().response();
        String respHeader = response.getHeader("Location");
        response = given()
                .baseUri("http://localhost:7844")
                .get(respHeader)
                .then().statusCode(200).extract().response();
        String respBody = response.getBody().asString();

        Assert.assertTrue(respBody.contains("login-page"));
        Assert.assertTrue(respHeader.contains("error"));
    }
}
