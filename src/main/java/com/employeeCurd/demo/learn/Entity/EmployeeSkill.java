package com.employeeCurd.demo.learn.Entity;

import jakarta.persistence.*;
        import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employee_skill")
public class EmployeeSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skillName;

    // Many-to-Many relationship with Employee
    @ManyToMany
    @JoinTable(
            name = "employee_skill",  // Join table name
            joinColumns = @JoinColumn(name = "skill_id"),  // Column referring to EmployeeSkill
            inverseJoinColumns = @JoinColumn(name = "employee_id")  // Column referring to Employee
    )
    private List<Employee> employees;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
