package io.eaterythem.eaterythem.security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.UUID;

import io.eaterythem.eaterythem.security.UserPrincipal;
import io.eaterythem.eaterythem.security.jwt.JwtUtil;
import io.eaterythem.eaterythem.service.CustomUserDetailsService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        String token = null;
        String username = null;
        UUID userId = null;
        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
            if (header != null && header.startsWith("Bearer ")) {
                token = header.substring(7);
                try {
                    if (jwtUtil.validateJwtToken(token)) {
                        username = jwtUtil.getUsernameFromToken(token);
                        userId = UUID.fromString(jwtUtil.getUserIdFromToken(token));
                    }
                } catch (Exception ex) {
                    System.out.println("JWT validation failed: " + ex.getMessage());
                }
            }

        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UserPrincipal userPrincipal = new UserPrincipal(userId, userDetails.getUsername(),
                    userDetails.getPassword(), userDetails.getAuthorities());

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    userPrincipal, null, userDetails.getAuthorities());

            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }
}