package com.employeeCurd.demo.learn.Repository;
import com.employeeCurd.demo.learn.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
//public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//    @Query("SELECT e FROM Employee e JOIN FETCH e.skills s JOIN FETCH e.address WHERE e.id = :id")
//    Employee findEmployeeWithSkillsAndAddress(@Param("id") Long id);

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Add the method to find employees by phone number
    List<Employee> findByPhoneNumber(String phoneNumber);
}
