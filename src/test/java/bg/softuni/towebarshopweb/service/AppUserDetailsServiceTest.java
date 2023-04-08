package bg.softuni.towebarshopweb.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import bg.softuni.towebarshopweb.model.entity.RoleEntity;
import bg.softuni.towebarshopweb.model.entity.UserEntity;
import bg.softuni.towebarshopweb.model.enums.RoleNameEnum;
import bg.softuni.towebarshopweb.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opentest4j.AssertionFailedError;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppUserDetailsServiceTest {

    private final String NOT_EXISTING_EMAIL = "pesho@example.com";

    private AppUserDetailsService toTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        toTest = new AppUserDetailsService(
                mockUserRepository
        );
    }

    @Test
    void testUserFound() {

        // ARRANGE
        RoleEntity testAdminRole = new RoleEntity().setRole(RoleNameEnum.ADMIN);
        RoleEntity testUserRole = new RoleEntity().setRole(RoleNameEnum.USER);

        String EXISTING_EMAIL = "admin@example.com";
        UserEntity testUserEntity = new UserEntity();
        testUserEntity.setUsername(EXISTING_EMAIL);
        testUserEntity.setPassword("topsecret");
        testUserEntity.setRoles(Set.of(testAdminRole, testUserRole));


        when(mockUserRepository.findByUsername(EXISTING_EMAIL)).
                thenReturn(Optional.of(testUserEntity));
        // EO: ARRANGE


        // ACT
        UserDetails adminDetails =
                toTest.loadUserByUsername(EXISTING_EMAIL);
        // EO: ACT

        // ASSERT
        Assertions.assertNotNull(adminDetails);
        Assertions.assertEquals(EXISTING_EMAIL, adminDetails.getUsername());
        Assertions.assertEquals(testUserEntity.getPassword(), adminDetails.getPassword());

        Assertions.assertEquals(2,
                adminDetails.getAuthorities().size(),
                "The authorities are supposed to be just two - ADMIN/USER.");

        assertRole(adminDetails.getAuthorities(), "ROLE_ADMIN");
        assertRole(adminDetails.getAuthorities(), "ROLE_USER");
        // EO: ASSERT
    }

    private void assertRole(Collection<? extends GrantedAuthority> authorities,
                            String role) {
        authorities.
                stream().
                filter(a -> role.equals(a.getAuthority())).
                findAny().
                orElseThrow(() -> new AssertionFailedError("Role " + role + " not found!"));
    }


    @Test
    void testUserNotFound() {
        assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername(NOT_EXISTING_EMAIL)
        );
    }
}
