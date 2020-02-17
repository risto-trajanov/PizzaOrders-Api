package mk.finki.ukim.mk.lab.listners;

import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListner extends ContextLoaderListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("[WP-Log] {contextInit}");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("[WP-Log] {contextDestroied}");
    }
}
