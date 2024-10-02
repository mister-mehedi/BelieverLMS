package com.example.lms_cse327.SecurityConfig;

import com.example.lms_cse327.Services.CustomOauth2UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class OauthConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomOauth2UserServices oauthUserService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                  .antMatchers("/", "/login", "/oauth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(oauthUserService)
                .and().successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
                        response.sendRedirect("/home");
                    }
                });


    }

}