package models.registrationUser;

import lombok.Data;

@Data
public class UnsuccessfulRegistrationBody {
    private String email, password;
}
