package com.employeeCurd.demo.learn.Repository;

import com.employeeCurd.demo.learn.Entity.Employee;
import com.employeeCurd.demo.learn.Entity.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
//public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails, Long> {
  //  @Query("SELECT e FROM EmployeeDetails e WHERE e.newEmail = :email")
    //List<EmployeeDetails> findByNewEmail(String email);
//}
@Repository
    // New method to find by new phone number
   // @Query("SELECT e FROM EmployeeDetails e WHERE e.newPhoneNumber = :phoneNumber")

public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails, Long> {

    // Add the method to find employee details by employee
    List<EmployeeDetails> findByPhoneNumber(String phoneNumber);
}
