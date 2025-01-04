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
    private final EmployeeEmailRepository employeeEmailRepository;
    private final EmployeePhoneNumberRepository employeePhoneNumberRepository;

    // Constructor injection for repositories
    public EmployeeServices(EmployeeRepository employeeRepository,
                            EmployeeEmailRepository employeeEmailRepository,
                            EmployeePhoneNumberRepository employeePhoneNumberRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeEmailRepository = employeeEmailRepository;
        this.employeePhoneNumberRepository = employeePhoneNumberRepository;
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

        return employeeRepository.save(employee);
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
    public void updateEmployee(Long id, EmployeeDTO employeeDTO) {
        // Find the employee by ID, or throw exception if not found
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + id));

        // Track changes for email
        if (employeeDTO.getEmail() != null && !employeeDTO.getEmail().equals(employee.getEmail())) {
            // Record email change history
            EmployeeEmailChange emailChange = new EmployeeEmailChange();
            emailChange.setEmployeeId(employee.getId());
            emailChange.setOldEmail(employee.getEmail());
            emailChange.setNewEmail(employeeDTO.getEmail());
            emailChange.setChangeTime(LocalDateTime.now());
            employeeEmailRepository.save(emailChange);

            // Update email in the employee table
            employee.setEmail(employeeDTO.getEmail());
        }
        // Track changes for phone number
        if (employeeDTO.getPhoneNumber() != null && !employeeDTO.getPhoneNumber().equals(employee.getPhoneNumber())) {
            // Record phone number change history
            EmployeePhoneNumberChange phoneNumberChange = new EmployeePhoneNumberChange();
            phoneNumberChange.setEmployeeId(employee.getId());
            phoneNumberChange.setOldPhoneNumber(employee.getPhoneNumber());
            phoneNumberChange.setNewPhoneNumber(employeeDTO.getPhoneNumber());
            phoneNumberChange.setChangeTime(LocalDateTime.now());
            employeePhoneNumberRepository.save(phoneNumberChange);

            // Update phone number in the employee table
            employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        }

        // Save the updated employee
        employeeRepository.save(employee);
    }

    // Update employee phone number only
    @Transactional
    public void updatePhoneNumber(EmployeePhoneNumber phoneNumber) {
        if (phoneNumber.getPhoneNumber() == null) {
            throw new IllegalArgumentException("Phone number cannot be null");
        }
        employeePhoneNumberRepository.save(phoneNumber);
    }

    // Delete an employee by ID
    public void deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            throw new EmployeeNotFoundException("Employee not found with ID: " + id);
        }
    }
}






