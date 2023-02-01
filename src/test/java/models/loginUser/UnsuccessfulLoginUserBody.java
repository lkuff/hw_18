package models.loginUser;

import lombok.Data;

@Data
public class UnsuccessfulLoginUserBody {
    private String email, password;
}
