package com.back.recipe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // "스프링아, 이거 환경설정 파일이야!"
@EnableWebSecurity // "스프링 시큐리티 요원아, 내 지시대로 움직여!"
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // 1. 구체적인 예외들을 먼저 쫙 나열합니다. (스웨거, 회원가입 등)
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/user/signup").permitAll()

                        // 2. "이 위에서 언급 안 된 '나머지 모든 주소'는 전부 출입증 검사해!"
                        .anyRequest().authenticated()
                )
                // 2. "CSRF 보안 검사는 일단 꺼둬!" (초보자 에러의 주범 1순위)
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 3. 믹서기 모델명: BCrypt (현재 실무에서 가장 많이 쓰는 강력한 암호화 기계)
        return new BCryptPasswordEncoder();
    }
}