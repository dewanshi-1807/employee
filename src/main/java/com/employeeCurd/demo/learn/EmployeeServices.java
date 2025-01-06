package com.employeeCurd.demo.learn;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServices {

    private final EmployeeRepository employeeRepository;
private final EmployeeDetailsRepository employeeDetailsRepository;
    // Constructor injection for repositories
    public EmployeeServices(EmployeeRepository employeeRepository, EmployeeDetailsRepository employeeDetailsRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeDetailsRepository = employeeDetailsRepository;
    }

    // Retrieve all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Create a new employee from DTO
    public Employee createEmployeeFromDTO(EmployeeDTO employeeDTO) {
        validateEmployeeDTO(employeeDTO);

        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());

        Employee savedEmployee = employeeRepository.save(employee);

        // Save initial details in employee_details table
        EmployeeDetails employeeDetails = new EmployeeDetails();
//        employeeDetails.setEmployeeId(savedEmployee.getId());
        employeeDetails.setId(savedEmployee.getId());
        employeeDetails.setOldEmail(null); // No old email on creation
        employeeDetails.setNewEmail(employeeDTO.getEmail());
        employeeDetails.setEmailChangeTime(LocalDateTime.now());

        employeeDetails.setOldPhoneNumber(null); // No old phone number on creation
        employeeDetails.setNewPhoneNumber(employeeDTO.getPhoneNumber());
        employeeDetails.setPhoneNumberChangeTime(LocalDateTime.now());

        employeeDetails.setCreateDate(LocalDateTime.now());

        employeeDetailsRepository.save(employeeDetails);

        return savedEmployee;
    }

    private void validateEmployeeDTO(EmployeeDTO employeeDTO) {
        if (employeeDTO == null) {
            throw new InvalidEmployeeDataException("EmployeeDTO cannot be null");
        }
        if (employeeDTO.getName() == null || employeeDTO.getName().isBlank()) {
            throw new InvalidEmployeeDataException("Employee name is required");
        }
        if (employeeDTO.getEmail() == null || employeeDTO.getEmail().isBlank()) {
            throw new InvalidEmployeeDataException("Employee email is required");
        }
        if (employeeDTO.getPhoneNumber() == null || employeeDTO.getPhoneNumber().isBlank()) {
            throw new InvalidEmployeeDataException("Employee phone number is required");
        }
    }

    // Retrieve an employee by ID
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // Update employee details
    @Transactional
    public void updateEmployeeFromDTO(Long id, EmployeeDTO employeeDTO) {
        // Find the employee by ID, or throw exception if not found
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + id));

        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setEmployee(employee); // Link to Employee
        employeeDetails.setCreateDate(LocalDateTime.now());

        // Track changes for email
        if (employeeDTO.getEmail() != null && !employeeDTO.getEmail().equals(employee.getEmail())) {
            employeeDetails.setOldEmail(employee.getEmail());
            employeeDetails.setNewEmail(employeeDTO.getEmail());
            employeeDetails.setEmailChangeTime(LocalDateTime.now());
            employee.setEmail(employeeDTO.getEmail());
        }

        // Track changes for phone number
        if (employeeDTO.getPhoneNumber() != null && !employeeDTO.getPhoneNumber().equals(employee.getPhoneNumber())) {
            employeeDetails.setOldPhoneNumber(employee.getPhoneNumber());
            employeeDetails.setNewPhoneNumber(employeeDTO.getPhoneNumber());
            employeeDetails.setPhoneNumberChangeTime(LocalDateTime.now());
            employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        }

        // Save the updated employee
        employeeRepository.save(employee);

        // Save employee details if changes exist
        if (employeeDetails.getOldEmail() != null || employeeDetails.getOldPhoneNumber() != null) {
            employeeDetailsRepository.save(employeeDetails);
        }
    }

}






