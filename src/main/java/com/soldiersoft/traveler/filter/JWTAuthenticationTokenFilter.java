package com.soldiersoft.traveler.filter;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTHeader;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JWTAuthenticationTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // 从请求头中获取JWT Token
            String token = extractTokenFromRequest(request);

            if (token != null) {
                // 解析JWT Token
                byte[] key = "1234567890".getBytes();
                final JWTSigner signer = JWTSignerUtil.hs256(key);
                final JWT jwt = JWTUtil.parseToken(token).setSigner(signer);

                // 从Token中获取用户名等信息
                jwt.getHeader(JWTHeader.TYPE);
                Object username = jwt.getPayload("username");
                List<SimpleGrantedAuthority> authorities = convertToListOfString(
                        (List<?>) jwt.getPayload("authorities")).stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList();

                // 验证是否有效
                boolean validate = jwt.validate(0);
                if (validate) {
                    // 在Spring Security中设置认证信息
                    Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                }
            }
        } catch (Exception e) {
            // 处理验证失败的情况
            SecurityContextHolder.clearContext(); // 清除认证信息
            response.setStatus(HttpStatus.UNAUTHORIZED.value()); // 设置未认证状态
            response.getWriter().write("Authentication Failed: " + e.getMessage()); // 输出错误信息
            return;
        }
        filterChain.doFilter(request, response);
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        // 从请求头中获取JWT Token
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // 去除"Bearer "前缀
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static List<String> convertToListOfString(List<?> list) {
        return (List<String>) list;
    }
}