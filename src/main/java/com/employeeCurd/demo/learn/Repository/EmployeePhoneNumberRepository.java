package com.employeeCurd.demo.learn.Repository;

import com.employeeCurd.demo.learn.Entity.EmployeePhoneNumberChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePhoneNumberRepository extends JpaRepository<EmployeePhoneNumberChange, Long> {
//     EmployeePhoneNumberChange getByPhoneNumber (long phonenumber);
}
