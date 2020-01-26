package testSuiteRest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseRestTest {

    /**
     * Login function, POST the request "/api/login"
     * with JSON body {"password":"password", "username":"username"}
     *
     * @param username - username to login
     * @param password - password to login
     * @return - response value
     */
    public Response loginRest(String username, String password) {
        Response resp = given()
                .baseUri("http://localhost:7844")
                .contentType(ContentType.JSON)
                .body("{ \"password\" : \""+password+"\",  \"username\" : \""+username+"\" }")
                .when().post("/api/login")
                .then().extract().response();
        return resp;
    }
}
