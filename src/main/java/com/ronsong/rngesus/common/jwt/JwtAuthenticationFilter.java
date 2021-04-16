package com.ronsong.rngesus.common.jwt;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final PathMatcher PATH_MATCHER = new AntPathMatcher();
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            if (isProtectedUrl(httpServletRequest) && !"OPTIONS".equals(httpServletRequest.getMethod())) {
                httpServletRequest = JwtUtil.validateTokenAndAddUserIdToHeader(httpServletRequest);
            }
        } catch (Exception e) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private boolean isProtectedUrl(HttpServletRequest httpServletRequest) {
        List<String> paths = new ArrayList<>();
        paths.add("/user/info");
        paths.add("/post/create");

        boolean flag = false;

        for (String path : paths) {
            flag = PATH_MATCHER.match(path, httpServletRequest.getServletPath());
            if (flag) {
                break;
            }
        }
        return flag;
    }
}