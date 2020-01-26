package testSuiteRest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * Test class to check logout function via REST.
 *
 * This REST function is logging out from user account
 * by post the request "/api/logout", without any body info.
 */
public class TestRestLogout extends BaseRestTest {

    /**
     * Check the function logout.
     * After login, use the POST with request "/api/logout".
     * Check that return status code is 200, and the response
     * contains expected value.
     * POST is return code 302, and Location header, and need to
     * redirect on the Location page.
     *
     * @param username - username to login
     * @param password - password to login
     * @param expectation - part of the response that determines the correct result
     */
    @Test(dataProvider = "data_logout_rest_test", dataProviderClass = DataProviderRest.class)
    public void testRestLogout(String username, String password, String expectation) {
        Response response = loginRest(username, password);

        response = given()
                .baseUri("http://localhost:7844")
                .contentType(ContentType.JSON)
                .cookie(response.getDetailedCookie("Authorization"))
                .when().post("/api/logout")
                .then().log().body().statusCode(302).extract().response();
        String location = response.getHeader("Location");
        response = given()
                .baseUri("http://localhost:7844")
                .cookie(response.getDetailedCookie("Authorization"))
                .when().get(location)
                .then().statusCode(200).extract().response();
        String respBody = response.getBody().asString();

        Assert.assertTrue(respBody.contains(expectation));
    }
}
