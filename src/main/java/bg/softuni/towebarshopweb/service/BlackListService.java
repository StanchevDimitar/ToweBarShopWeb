package bg.softuni.towebarshopweb.service;

import bg.softuni.towebarshopweb.model.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class BlackListService {

    private final UserService userService;

    public BlackListService(UserService userService) {
        this.userService = userService;
    }

    public boolean isBlacklisted() {
        UserEntity currentlyLoggedInUser = userService.getCurrentlyLoggedInUser();
        if (currentlyLoggedInUser != null) {
            return !currentlyLoggedInUser.getActive();
        }
        return false;
    }

}