package mk.finki.ukim.mk.lab.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class SelectedFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpResp = (HttpServletResponse) servletResponse;
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        System.out.println("[WP-Log]" + httpRequest.getMethod() + " " + httpRequest.getServletPath());
        String path = httpRequest.getServletPath();
        String size = (String) httpRequest.getSession().getAttribute("pizzaSize");
        String clientName = (String)  httpRequest.getSession().getAttribute("clientName");
        String clientAddress = (String)  httpRequest.getSession().getAttribute("clientAddress");
        if(path.equals("/PizzaOrder.do") && size == null){
            httpResp.sendRedirect("/selectPizza.do");
        } else if(path.equals("/ConfirmationInfo.do") && clientName.equals("") && clientAddress.equals("")){
            httpResp.sendRedirect("/PizzaOrder.do");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
