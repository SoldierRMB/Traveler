package com.soldiersoft.traveler.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class JsonLoginFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper mapper = new ObjectMapper();
        UsernamePasswordAuthenticationToken authRequest;
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            try (InputStream inputStream = request.getInputStream()) {
                HashMap<?, ?> authenticationBean = mapper.readValue(inputStream, HashMap.class);
                authRequest = new UsernamePasswordAuthenticationToken(
                        authenticationBean.get("username"), authenticationBean.get("password"));
                setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            } catch (IOException e) {
                authRequest = new UsernamePasswordAuthenticationToken(
                        null, null, null);
                return this.getAuthenticationManager().authenticate(authRequest);
            }
        }
        return super.attemptAuthentication(request, response);
    }
}
