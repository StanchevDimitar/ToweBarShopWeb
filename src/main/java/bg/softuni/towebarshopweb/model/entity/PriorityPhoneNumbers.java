package bg.softuni.towebarshopweb.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "phone-numbers")
public class PriorityPhoneNumbers extends BaseEntity{

    @Column
    private String number;

    @Column
    private LocalDateTime insertedTime;
    @Column
    private LocalDateTime completedTime;

    @Column
    private Boolean isProcessed;

    public PriorityPhoneNumbers() {
        this.isProcessed = false;
    }

    public Boolean getProcessed() {
        return isProcessed;
    }

    public PriorityPhoneNumbers setProcessed(Boolean processed) {
        isProcessed = processed;
        return this;
    }

    public LocalDateTime getCompletedTime() {
        return completedTime;
    }

    public PriorityPhoneNumbers setCompletedTime(LocalDateTime completedTime) {
        this.completedTime = completedTime;
        return this;
    }

    public LocalDateTime getInsertedTime() {
        return insertedTime;
    }

    public PriorityPhoneNumbers setInsertedTime(LocalDateTime insertedTime) {
        this.insertedTime = insertedTime;
        return this;
    }

    public String formatTime(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return localDateTime.format(formatter);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
