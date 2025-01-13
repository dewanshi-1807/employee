package com.employeeCurd.demo.learn.Repository;

import com.employeeCurd.demo.learn.Entity.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
//public interface EmploeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {
//}

//import org.springframework.data.jpa.repository.JpaRepository;
public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {
    Optional<EmployeeSkill> findBySkillName(String skillName);
}

