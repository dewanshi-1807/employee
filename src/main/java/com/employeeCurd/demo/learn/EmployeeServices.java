package com.employeeCurd.demo.learn;

import com.employeeCurd.demo.learn.Entity.Employee;
import com.employeeCurd.demo.learn.Entity.EmployeeAddress;
import com.employeeCurd.demo.learn.Entity.EmployeeSkill;
import com.employeeCurd.demo.learn.Entity.EmployeeDetails;
import com.employeeCurd.demo.learn.Repository.EmployeeDetailsRepository;
import com.employeeCurd.demo.learn.Repository.EmployeeRepository;
import com.employeeCurd.demo.learn.Repository.EmployeeSkillRepository;
import com.employeeCurd.demo.learn.Repository.EmployeeAddressRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServices {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServices.class);

    private final EmployeeRepository employeeRepository;
    private final EmployeeDetailsRepository employeeDetailsRepository;
    private final EmployeeSkillRepository employeeSkillRepository;
    private final EmployeeAddressRepository employeeAddressRepository;

    public EmployeeServices(EmployeeRepository employeeRepository, EmployeeDetailsRepository employeeDetailsRepository, EmployeeSkillRepository employeeSkillRepository, EmployeeAddressRepository employeeAddressRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeDetailsRepository = employeeDetailsRepository;
        this.employeeSkillRepository = employeeSkillRepository;
        this.employeeAddressRepository = employeeAddressRepository;
    }

    // Retrieve all employees
    public List<Employee> getAllEmployees() {
        logger.info("Fetching all employees");
        return employeeRepository.findAll();
    }

    // Create a new employee with address and skills
    @Transactional
    public Employee createEmployeeFromDTO(EmployeeDTO employeeDTO) {
       // validateEmployeeDTO(employeeDTO);

        logger.debug("Creating employee from DTO: {}", employeeDTO);

        // Create Employee entity
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());

        // Create EmployeeAddress entity
        EmployeeAddress address = new EmployeeAddress();
        address.setStreet(employeeDTO.getAddress().getStreet());
        address.setCity(employeeDTO.getAddress().getCity());
        address.setEmployee(employee);
        employee.setEmployeeAddress(address);

        // Create and associate skills
        List<EmployeeSkill> skills = employeeDTO.getSkills().stream()
                .map(skillName -> employeeSkillRepository.findBySkillName(skillName)
                        .orElseGet(() -> {
                            EmployeeSkill newSkill = new EmployeeSkill();
                            newSkill.setSkillName(skillName);
                            return employeeSkillRepository.save(newSkill);
                        }))
                .collect(Collectors.toList());
        employee.getSkills().addAll(skills);

        logger.debug("Saving employee with skills: {}", skills);
        Employee savedEmployee = employeeRepository.save(employee);

        // Save employee details
        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setEmployee(savedEmployee);
        employeeDetails.setOldEmail(null);
        employeeDetails.setNewEmail(employeeDTO.getEmail());
        employeeDetails.setEmailChangeTime(LocalDateTime.now());
        employeeDetails.setOldPhoneNumber(null);
        employeeDetails.setNewPhoneNumber(employeeDTO.getPhoneNumber());
        employeeDetails.setPhoneNumberChangeTime(LocalDateTime.now());
        employeeDetails.setCreateDate(LocalDateTime.now());

        employeeDetailsRepository.save(employeeDetails);

        logger.info("Employee created successfully with ID: {}", savedEmployee.getId());
        return savedEmployee;
    }

    // Retrieve an employee by ID
    public Employee getEmployeeById(Long id) {
        logger.info("Fetching employee with ID: {}", id);
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));
    }

    // Update employee details and related entities
    @Transactional
    public Employee updateEmployeeFromDTO(Long id, EmployeeDTO employeeDTO) {
        logger.info("Updating employee with ID: {}", id);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));

        // Update basic fields
        if (employeeDTO.getName() != null) employee.setName(employeeDTO.getName());
        if (employeeDTO.getEmail() != null) employee.setEmail(employeeDTO.getEmail());
        if (employeeDTO.getPhoneNumber() != null) employee.setPhoneNumber(employeeDTO.getPhoneNumber());

        // Update address
        if (employeeDTO.getAddress() != null) {
            EmployeeAddress address = employee.getEmployeeAddress();
            if (address == null) {
                address = new EmployeeAddress();
                address.setEmployee(employee);
                employee.setEmployeeAddress(address);
            }
            address.setStreet(employeeDTO.getAddress().getStreet());
            address.setCity(employeeDTO.getAddress().getCity());
        }

       //Update skills;
        if (employeeDTO.getSkills() != null) {
            List<EmployeeSkill> updatedSkills = employeeDTO.getSkills().stream()
                    .map(skillName -> employeeSkillRepository.findBySkillName(skillName)
                            .orElseGet(() -> {
                                EmployeeSkill newSkill = new EmployeeSkill();
                                newSkill.setSkillName(skillName);
                                return employeeSkillRepository.save(newSkill);
                            }))
                    .collect(Collectors.toList());
            employee.setSkills(updatedSkills);
        }

        Employee updatedEmployee = employeeRepository.save(employee);
        logger.info("Employee updated successfully with ID: {}", id);
        return updatedEmployee;
    }

    // Fetch employee details by phone number
    public List<EmployeeDetails> getEmployeeDetailsByPhoneNumber(String phoneNumber) {
        return employeeDetailsRepository.findByPhoneNumber(phoneNumber);
    }

    // Validate EmployeeDTO input
    private void validateEmployeeDTO(EmployeeDTO employeeDTO) {
        if (employeeDTO == null) {
            throw new InvalidEmployeeDataException("EmployeeDTO cannot be null");
        }
        if (employeeDTO.getName() == null || employeeDTO.getName().isBlank()) {
            throw new InvalidEmployeeDataException("Employee name is required");
        }
        if (employeeDTO.getEmail() == null || !employeeDTO.getEmail().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new InvalidEmployeeDataException("Invalid email format");
        }
        if (employeeDTO.getPhoneNumber() == null || !employeeDTO.getPhoneNumber().matches("^\\+?[0-9]{10,15}$")) {
            throw new InvalidEmployeeDataException("Invalid phone number format");
        }
    }

}












