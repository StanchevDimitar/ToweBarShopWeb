package bg.softuni.towebarshopweb.service;

import bg.softuni.towebarshopweb.model.dto.UserBasicInfoView;
import bg.softuni.towebarshopweb.model.dto.UserEditDto;
import bg.softuni.towebarshopweb.model.dto.UserRegisterDto;
import bg.softuni.towebarshopweb.model.entity.FileEntity;
import bg.softuni.towebarshopweb.model.entity.RoleEntity;
import bg.softuni.towebarshopweb.model.entity.UserEntity;
import bg.softuni.towebarshopweb.model.enums.RoleNameEnum;
import bg.softuni.towebarshopweb.repository.RoleRepository;
import bg.softuni.towebarshopweb.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;
    private final FileService fileService;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, FileService fileService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.fileService = fileService;
    }

    public boolean checkUsername(String username) {

        return userRepository.findByUsername(username).isPresent();
    }

    public UserEntity getCurrentlyLoggedInUser(){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        return userRepository.findByUsername(username).orElse(null);


    }

    public void registerUser(UserRegisterDto userRegisterDto) {

        RoleEntity role = roleRepository.findByRole(RoleNameEnum.USER);

        UserEntity map = modelMapper.map(userRegisterDto, UserEntity.class);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(userRegisterDto.getPassword());
        map.setPassword(encode);
        map.setCreated(LocalDateTime.now());
        map.setRoles(Set.of(role));
        map.setActive(true);

        userRepository.save(map);
    }

    public boolean approveCurrentPassword(String currentPass) {
        return passwordEncoder.matches(currentPass, getCurrentlyLoggedInUser().getPassword());
    }

    public String editUser(UserEditDto user, MultipartFile img) throws IOException {


        UserEntity currLoggedUser = getCurrentlyLoggedInUser();
        if (userRepository.findByUsername(user.getUsername()).isPresent()){
            return "Username is taken";
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()){
            return "Email is already used";
        }

        modelMapper.map(user,currLoggedUser);
        currLoggedUser.setModified(LocalDateTime.now());
        if (!img.isEmpty()){
            FileEntity fileEntity = fileService.saveFile(img);
            currLoggedUser.setImage(fileEntity);
        }
        userRepository.save(currLoggedUser);


        return "Edited correctly";
    }



    public UserBasicInfoView findByUsername(String username) {
        Optional<UserEntity> optUser = userRepository.findByUsername(username);

        return optUser.map(user -> modelMapper.map(user, UserBasicInfoView.class)).orElse(null);

    }

    public void disableUserById(Long id) {
        UserEntity user = userRepository.findById(id).get();
        user.setActive(false);
        userRepository.save(user);
    }

    public void enableUserById(Long id) {
        UserEntity user = userRepository.findById(id).get();
        user.setActive(true);
        userRepository.save(user);
    }

    public List<UserEntity> findAllActive() {
//        Optional<List<UserEntity>> allActive = userRepository.findAllActive();

        return userRepository.findAllByIsActiveTrue();
//        return allActive.orElse(null);

    }

    public List<UserEntity> findAllNonActive() {
        return userRepository.findAllByIsActiveFalse();
    }

    public void changePassword(String newPass) {
        String encode = passwordEncoder.encode(newPass);
        UserEntity currentlyLoggedInUser = getCurrentlyLoggedInUser();
        currentlyLoggedInUser.setPassword(encode);
        userRepository.save(currentlyLoggedInUser);
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    public void removeRoleById(Long id, RoleNameEnum role) {
        Optional<UserEntity> byId = userRepository.findById(id);
        if (byId.isPresent()){
            UserEntity userEntity = byId.get();
            RoleEntity byRole = roleRepository.findByRole(role);
            Set<RoleEntity> roles = userEntity.getRoles();
            for (RoleEntity roleEntity : roles) {
                RoleNameEnum role1 = roleEntity.getRole();
                RoleNameEnum role2 = byRole.getRole();
                if (role1.equals(role2)){
                    roles.remove(roleEntity);
                    userEntity.setRoles(roles);
                    break;
                }
            }
            userRepository.save(userEntity);
        }
    }

    public void addRoleToUserById(Long id, RoleNameEnum role) {
        Optional<UserEntity> byId = userRepository.findById(id);
        if (byId.isPresent()){
            UserEntity userEntity = byId.get();
            RoleEntity byRole = roleRepository.findByRole(role);
            userEntity.getRoles().add(byRole);
            userRepository.save(userEntity);
        }
    }
}
