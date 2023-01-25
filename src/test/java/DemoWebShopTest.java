import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DemoWebShopTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://demowebshop.tricentis.com";
    }

    @Test
    void subscribeNewsLetterTest() {
        String cookieValue = "A9AD3A87DD24D82DD3D0AD7C5F651D1EFF9F17EC6D085DD115B46ECAB0790BA4207674DA5" +
                "B09725C97176BC5D8FF273E47EF8AF0F683CC5727231C8FA561EB0EBDA54648027FBEA48956AC808F21BE4" +
                "C5235CEF13FC1909D3A485B77819AEB4E4B79BED51689A4D8A57E4E317A02F44C2A788C20AD4902BD9DEAC6D" +
                "B437F25D444FF0F54095FEC794BC6CA291B878601;",
                body = "email=leshka@40mail.com";

        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("NOPCOMMERCE.AUTH", cookieValue)
                .body(body)
                .when()
                .post("/subscribenewsletter")
                .then()
                .log().all()
                .statusCode(200)
                .body("Success", is(true))
                .body("Result", is("Thank you for signing up! A verification email has been sent. We appreciate your interest."));
    }
}
