package com.employeeCurd.demo.learn.Repository;

import com.employeeCurd.demo.learn.Entity.EmployeeAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddress, Long> {
//}
@Repository
//import org.springframework.data.jpa.repository.JpaRepository;
public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddress, Long> {}
