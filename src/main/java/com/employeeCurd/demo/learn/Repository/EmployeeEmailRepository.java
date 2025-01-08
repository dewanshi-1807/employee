package com.employeeCurd.demo.learn.Repository;

import com.employeeCurd.demo.learn.Entity.EmployeeEmailChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeEmailRepository extends JpaRepository<EmployeeEmailChange, Long> {
}
