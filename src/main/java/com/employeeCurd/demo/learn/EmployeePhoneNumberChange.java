package com.employeeCurd.demo.learn;

import jakarta.persistence.*;
        import java.time.LocalDateTime;

@Entity
@Table(name = "employee_phone_number_changes")
public class EmployeePhoneNumberChange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "old_phone_number", nullable = false)
    private String oldPhoneNumber;

    @Column(name = "new_phone_number", nullable = false)
    private String newPhoneNumber;

    @Column(name = "change_time", nullable = false)
    private LocalDateTime changeTime;

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

    // toString method for debugging

    @Override
    public String toString() {
        return "EmployeePhoneNumberChange{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", oldPhoneNumber='" + oldPhoneNumber + '\'' +
                ", newPhoneNumber='" + newPhoneNumber + '\'' +
                ", changeTime=" + changeTime +
                '}';
    }
}
