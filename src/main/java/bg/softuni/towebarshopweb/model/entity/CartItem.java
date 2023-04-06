package bg.softuni.towebarshopweb.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem extends BaseEntity {

    @ManyToOne
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER)
    private TowBar towBar;

    @Column(nullable = false)
    private Integer quantity;

    public CartItem() {
    }

    public CartItem(UserEntity user, TowBar towBar, Integer quantity) {
        this.user = user;
        this.towBar = towBar;
        this.quantity = quantity;
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



    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
