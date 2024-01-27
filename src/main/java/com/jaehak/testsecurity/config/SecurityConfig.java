package com.jaehak.testsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //스프링 시큐리티는 사용자 인증(로그인)시 비밀번호에 대해 단방향 해시 암호화를 진행하여 저장되어 있는 비밀번호와 대조한다.
    //따라서 회원가입시 비밀번호 항목에 대해서 암호화를 진행해야 한다.
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((auth)-> auth
                        .requestMatchers("/","/login", "join").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                );

        http
                .formLogin((auth) -> auth.loginPage("/login") // ex) /admin 접속시 설정한 로그인 페이지(/login)로 redirect
                        .loginProcessingUrl("/loginPost") // post방식으로 로그인 정보 입력시 security가 처리
                        .permitAll() // post방식의 /loginPost(설정 값)으로 아무나 접근가능 즉 직접 post로 접근하여도 접근가능하다는 뜻
                );

        http
                .csrf((auth) -> auth.disable()); // 사이트 위변조 방지 설정(실제로 이 토큰도 같이 보내주어야 로그인 됨 -> 개발환경에서만 잠시 disable)




        return http.build();
    }
}
