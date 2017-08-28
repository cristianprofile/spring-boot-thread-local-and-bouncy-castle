package com.example.bouncycastle.filter;

import com.example.bouncycastle.threadlocal.MyThreadLocal;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Component
public class CustomFilter implements Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {
        // nothing
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        if (req instanceof HttpServletRequest) {

            HttpServletRequest request = (HttpServletRequest) req;
            String correlationId  = UUID.randomUUID().toString();
            // add correlationId to the MDC

            MyThreadLocal myThreadLocal = new MyThreadLocal();
            myThreadLocal.startTransaction(correlationId);
            MDC.put("correlationId", correlationId);
        }

        try {
            // call filter(s) upstream for the real processing of the request
            chain.doFilter(req, res);
        } finally {
            // it's important to always clean correlationId from the MDC,
            // this Thread goes to the pool so another request using this thread would have this value.
            MDC.remove("correlationId");
            MyThreadLocal.endTransaction();
        }

    }

    @Override
    public void destroy() {
        // nothing
    }
}