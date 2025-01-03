package com.employeeCurd.demo.learn;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeEmailRepository extends JpaRepository<EmployeeEmailChange, Long> {
}
