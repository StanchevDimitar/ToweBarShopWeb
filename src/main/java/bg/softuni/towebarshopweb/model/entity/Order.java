package bg.softuni.towebarshopweb.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @ManyToOne
    private UserEntity user;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<TowBar> items;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String city;
    @Column
    private Integer zip;
    @Column
    private String address;
    @Column
    private String telephoneNumber;
    @Column
    private Boolean isFinished;
    @Column
    private LocalDateTime completedTime;
    @Column
    private BigDecimal orderPrice;

    @Column LocalDateTime sentTime;

    @Column
    private Boolean isSent;

    public Order() {
        this.isFinished = false;
        this.isSent =false;
    }

    public UserEntity getUser() {
        return user;
    }

    public Order setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public LocalDateTime getSentTime() {
        return sentTime;
    }

    public Order setSentTime(LocalDateTime sentTime) {
        this.sentTime = sentTime;
        return this;
    }

    public Boolean getSent() {
        return isSent;
    }

    public Order setSent(Boolean sent) {
        isSent = sent;
        return this;
    }

    public Boolean getIsSent() {
        return isSent;
    }

    public Order setIsSent(Boolean sent) {
        this.isSent = sent;
        return this;
    }

    public List<TowBar> getItems() {
        return items;
    }

    public Order setItems(List<TowBar> items) {
        this.items = items;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Order setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Order setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Order setCity(String city) {
        this.city = city;
        return this;
    }

    public Integer getZip() {
        return zip;
    }

    public Order setZip(Integer zip) {
        this.zip = zip;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Order setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public Order setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
        return this;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public Order setFinished(Boolean finished) {
        isFinished = finished;
        return this;
    }

    public LocalDateTime getCompletedTime() {
        return completedTime;
    }

    public Order setCompletedTime(LocalDateTime completedTime) {
        this.completedTime = completedTime;
        return this;
    }

    public String formatTime(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return localDateTime.format(formatter);
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public Order setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
        return this;
    }
}
