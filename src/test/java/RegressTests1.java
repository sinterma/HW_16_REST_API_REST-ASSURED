import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class RegressTests1 {


    @Test
    void successfulLogin() {

        given()
                .contentType(JSON)
                .body("{\"email\": \"eve.holt@reqres.in\",\"password\": \"cityslicka\"}")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void singleUser() {
        given()
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200);
    }

    @Test
    void registerSuccessful() {
        given()
                .contentType(JSON)
                .body("{\"email\": \"eve.holt@reqres.in\",\"password\": \"pistol\"}")
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(200)
                .body("id", is(4), "token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void updateSuccessful() {
        given()
                .contentType(JSON)
                .body("{\"name\": \"morpheus\",\"job\": \"zion resident\"}")
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("name", is("morpheus"),
                        "job", is("zion resident"));

    }

    @Test
    void successfulDelete() {
        given()
                .contentType(JSON)
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204);
    }

    @Test
    void successfulCreate() {
        given()
                .contentType(JSON)
                .body("{\"name\": \"morpheus\",\"job\": \"leader\"}")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", is("morpheus"),
                        "job", is("leader"));
    }

}