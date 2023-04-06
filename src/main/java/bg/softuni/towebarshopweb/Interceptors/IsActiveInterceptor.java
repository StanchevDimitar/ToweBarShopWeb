package bg.softuni.towebarshopweb.Interceptors;

import bg.softuni.towebarshopweb.model.entity.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import bg.softuni.towebarshopweb.service.UserService;

@Component
public class IsActiveInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (handlerMethod.getMethod().isAnnotationPresent(PostMapping.class)) {
                if (request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null) {
                    String username = request.getUserPrincipal().getName();
                    UserEntity user = userService.getUserByUsername(username);
                    if (!user.getActive()) {
                        response.sendRedirect(request.getContextPath() + "/login?error=disabled");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // No action needed
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        // No action needed
    }
}