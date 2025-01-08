package com.employeeCurd.demo.learn.Entity;

import jakarta.persistence.*;
        import java.time.LocalDateTime;

@Entity
@Table(name = "employee_phonenumber")
public class EmployeePhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "old_phone_number")
    private String oldPhoneNumber;

    @Column(name = "new_phone_number")
    private String newPhoneNumber;

    @Column(name = "change_time", nullable = false)
    private LocalDateTime changeTime;

    // Default Constructor
    public EmployeePhoneNumber() {
    }

    // Parameterized Constructor
    public EmployeePhoneNumber(Long employeeId, String phoneNumber, String oldPhoneNumber, String newPhoneNumber, LocalDateTime changeTime) {
        this.employeeId = employeeId;
        this.phoneNumber = phoneNumber;
        this.oldPhoneNumber = oldPhoneNumber;
        this.newPhoneNumber = newPhoneNumber;
        this.changeTime = changeTime;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public LocalDateTime getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(LocalDateTime changeTime) {
        this.changeTime = changeTime;
    }

    @Override
    public String toString() {
        return "EmployeePhoneNumber{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", oldPhoneNumber='" + oldPhoneNumber + '\'' +
                ", newPhoneNumber='" + newPhoneNumber + '\'' +
                ", changeTime=" + changeTime +
                '}';
    }
}
