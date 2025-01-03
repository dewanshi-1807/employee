package com.employeeCurd.demo.learn;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee_details")
public class EmployeeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "old_email")
    private String oldEmail;

    @Column(name = "new_email")
    private String newEmail;

    @Column(name = "email_change_time")
    private LocalDateTime emailChangeTime;

    @Column(name = "old_phone_number")
    private String oldPhoneNumber;

    @Column(name = "new_phone_number")
    private String newPhoneNumber;

    @Column(name = "phone_number_change_time")
    private LocalDateTime phoneNumberChangeTime;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOldEmail() {
        return oldEmail;
    }

    public void setOldEmail(String oldEmail) {
        this.oldEmail = oldEmail;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public LocalDateTime getEmailChangeTime() {
        return emailChangeTime;
    }

    public void setEmailChangeTime(LocalDateTime emailChangeTime) {
        this.emailChangeTime = emailChangeTime;
    }

    public String getOldPhoneNumber() {
        return oldPhoneNumber;
    }

    public void setOldPhoneNumber(String oldPhoneNumber) {
        this.oldPhoneNumber = oldPhoneNumber;
    }

    public String getNewPhoneNumber() {
        return newPhoneNumber;
    }

    public void setNewPhoneNumber(String newPhoneNumber) {
        this.newPhoneNumber = newPhoneNumber;
    }

    public LocalDateTime getPhoneNumberChangeTime() {
        return phoneNumberChangeTime;
    }

    public void setPhoneNumberChangeTime(LocalDateTime phoneNumberChangeTime) {
        this.phoneNumberChangeTime = phoneNumberChangeTime;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
