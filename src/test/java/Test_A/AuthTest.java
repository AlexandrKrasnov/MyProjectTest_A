package Test_A;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AuthTest {
    @Test
    void getXAuthToken1 () {
        Object response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("username", "GB202303ebb5d19")
                .formParam("password", "d7cc7833a9")
                .when()
                .post("https://test-stand.gb.ru/gateway/login")
                .then().extract()
                .jsonPath()
                .get("token")
                .toString();
        System.out.println("API.Token: " + response);
    }
    @Test
    void noGetXAuthTokenWithInvalidLogin(){
        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("username","GB202303ebb5d191")
                .formParam("password","d7cc7833a9")
                .when()
                .post("https://test-stand.gb.ru/gateway/login")
                .then()
                .statusCode(401);
    }
    @Test
    void noGetXAuthTokenWithInvalidPassword (){
        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("username","GB202303ebb5d19")
                .formParam("password","1234")
                .when()
                .post("https://test-stand.gb.ru/gateway/login")
                .then()
                .statusCode(401);
    }
}
