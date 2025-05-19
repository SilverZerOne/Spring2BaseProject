package wisdom.zero.baseproject.config;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@Slf4j
public class DebugFilter extends OncePerRequestFilter {

    @Autowired
    private Gson gson;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // ONLY IF USERS LIBRARY IS PRESENT
        /*var sc = SecurityContextHolder.getContext().getAuthentication();

        log.info("Verb: " + request.getMethod());
        log.info("Request: " + request.getRequestURI());

        try{
            var session = (Session) sc.getPrincipal();
            User user = new User();
            user.setIpAddress(HttpUtils.getIp(request));
            user.setUsername(session.getId());
            Sentry.setUser(user);
            log.info("User: " + session.getId());
        }catch (ClassCastException e){
            Sentry.setUser(null);
        }

        if(request.getHeader("X-Transaction-Id") != null){
            Sentry.setTag("transaction_id", request.getHeader("X-Transaction-Id"));
            log.info("transaction_id: " + request.getHeader("X-Transaction-Id"));
        }

        filterChain.doFilter(request, response);*/

    }
}
