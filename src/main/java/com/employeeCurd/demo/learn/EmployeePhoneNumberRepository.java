package com.employeeCurd.demo.learn;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePhoneNumberRepository<EmployeePhoneNumberChange> extends JpaRepository<EmployeePhoneNumberChange, Long> {
//     EmployeePhoneNumberChange getByPhoneNumber (long phonenumber);
}
