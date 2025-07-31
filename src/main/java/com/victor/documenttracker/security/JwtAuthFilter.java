//check incoming request for a valid JWT and set user in the security context
package com.victor.documenttracker.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.victor.documenttracker.model.User;
import com.victor.documenttracker.repository.UserRepository;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
        throws ServletException, IOException {

        //Get Authorization header and check for bearer token
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); //remove "Bearer " prefix
            String username = jwtUtil.extractUsername(token); //extract username

            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                Optional<User> userOptional = userRepository.findByUsername(username);

                if(userOptional.isPresent()){
                    User user = userOptional.get();

                    //create authentication token using roles
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        user, null,
                        user.getRoles().stream()
                            .map(role -> new org.springframework.security.core.authority.SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList())
                    );

                    //add request details
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    //set authentication in context
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        //continue the chain
        filterChain.doFilter(request, response);
    }
}


