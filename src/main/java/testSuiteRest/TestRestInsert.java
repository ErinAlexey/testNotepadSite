package testSuiteRest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * Test class to check Insert function via REST.
 *
 * This REST function is insert the note with special description
 * by post the request "/api/create", with body JSON {"description":"description"}
 */
public class TestRestInsert extends BaseRestTest {

    /**
     * Positive check of insert function.
     * POST the request "/api/create", with body JSON {"description":"description"}
     * Check that return status code is 200, and the response
     * contains "SUCCESS".
     *
     * @param username - username to login
     * @param password - password to login
     * @param description - description of inserting nate
     */
    @Test(dataProvider = "data_insert_rest_test", dataProviderClass = DataProviderRest.class)
    public void testRestInsertPositive(String username, String password, String description) {
        Response response = loginRest(username, password);

        response = given()
                .baseUri("http://localhost:7844")
                .cookie(response.getDetailedCookie("Authorization"))
                .contentType(ContentType.JSON)
                .body("{ \"description\": \""+ description +"\" }")
                .when().post("/api/create")
                .then().statusCode(200).extract().response();
        String respBody = response.getBody().asString();

        Assert.assertTrue(respBody.contains("SUCCESS"));
    }

    /**
     * Negative check of insert function.
     * POST the request "/api/create" without login.
     * Check that response contains expected value.
     */
    @Test(dataProvider = "data_insert_rest_test", dataProviderClass = DataProviderRest.class)
    public void testRestInsertNegative(String username, String password, String description) {
        Response response = given()
                .baseUri("http://localhost:7844")
                .contentType(ContentType.JSON)
                .body("{ \"description\": \""+ description +"\" }")
                .when().post("/api/create")
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
