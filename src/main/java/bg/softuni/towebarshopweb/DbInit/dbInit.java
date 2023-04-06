package bg.softuni.towebarshopweb.DbInit;

import bg.softuni.towebarshopweb.model.entity.*;
import bg.softuni.towebarshopweb.model.enums.ElectricalSystemType;
import bg.softuni.towebarshopweb.model.enums.RoleNameEnum;
import bg.softuni.towebarshopweb.model.enums.TowBarType;
import bg.softuni.towebarshopweb.repository.*;
import bg.softuni.towebarshopweb.service.AppUserDetailsService;
import bg.softuni.towebarshopweb.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class dbInit implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final PartRepository partRepository;
    private final TowBarRepository towBarRepository;
    private final ElectricalInstallationRepository electricalInstallationRepository;

    public dbInit(RoleRepository roleRepository, UserService userService, UserRepository userRepository, CartItemRepository cartItemRepository, PartRepository partRepository, TowBarRepository towBarRepository, ElectricalInstallationRepository electricalInstallationRepository) {
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.partRepository = partRepository;
        this.towBarRepository = towBarRepository;
        this.electricalInstallationRepository = electricalInstallationRepository;
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



//        Optional<UserEntity> mitko = userRepository.findByUsername("mitko");
//        if (mitko.isPresent()){
//            UserEntity userEntity = mitko.get();
//            RoleNameEnum role = RoleNameEnum.ADMIN;
//            RoleEntity byRole = roleRepository.findByRole(role);
//            if (!userEntity.getRoles().contains(byRole)) {
//                userEntity.addRole(byRole);
//                userRepository.save(userEntity);
//            }
//
//
//        }


//       Part part1 = new Part("01",8);
//       Part part2 = new Part("02",8);
//       Part part3 = new Part("03",8);
//       partRepository.save(part1);
//       partRepository.save(part2);
//       partRepository.save(part3);
//       TowBar towBar1 = new TowBar("name",TowBarType.DETACHABLE,List.of(part1,part2));
//       TowBar towBar2 = new TowBar("name2",TowBarType.FIXED,List.of(part3));
//       towBarRepository.save(towBar1);
//       towBarRepository.save(towBar2);
//

//        CartItem cartItem1 = new CartItem(user,towBar1,2);
//        CartItem cartItem2 = new CartItem(user,towBar2,6);
//
//        cartItemRepository.save(cartItem1);
//        cartItemRepository.save(cartItem2);
//
//        TowBar name = towBarRepository.findByName("name");
//        name.setPrice(BigDecimal.valueOf(69));
//        TowBar name2 = towBarRepository.findByName("name2");
//        name2.setPrice(BigDecimal.valueOf(3));
//        towBarRepository.save(name2);
//        towBarRepository.save(name);
//
//        ElectricalInstallation electricalInstallation = new ElectricalInstallation();
//        electricalInstallation.setType(ElectricalSystemType.ONLY_CABLES);
//        electricalInstallation.setPrice(BigDecimal.TEN);
//        name.setElectricalInstallation(electricalInstallation);
//        electricalInstallationRepository.save(electricalInstallation);
//        name2.setElectricalInstallation(electricalInstallation);
//        towBarRepository.save(name);
//        towBarRepository.save(name2);
//        name.setElectricalInstallation(null);
//        towBarRepository.save(name);

//
//        TowBar towBar = new TowBar();
//        towBar.setName("first");
//        towBar.setPrice(BigDecimal.TEN);
//        towBar.setType(TowBarType.FIXED);
//        TowBar towBar1 = new TowBar();
//        towBar1.setPrice(BigDecimal.ONE);
//        towBar1.setName("second");
//        towBar1.setType(TowBarType.DETACHABLE);
//        towBarRepository.save(towBar);
//        towBarRepository.save(towBar1);
//        CartItem cart1 = new CartItem(user,towBar,5);
//        CartItem cart2 = new CartItem(user,towBar1,60);
//
//        cartItemRepository.save(cart1);
//        cartItemRepository.save(cart2);
//

//        Optional<List<CartItem>> allByUser = cartItemRepository.findAllByUser(user1);
//
//        List<CartItem> cartItems = allByUser.get();
//        for (CartItem cartItem : cartItems) {
//            TowBar tow = cartItem.getTowBar();
//        }

//
//        UserEntity user = userRepository.findByUsername("mitko").get();
//        UserEntity user1 = userRepository.findByUsername("mitko").get();
//        TowBar first = towBarRepository.findByName("first");
//        TowBar second = towBarRepository.findByName("second");
//
//        CartItem cartItem1 = new CartItem(user,first,10);
//        CartItem cartItem2 = new CartItem(user,second,20);
//        cartItemRepository.save(cartItem1);
//        cartItemRepository.save(cartItem2);

//        Optional<UserEntity> mitko = userRepository.findByUsername("mitko");
//        UserEntity user = mitko.get();
//        user.setActive(true);
//        userRepository.save(user);

//        List<UserEntity> all = userRepository.findAll();
//        RoleEntity adm = roleRepository.findByRole(RoleNameEnum.ADMIN);
//        RoleEntity usr = roleRepository.findByRole(RoleNameEnum.USER);
//        for (int i = 0; i < all.size(); i++) {
//            UserEntity user = all.get(i);
//            user.addRole(usr);
//            if (i % 2 ==0 ){
//                user.addRole(adm);
//                userRepository.save(user);
//            }
//
//        }




        Optional<UserEntity> kashon = userRepository.findByUsername("bejunka");

        if (kashon.isPresent()) {

//            adding admin role to smb
//            UserEntity userEntity = kashon.get();
//            RoleEntity adm = roleRepository.findByRole(RoleNameEnum.ADMIN);
//            userEntity.addRole(adm);
//            userRepository.save(userEntity);



//            TowBar towBar = new TowBar();
//            towBar.setName("first");
//            towBar.setPrice(BigDecimal.TEN);
//            towBar.setType(TowBarType.FIXED);
//            TowBar towBar1 = new TowBar();
//            towBar1.setPrice(BigDecimal.ONE);
//            towBar1.setName("second");
//            towBar1.setType(TowBarType.DETACHABLE);
//            towBarRepository.save(towBar);
//            towBarRepository.save(towBar1);

            TowBar first = towBarRepository.findByName("first");
            TowBar second = towBarRepository.findByName("second");

            CartItem cartItem1 = new CartItem(kashon.get(), first, 10);
            CartItem cartItem2 = new CartItem(kashon.get(), second, 20);
            cartItemRepository.save(cartItem1);
            cartItemRepository.save(cartItem2);

        }
    }
}
