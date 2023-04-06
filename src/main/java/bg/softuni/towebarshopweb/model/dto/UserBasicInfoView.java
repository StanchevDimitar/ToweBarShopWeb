package bg.softuni.towebarshopweb.model.dto;

import bg.softuni.towebarshopweb.model.entity.RoleEntity;
import bg.softuni.towebarshopweb.model.enums.RoleNameEnum;

import java.util.List;
import java.util.Set;

public class UserBasicInfoView {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;

    private Set<RoleEntity> roles;

    private Boolean isActive;

    public UserBasicInfoView() {
    }

    public Boolean containsAdmin(){
        for (RoleEntity role : roles) {
            if (role.getRole().equals(RoleNameEnum.ADMIN)) {
                return true;
            }
        }

        return false;
    }
    public Boolean containsModerator(){
        for (RoleEntity role : roles) {
            if (role.getRole().equals(RoleNameEnum.MODERATOR)) {
                return true;
            }
        }

        return false;
    }

    public Long getId() {
        return id;
    }

    public UserBasicInfoView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserBasicInfoView setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserBasicInfoView setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserBasicInfoView setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public UserBasicInfoView setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public Boolean getActive() {
        return isActive;
    }

    public UserBasicInfoView setActive(Boolean active) {
        isActive = active;
        return this;
    }

    public void addRole(RoleNameEnum role){
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRole(role);
        roles.add(roleEntity);
    }
    public void removeRole(RoleNameEnum role){
        roles.removeIf(roleEntity -> roleEntity.getRole().equals(role));
    }
}
