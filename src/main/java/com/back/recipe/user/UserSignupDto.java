package com.back.recipe.user;

import lombok.Getter;
import lombok.Setter;

// RequestDto 프론트가 사용자가 입력한 값(json)을 db로 넣기 위해
@Getter
@Setter
public class UserSignupDto {
    private String userName;
    private String email;
    private String password;
}
