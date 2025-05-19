package wisdom.zero.baseproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import wisdom.core.libraries.users.security.CookieFilter;
import wisdom.core.libraries.users.security.SecurityEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityEntryPoint securityEntryPoint;

    @Autowired
    private CookieFilter cookieFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(securityEntryPoint)
                .and()
                .addFilterBefore(cookieFilter, BasicAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/signin", "/signup", "/password", "/recoveryPassword").permitAll()
                .regexMatchers("^/swagger.*", "^/v3/api-docs.*").permitAll()
                .anyRequest().authenticated()
                .and().logout().disable();
    }
}

