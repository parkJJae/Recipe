package com.back.recipe.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignupDto userSignupDto) {
        userService.registerUser(
                userSignupDto.getEmail(),
                userSignupDto.getPassword(),
                userSignupDto.getUserName()
        );
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }
}
