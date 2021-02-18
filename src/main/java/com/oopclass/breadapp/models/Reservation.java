package com.oopclass.breadapp.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OOP Class 20-21
 *
 * @author Gerald Villaran
 */
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDate createdAt;
    
    @Column(name = "updated_at")
    
    private LocalDateTime updatedAt;
    private String reservationAmount;
    private String fullName;
    private LocalDate date;
    private String status;
     
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReservationAmount() {
        return reservationAmount;
    }

    public void setReservationAmount(String reservationAmount) {
        this.reservationAmount = reservationAmount;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt() {
        LocalDate localDate = LocalDate.now();
        this.createdAt = localDate;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt() {
        LocalDateTime localDateTime = LocalDateTime.now();
        this.updatedAt = localDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "Reservation [id=" + id + ", reservationAmount=" + reservationAmount + ", fullName=" + fullName + ", date=" + date + ",status=" + status +"]";
    }

}
