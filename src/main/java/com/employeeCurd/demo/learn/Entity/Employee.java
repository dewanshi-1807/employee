package com.employeeCurd.demo.learn.Entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String email;
    private String phoneNumber;

    // Many-to-Many relationship with EmployeeSkill
    @ManyToMany
    @JoinTable(
            name = "employee_skill",  // Join table name
            joinColumns = @JoinColumn(name = "employee_id"),  // Column referring to Employee
            inverseJoinColumns = @JoinColumn(name = "skill_id")  // Column referring to EmployeeSkill
    )
    private List<EmployeeSkill> skills;

    // One-to-One relationship with EmployeeAddress
    //@OneToOne(cascade = CascadeType.ALL)
   // @JoinColumn(name = "address_id")  // Foreign key in Employee table
    //private RabbitConnectionDetails.EmployeeAddress employeeaddress;  // Correct relationship field
   // private EmployeeAddress employeeAddress;

    //@OneToMany(mappedBy = "employee") // Example
    //private Set<EmployeeSkill> employeeSkill;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(List<EmployeeSkill> skills) {
        this.skills = skills;
    }

    //public EmployeeAddress getEmployeeAddress() {
      //  return employeeAddress;
    }

    //public void setEmployeeAddress(EmployeeAddress employeeAddress) {
      //  this.employeeAddress = employeeAddress;
    //}
//}


