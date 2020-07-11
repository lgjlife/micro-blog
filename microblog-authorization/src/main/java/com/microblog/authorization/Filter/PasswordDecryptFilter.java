package com.microblog.authorization.Filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Order(0)
@Component
public class PasswordDecryptFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;


        HttpServletRequestWrapper servletRequestWrapper = new RemodifyPasswordHttpServletRequestWrapper(servletRequest);

        filterChain.doFilter(servletRequestWrapper,servletResponse);
    }

    private class RemodifyPasswordHttpServletRequestWrapper extends HttpServletRequestWrapper {

        private HttpServletRequest request;

        private Map<String, String[]> params = new HashMap<>();


        public RemodifyPasswordHttpServletRequestWrapper(HttpServletRequest request) {
            super(request);
            request = request;

        }

        private void init(){

            Enumeration<String> names =   request.getParameterNames();

            while (names.hasMoreElements()){
                String name = names.nextElement();


            }
        }

        @Override
        public String getParameter(String name) {
            return super.getParameter(name);
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            return super.getParameterMap();
        }

        @Override
        public Enumeration<String> getParameterNames() {
            return super.getParameterNames();
        }
    }

}
