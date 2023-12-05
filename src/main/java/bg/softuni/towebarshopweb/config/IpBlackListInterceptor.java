package bg.softuni.towebarshopweb.config;

import bg.softuni.towebarshopweb.service.BlackListService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.View;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import java.util.Locale;
import java.util.Map;

@Component
public class IpBlackListInterceptor implements HandlerInterceptor {

    private final BlackListService service;
    private final ThymeleafViewResolver tlvr;

    public IpBlackListInterceptor(BlackListService service,
                                  ThymeleafViewResolver tlvr) {
        this.service = service;
        this.tlvr = tlvr;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler)
            throws Exception {

        if (service.isBlacklisted()) {
            View blockedView = tlvr.resolveViewName("blocked", Locale.getDefault());
            if (blockedView != null) {
                blockedView.render(Map.of(), request, response);
            }
            return false;
        }

        return true;
    }
}
