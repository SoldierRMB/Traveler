package com.soldiersoft.traveler.config;

import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.soldiersoft.traveler.filter.JWTAuthenticationTokenFilter;
import com.soldiersoft.traveler.filter.JsonLoginFilter;
import com.soldiersoft.traveler.model.vo.ResultVO;
import com.soldiersoft.traveler.model.vo.UserDetailsVO;
import com.soldiersoft.traveler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {
    private final UserService userService;
    private final JWTAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    public WebSecurityConfig(UserService userService, JWTAuthenticationTokenFilter jwtAuthenticationTokenFilter) {
        this.userService = userService;
        this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers(WHITE_LIST).permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/staff/**").hasRole("STAFF")
                        .requestMatchers("/tourist/**").hasRole("TOURIST")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(conf -> conf
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setContentType("application/json;charset=utf-8");
                            response.getWriter().println(JSONUtil.toJsonStr(ResultVO.fail(HttpStatus.UNAUTHORIZED.value(), "授权失败")));
                        })
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setContentType("application/json;charset=utf-8");
                            response.getWriter().println(JSONUtil.toJsonStr(ResultVO.fail(HttpStatus.FORBIDDEN.value(), "禁止访问")));
                        })
                )
                .logout(conf -> conf
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setContentType("application/json;charset=utf-8");
                            response.getWriter().println(JSONUtil.toJsonStr(ResultVO.ok("注销成功")));
                        })
                )
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterAt(jsonLoginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private static final String[] WHITE_LIST = {
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        return new ProviderManager(provider);
    }

    @Bean
    JsonLoginFilter jsonLoginFilter() {
        JsonLoginFilter filter = new JsonLoginFilter();
        filter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            Map<String, Object> payload = new HashMap<>() {
                {
                    LocalDateTime now = LocalDateTime.now();
                    LocalDateTime expireTime = now.plusDays(15);
                    //签发时间
                    put(JWTPayload.ISSUED_AT, now);
                    //过期时间
                    put(JWTPayload.EXPIRES_AT, expireTime);
                    //生效时间
                    put(JWTPayload.NOT_BEFORE, now);
                }
            };
            Map<String, Object> user = new HashMap<>();
            UserDetailsVO userDetailsVO = (UserDetailsVO) authentication.getPrincipal();
            List<String> authorities = userDetailsVO.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
            user.put("username", userDetailsVO.getUsername());
            user.put("authorities", authorities);
            payload.putAll(user);
            byte[] key = "1234567890".getBytes();
            final JWTSigner signer = JWTSignerUtil.hs256(key);
            String token = JWTUtil.createToken(payload, signer);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().println(JSONUtil.toJsonStr(ResultVO.ok(token)));
        });
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_STAFF \n ROLE_ADMIN > ROLE_TOURIST");
        return roleHierarchy;
    }
}
