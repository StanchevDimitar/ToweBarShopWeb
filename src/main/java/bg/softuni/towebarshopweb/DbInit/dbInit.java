package bg.softuni.towebarshopweb.DbInit;

import bg.softuni.towebarshopweb.model.entity.*;
import bg.softuni.towebarshopweb.model.enums.RoleNameEnum;
import bg.softuni.towebarshopweb.repository.*;
import bg.softuni.towebarshopweb.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class dbInit implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final TowBarRepository towBarRepository;

    public dbInit(RoleRepository roleRepository, UserService userService, UserRepository userRepository, CartItemRepository cartItemRepository, TowBarRepository towBarRepository) {
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.towBarRepository = towBarRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (roleRepository.count() <= 0) {
            Arrays.stream(RoleNameEnum.values()).forEach(r -> {
                        RoleEntity role = new RoleEntity();

                        role.setRole(r);

                        roleRepository.save(role);
                    }

            );
        }

        if (userRepository.count() == 1) {
            List<UserEntity> all = userRepository.findAll();
            UserEntity userEntity = all.get(0);
            userService.addRoleToUserById(userEntity.getId(), RoleNameEnum.ADMIN);
            userRepository.save(userEntity);
        }

    }
}

