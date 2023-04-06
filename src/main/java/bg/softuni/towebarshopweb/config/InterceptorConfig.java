package bg.softuni.towebarshopweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    private final IpBlackListInterceptor ipBlacklistInterceptor;

    public InterceptorConfig(IpBlackListInterceptor ipBlacklistInterceptor) {
        this.ipBlacklistInterceptor = ipBlacklistInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ipBlacklistInterceptor).addPathPatterns("/**").excludePathPatterns("/users/logout");;;
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
