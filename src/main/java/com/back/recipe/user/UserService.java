package com.back.recipe.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(String email, String password, String userName) {
        // 1. 이메일 양식 검사 및 중복 검사
        if(userRepository.existsByEmail(email)) {
            throw new RuntimeException("이미 가입된 이메일입니다.");
        }

        // 2. 비밀번호 가공 (암호화)
        String encodedPassword = passwordEncoder.encode(password);

        // 3. 가입 시간 도장 찍기 및 빈 상자(Entity)에 담아서 창고(DB)에 저장!
        UserEntity user = new UserEntity();
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
    }
}
