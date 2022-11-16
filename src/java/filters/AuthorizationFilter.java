package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // In order to use the getSession() method, we must have an HttpServletRequest object
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpSession session = httpRequest.getSession();
        String email = (String)session.getAttribute("email");
        if ( email == null ){
            // in order to use the sendRedirect method, we must have a HttpServletResponse object
            HttpServletResponse httpResponse = (HttpServletResponse)response;
            httpResponse.sendRedirect("login");
            return;
        }
        
        //any code before the chain.doFilter() method will execute before the next link in the chain
        
        // pass the request or response along to the next filter or servlet in the chain
        chain.doFilter(request, response);
        
        // any code after the chain.doFilter() method will execute during the response
        
        
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    @Override
    public void destroy() {}

}
