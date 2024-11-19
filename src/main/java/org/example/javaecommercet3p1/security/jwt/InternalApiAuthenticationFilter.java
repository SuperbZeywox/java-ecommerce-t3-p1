package org.example.javaecommercet3p1.security.jwt;

import org.example.javaecommercet3p1.security.UserPrincipal;
import org.example.javaecommercet3p1.utils.SecurityUtils.SecurityUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
@Slf4j
public class InternalApiAuthenticationFilter extends OncePerRequestFilter {

    private final String accessKey;


    @Override
    protected boolean shouldNotFilter(
            HttpServletRequest request) throws ServletException {
        return !request.getRequestURI().startsWith("/api/internal");
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        try {
            String requestKey = SecurityUtils
                    .extractAuthTokenFromRequest(request);
            if (requestKey == null || !requestKey.equals(accessKey)) {

                log.warn("Internal key endpoint requested without/wrong" +
                                " key uri: {}",
                        request.getRequestURI());
                throw new RuntimeException("UNAUTHORIZED");
            }
            UserPrincipal user = UserPrincipal.createSuperUser();
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (RuntimeException e) {
            log.error("Could not set user authentication " +
                    "in security context",e);
        }
        filterChain.doFilter(request, response);


    }
}

