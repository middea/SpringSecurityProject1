package kz.yerbolov.MyJavaProject.config;

import kz.yerbolov.MyJavaProject.services.MyUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public MyUserService userService(){
        return new MyUserService();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userService())
                .passwordEncoder(passwordEncoder());
        http.formLogin()
                .loginPage("/bank/login")
                .loginProcessingUrl("/sign-in")
                .usernameParameter("user-email")
                .passwordParameter("user-password")
                .defaultSuccessUrl("/bank/home")
                .failureUrl("/bank/login?error");
        http.logout()
                        .logoutUrl("/logout")
                                .logoutSuccessUrl("/bank/home");
        http.csrf().disable();
        return http.build();
    }
}
