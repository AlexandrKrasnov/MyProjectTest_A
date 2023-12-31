package Test_A;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

public class MainTest extends AbstractTest{

    @Test
    void requestToLookAtOtherPeoplesPostsWithoutSort() {
        JsonPath response = given()
                .spec(requestSpecification)
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .when()
                .get(getBaseUrl())
                .jsonPath();
        int id = response.get("data[1].id");
        assertThat(response.get("data[0].id"), lessThan(id));
    }

    @Test
    void requestToLookAtOtherPeoplesPostsLessToMore() {
        given()
                .queryParam("owner", "notMe")
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .queryParam("page", "1")
                .when()
                .get(getBaseUrl()+"api/posts")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void requestToLookAtOtherPeoplesPostsMoreToLess() {
        given()
                .queryParam("owner", "notMe")
                .queryParam("sort", "createdAt")
                .queryParam("order", "DESC")
                .queryParam("page", "1")
                .when()
                .get(getBaseUrl()+"api/posts")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void requestToLookAtALLOtherPeoplesPosts() {
        given()
                .queryParam("owner", "notMe")
                .queryParam("sort", "createdAt")
                .queryParam("order", "ALL")
                .queryParam("page", "1")
                .when()
                .get(getBaseUrl()+"api/posts")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void requestToLookAtOtherPeoplesPostsNonExistentPage() {
        given()
                .queryParam("owner", "notMe")
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .queryParam("page", "10000")
                .when()
                .get(getBaseUrl()+"api/posts")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void requestToLookAtOtherPeoplesPostsNonExistentPage2() {
        given()
                .queryParam("owner", "notMe")
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .queryParam("page", "999")
                .when()
                .get(getBaseUrl()+"api/posts")
                .then()
                .statusCode(200);
    }

    @Test
    void requestToLookMyPostsWithoutSort() {
        given()
                .queryParam("order", "ASC")
                .queryParam("page", "1")
                .when()
                .get(getBaseUrl()+"api/posts")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void requestToLookMyPostsLessToMore() {
        given()
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .queryParam("page", "1")
                .when()
                .get(getBaseUrl()+"api/posts")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void requestToLookMyPostsMoreToLess() {
        given()
                .queryParam("sort", "createdAt")
                .queryParam("order", "DESC")
                .queryParam("page", "1")
                .when()
                .get(getBaseUrl()+"api/posts")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void requestToLookMyPostsNonExistentPage() {
        given()
                .queryParam("sort", "createdAt")
                .queryParam("order", "ASC")
                .queryParam("page", "10000")
                .when()
                .get(getBaseUrl()+"api/posts")
                .then()
                .spec(responseSpecification);
    }

}
