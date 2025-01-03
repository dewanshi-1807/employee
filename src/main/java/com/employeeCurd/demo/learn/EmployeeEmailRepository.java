package com.employeeCurd.demo.learn;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeEmailRepository extends JpaRepository<EmployeeEmailChange, Long> {
}
