package org.example.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LoggingFilter implements Filter {

    private static final Logger logger =
            LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        logger.info(
                "[Filter] Request: {} {}",
                httpRequest.getMethod(),
                httpRequest.getRequestURI()
        );

        chain.doFilter(request, response);

        logger.info(
                "[Filter] Response: {} {}",
                httpRequest.getMethod(),
                httpRequest.getRequestURI()
        );
    }
}