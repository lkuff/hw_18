package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ReqresApiTests {

    @Test
    public void checkUserName() {

        given()
                .when()
                .log().uri()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"));
    }

    @Test
    public void checkNotFoundUser() {

        given()
                .when()
                .log().uri()
                .get("https://reqres.in/api/users/23")
                .then()
                .log().all()
                .statusCode(404)
                .body(is("{}"));
    }

    @Test
    public void checkDeleteUser() {

        given()
                .when()
                .log().uri()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(204);
    }

    @Test
    public void checkLoginUser() {

        String data = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

        given()
                .when()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .statusCode(200)
                .body("token", is(notNullValue()));
    }

    @Test
    public void checkUnsuccesLoginUser() {

        String data = "{ \"email\": \"eve.holt@reqres.i\", \"password\": \"cityslicka\" }";

        given()
                .when()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .statusCode(400)
                .body("error", is("user not found"));
    }

    @Test
    public void checkRegisterUser() {

        String data = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";

        given()
                .when()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .post("https://reqres.in/api/register")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", is(notNullValue()))
                .body("token", is(notNullValue()));
    }

    @Test
    public void checkUnsuccesRegisterUser() {

        String data = "{ \"email\": \"eve.holt@reqres.i\", \"password\": \"pistol\" }";

        given()
                .when()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .post("https://reqres.in/api/register")
                .then()
                .log().all()
                .statusCode(400)
                .body("error", is("Note: Only defined users succeed registration"));
    }

    @Test
    public void checkCreateUser() {

        String data = "{ \"name\": \"chamomile\", \"job\": \"qa\" }";

        given()
                .when()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .post("https://reqres.in/api/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("name", is(notNullValue()))
                .body("job", is(notNullValue()))
                .body("id", is(notNullValue()))
                .body("createdAt", is(notNullValue()));
    }
}
