package bg.softuni.towebarshopweb.model.dto;

import bg.softuni.towebarshopweb.model.entity.FileEntity;
import bg.softuni.towebarshopweb.model.entity.RoleEntity;

import java.util.List;
import java.util.Set;

public class UserMyProfileDto {

    private FileEntity image;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Set<RoleEntity> roles;

    public UserMyProfileDto() {
    }

    public FileEntity getImage() {
        return image;
    }

    public UserMyProfileDto setImage(FileEntity image) {
        this.image = image;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserMyProfileDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserMyProfileDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserMyProfileDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserMyProfileDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public UserMyProfileDto setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }
}
