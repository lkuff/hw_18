package tests;

import models.createUser.CreateUserBody;
import models.createUser.CreateUserResponse;
import models.loginUser.LoginUserBody;
import models.loginUser.LoginUserResponse;
import models.loginUser.UnsuccessfulLoginUserBody;
import models.loginUser.UnsuccessfulLoginUserResponse;
import models.registrationUser.RegistrationUserBody;
import models.registrationUser.RegistrationUserResponse;
import models.registrationUser.UnsuccessfulRegistrationBody;
import models.registrationUser.UnsuccessfulRegistrationUserResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.LoginSpecs.*;

public class ReqresApiWithModelsAndSpecTests {
    @Test
    public void checkLoginUser() {

        LoginUserBody data = new LoginUserBody();
        data.setEmail("eve.holt@reqres.in");
        data.setPassword("cityslicka");


        LoginUserResponse response = given(requestSpec)
                .body(data)
                .when()
                .post("/login")
                .then()
                .spec(loginResponseSpec)
                .extract().as(LoginUserResponse.class);
        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    public void checkUnsuccesLoginUser() {

        UnsuccessfulLoginUserBody data = new UnsuccessfulLoginUserBody();
        data.setEmail("eve.holt@reqres.i");
        data.setPassword("cityslicka");

        UnsuccessfulLoginUserResponse response = given(requestSpec)
                .body(data)
                .when()
                .post("/login")
                .then()
                .spec(unsuccessfulLoginResponseSpec)
                .extract().as(UnsuccessfulLoginUserResponse.class);
        assertThat(response.getError()).isEqualTo("user not found");
    }

    @Test
    public void checkRegisterUser() {

        RegistrationUserBody data = new RegistrationUserBody();
        data.setEmail("eve.holt@reqres.in");
        data.setPassword("pistol");

        RegistrationUserResponse response = given(requestSpec)
                .body(data)
                .when()
                .post("/register")
                .then()
                .spec(registrationResponseSpec)
                .extract().as(RegistrationUserResponse.class);
        assertThat(response.getId()).isEqualTo("4");
        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    public void checkUnsuccesRegisterUser() {

        UnsuccessfulRegistrationBody data = new UnsuccessfulRegistrationBody();
        data.setEmail("eve.holt@reqres.i");
        data.setPassword("pistol");

        UnsuccessfulRegistrationUserResponse response = given(requestSpec)
                .body(data)
                .when()
                .post("/register")
                .then()
                .spec(unsuccessfulRegistrationResponseSpec)
                .extract().as(UnsuccessfulRegistrationUserResponse.class);
        assertThat(response.getError()).isEqualTo("Note: Only defined users succeed registration");
    }

    @Test
    public void checkCreateUser() {

        CreateUserBody data = new CreateUserBody();
        data.setName("chamomile");
        data.setJob("qa");

        CreateUserResponse response = given(requestSpec)
                .body(data)
                .when()
                .post("/users")
                .then()
                .spec(createUserResponseSpec)
                .extract().as(CreateUserResponse.class);
        assertThat(response.getName()).isEqualTo("chamomile");
        assertThat(response.getJob()).isEqualTo("qa");
    }
}
