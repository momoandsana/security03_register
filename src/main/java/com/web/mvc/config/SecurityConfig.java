package com.web.mvc.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig{

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        log.info("BCryptPasswordEncoder");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("SecurityFilterChain");
        http
                .csrf((auth)->auth.disable())
                /*
                csrf 공격은 주로 인증쿠키를 이용하는데 jwt 기반 인증에서는 authorization 헤더에
                jwt 토큰을 포함하여 요청하기 때문에 브라우저가 자동으로 쿠키를 첨부하지 않는다
                그래서 여기에서 disable 한다
                */
                .formLogin((auth)->auth.disable())
                .httpBasic((auth)->auth.disable());
        /*
        http 기본 인증은 사용자 자격 증명을 매번 클라이언트에 보낼 때 암호화되지 않은 형태로
        보내게 되기 때문에 보안적인 문제가 있음
        그리고 어차피 나중에 jwt 사용할 예정이기 때문에 비활성화함
         */

        http.authorizeHttpRequests((auth)->auth
                .requestMatchers("/index","/members","/members/**","/boards").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated());
        return http.build();
    }
}
