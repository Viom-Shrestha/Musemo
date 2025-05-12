package com.musemo.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.musemo.util.CookieUtil;

@WebFilter(asyncSupported = true, urlPatterns = "/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String path = uri.substring(contextPath.length());

        // Skip resources
        if (path.endsWith(".png") || path.endsWith(".jpg") || path.endsWith(".jpeg") || 
            path.endsWith(".gif") || path.endsWith(".css") || path.endsWith(".js")) {
            chain.doFilter(request, response);
            return;
        }

        String userRole = CookieUtil.getCookie(req, "role") != null ? 
            CookieUtil.getCookie(req, "role").getValue() : null;

        // Public routes
        if (path.equals("/login") || path.equals("/register") || path.equals("/home") || 
            path.equals("/") || path.equals("/about") || path.equals("/contact") || 
            path.equals("/exhibition") || path.equals("/artifact") || 
            path.equals("/exhibitionDetails") || path.equals("/artifactDetails")) {
            chain.doFilter(request, response);
            return;
        }

        // Admin access
        if ("Admin".equals(userRole)) {
            if (path.equals("/dashboard") || path.equals("/adminProfile") || 
                path.equals("/userManagement") || path.equals("/artifactManagement") || 
                path.equals("/exhibitionManagement") || path.equals("/logout")) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(contextPath + "/dashboard");
            }
            return;
        }

        // User access
        if ("User".equals(userRole)) {
            if (path.equals("/booking") || path.equals("/profile") || path.equals("/logout")) {
                chain.doFilter(request, response);
            } else if (path.startsWith("/admin")) {
                res.sendRedirect(contextPath + "/home");
            } else {
                res.sendRedirect(contextPath + "/home");
            }
            return;
        }

        // Unauthenticated
        res.sendRedirect(contextPath + "/login");
    }
}