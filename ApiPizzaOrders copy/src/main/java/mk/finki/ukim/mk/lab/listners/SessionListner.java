package mk.finki.ukim.mk.lab.listners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListner implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("[WP-Log] {sessionCreated}");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("[WP-Log] {sessionDestroied}");
    }
}
