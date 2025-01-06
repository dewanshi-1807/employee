package com.employeeCurd.demo.learn;

import com.employeeCurd.demo.learn.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails, Long> {
    // Custom query methods can be added here if needed
}
