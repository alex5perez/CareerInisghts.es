package com.etrivium.backend.configuration;

import java.io.IOException;
import java.util.List;

import com.etrivium.backend.repository.entity.UserEntity;
import com.etrivium.backend.service.AllowedDomainsService;
import com.etrivium.backend.service.JwtService;
import com.etrivium.backend.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    JwtService jwtService;

    @Autowired
    UserService userService;

    @Autowired
    AllowedDomainsService allowedDomainsService;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {
            final String authHeader = request.getHeader("Authorization");
            final String origin = request.getHeader("Origin");
            final String jwt;
            final String userEmail;
            String requestURI = request.getRequestURI();
            List<String> excludedURIs = List.of("/api/auth/signup", "/api/auth/signin", "/api/auth/signin/key", "/api/hello");
            if (excludedURIs.contains(requestURI)) {
                filterChain.doFilter(request, response);
                return;
            }
            jwt = authHeader.substring(7);
            userEmail = jwtService.extractUserName(jwt);
            if (StringUtils.isNotEmpty(userEmail)
                    && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = userService.userDetailsService()
                        .loadUserByUsername(userEmail);

                if (isOriginAllowedForCompany(response, (UserEntity) userDetails, origin)) return;

                isTokenValid(request, jwt, userDetails);
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);

    }

    private void isTokenValid(HttpServletRequest request, String jwt, UserDetails userDetails) {
        if (jwtService.isTokenValid(jwt, userDetails)) {
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            context.setAuthentication(authToken);
            SecurityContextHolder.setContext(context);
            request.setAttribute("user", userDetails);
        }
    }

    private boolean isOriginAllowedForCompany(HttpServletResponse response, UserEntity userDetails, String origin) {
        UserEntity user = userDetails;
       // boolean isAllowed = allowedDomainsService.isOriginAllowedForCompany(origin, user.getCompany().getId());
        //if (!isAllowed && !origin.equalsIgnoreCase("https://prod.etrivium3l.com")) {
        //    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        //    return true;
        //}
        return false;
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        // Extract JWT from the Authorization header
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}