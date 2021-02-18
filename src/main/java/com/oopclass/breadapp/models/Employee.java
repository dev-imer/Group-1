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
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDate createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    	
    private String firstName;
	
    private String lastName;
	
    private LocalDate dob;
	
    private String gender;
    
    private String contact;
    
    private String position;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
    
    
    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName +  ", lastName=" + lastName + ", dob=" + dob + ", contact=" + contact + ", position=" + position + "]";
    }

}
