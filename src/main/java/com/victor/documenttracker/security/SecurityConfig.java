package com.victor.documenttracker.security;

//import com.victor.documenttracker.security.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class SecurityConfig {
    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
            .csrf(csrf -> csrf.disable()) //disable csrf for stateless API
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))// no session
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll() // allow open access to login/register routes
                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN") //Only admins
                .requestMatchers("/user/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN") //users and admins
                .anyRequest().authenticated()// Everything else must be authenticated
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) //add jwt filter
            .build();
    }

    @Bean //provides bycrypt password
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
