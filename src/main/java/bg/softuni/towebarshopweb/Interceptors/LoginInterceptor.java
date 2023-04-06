package bg.softuni.towebarshopweb.Interceptors;

import bg.softuni.towebarshopweb.model.entity.UserEntity;
import bg.softuni.towebarshopweb.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

@Service
public class LoginInterceptor implements HandlerInterceptor {
    private final UserService userService;

    public LoginInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String username = request.getParameter("username");
        UserEntity user = userService.getUserByUsername(username);

        if (user != null && !user.getActive()) {
            // User is inactive, set the inactive flag on the user object in the session
            request.getSession().setAttribute("inactiveUser", true);
            // Redirect to the login page with an error message
            response.sendRedirect(request.getContextPath() + "/users/login-error");
            return false;
        }

        // User is active, remove the inactive flag from the session
        request.getSession().removeAttribute("inactiveUser");
        return true;
    }
}