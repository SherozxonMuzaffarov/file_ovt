package com.uzvagontamir.File.config;

import com.uzvagontamir.File.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return  NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().and().cors().disable()
                .authorizeRequests().antMatchers("/").permitAll();


        http.antMatcher("/**")
                .authorizeRequests().anyRequest().hasAuthority("USER")
                .and()
                .formLogin()
                    .loginPage("/")
                    .usernameParameter("username")
                    .loginProcessingUrl("/")
                    .defaultSuccessUrl("/user/home")
                    .permitAll()
                .and()
                    .logout().logoutUrl("/user/logout")
                    .logoutSuccessUrl("/");
        return http.build();
    }
}


