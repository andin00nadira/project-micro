package com.jasmine.order.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

        private final JwtUtil jwtUtil;

        @Override
        protected boolean shouldNotFilter(HttpServletRequest request) {
                return !request.getServletPath().startsWith("/api/order");
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request,
                        HttpServletResponse response,
                        FilterChain filterChain)
                        throws ServletException, IOException {

                String header = request.getHeader("Authorization");

                if (header == null || !header.startsWith("Bearer ")) {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                                        "Token tidak ditemukan");
                        return;
                }

                String token = header.substring(7);

                try {
                        jwtUtil.validateToken(token);

                        String username = jwtUtil.extractUsername(token);
                        String email = jwtUtil.extractEmail(token);
                        String role = jwtUtil.extractRole(token);

                        List<GrantedAuthority> authorities = List.of(
                                        new SimpleGrantedAuthority("ROLE_" + role));

                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                        username,
                                        null,
                                        authorities);

                        authentication.setDetails(
                                        new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authentication);

                        request.setAttribute("username", username);
                        request.setAttribute("email", email);
                        request.setAttribute("role", role);

                } catch (ExpiredJwtException e) {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                                        "Token telah kedaluwarsa");
                        return;
                } catch (JwtException e) {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                                        "Token tidak valid");
                        return;
                }

                filterChain.doFilter(request, response);
        }
}
