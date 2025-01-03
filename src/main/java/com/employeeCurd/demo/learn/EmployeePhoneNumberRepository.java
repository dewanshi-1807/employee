package com.employeeCurd.demo.learn;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePhoneNumberRepository extends JpaRepository<EmployeePhoneNumberChange, Long> {
    @Query ("")
    EmployeePhoneNumberChange getByPhoneNumber (long phonenumber);
}
