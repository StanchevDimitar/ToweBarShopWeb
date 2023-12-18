package bg.softuni.towebarshopweb.config;

import bg.softuni.towebarshopweb.config.Security.CustomAccessDeniedHandler;
import bg.softuni.towebarshopweb.repository.UserRepository;
import bg.softuni.towebarshopweb.service.AppUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.stereotype.Component;

import static org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive.*;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           SecurityContextRepository securityContextRepository) throws Exception {

        String[] staticResources = {
                "/css/**",
                "/img/**",
                "/fonts/**",
                "/js/**",
                "/info/**"
        };

        http.
                // defines which pages will be authorized
                        authorizeHttpRequests().
                // allow access to all static files (images, CSS, js)
                        requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().

                requestMatchers("/", "/quick-order").permitAll().
                requestMatchers("/info").permitAll().
                requestMatchers("/add-product/*").hasAnyRole("ADMIN", "MODERATOR").
                requestMatchers("/address-info/*").authenticated().
                requestMatchers("/cart/*").authenticated().
                requestMatchers("my-profile").authenticated().
                requestMatchers("/api").authenticated().
                requestMatchers("/shop/*").authenticated().
                // the URL-s below are available for all users - logged in and anonymous
                        requestMatchers("/users/login", "/users/register").anonymous().
                requestMatchers("/admin/*").hasRole("ADMIN").
                requestMatchers("/all-orders").hasAnyRole("ADMIN", "MODERATOR").
                requestMatchers(staticResources).permitAll().
                anyRequest().authenticated().
                and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler()).
                and().
                // configure login with HTML form
                        formLogin().
                loginPage("/users/login").
                // the names of the username, password input fields in the custom login form
                        usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                // where do we go after login
                        defaultSuccessUrl("/", true).//use true argument if you always want to go there, otherwise go to previous page
                failureForwardUrl("/users/login-error").
                and().logout().deleteCookies("JSESSIONID").//configure logout
                logoutUrl("/users/logout").
                logoutSuccessUrl("/").//go to homepage after logout
                invalidateHttpSession(true).
                and().
                rememberMe().key("topSecretKey").tokenValiditySeconds(86400).
                and().
                securityContext().
                securityContextRepository(securityContextRepository);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new AppUserDetailsService(userRepository);
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository()
        );
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }


    private static final ClearSiteDataHeaderWriter.Directive[] SOURCE =
            {CACHE, COOKIES, STORAGE, EXECUTION_CONTEXTS};

    @Bean
    public SecurityFilterChain logOutUser(HttpSecurity http) throws Exception {
        http
                .logout(logout -> logout
                        .logoutUrl("/users/logout")
                        .addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(SOURCE)))
                        .logoutSuccessUrl("/users/login")
                );
        return http.build();
    }

}
