package com.employeeCurd.demo.learn.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_address")
public class EmployeeAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;


    // One-to-One relationship with Employee
   // @OneToOne // Ensure the correct property name is referenced here
    //private Employee employee;  // Correct relationship field

    //@OneToOne(mappedBy = "employeeAddress", cascade = CascadeType.ALL)
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}