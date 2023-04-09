package bg.softuni.towebarshopweb.service;

import bg.softuni.towebarshopweb.model.entity.RoleEntity;
import bg.softuni.towebarshopweb.model.entity.UserEntity;
import bg.softuni.towebarshopweb.model.enums.RoleNameEnum;
import bg.softuni.towebarshopweb.repository.RoleRepository;
import bg.softuni.towebarshopweb.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.*;
import java.io.*;
import java.time.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;
    private FileService fileService;

    @Mock
    private PasswordEncoder passwordEncoder;
    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        roleRepository = mock(RoleRepository.class);
        modelMapper = mock(ModelMapper.class);
        passwordEncoder = mock(PasswordEncoder.class);
        fileService = mock(FileService.class);
        userService = new UserService(userRepository, roleRepository, modelMapper, passwordEncoder, fileService);
    }





    @Test
    void testRegisterUser() {
        // Test case here
    }

    @Test
    void testApproveCurrentPassword() {
        // Test case here
    }

    @Test
    void testEditUser() {
        // Test case here
    }

    @Test
    void testFindByUsername() {
        // Test case here
    }

    @Test
    void testDisableUserById() {
        // Test case here
    }

    @Test
    void testEnableUserById() {
        // Test case here
    }

    @Test
    void testFindAllActive() {
        // Test case here
    }

    @Test
    void testFindAllNonActive() {
        // Test case here
    }

    @Test
    void testChangePassword() {
        // Test case here
    }

    @Test
    void testGetUserByUsername() {
        // Test case here
    }

    @Test
    void testAddRoleToUserById() {
        // Test case here
    }
}
