package bg.softuni.towebarshopweb.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem extends BaseEntity {

    @ManyToOne
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER)
    private TowBar towBar;

    public CartItem() {
    }

    public CartItem(UserEntity user, TowBar towBar) {
        this.user = user;
        this.towBar = towBar;

    }

    public CartItem(TowBar towBar) {
        this.towBar = towBar;
    }


    public TowBar getTowBar() {
        return towBar;
    }

    public void setTowBar(TowBar towBars) {
        this.towBar = towBars;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }




}
