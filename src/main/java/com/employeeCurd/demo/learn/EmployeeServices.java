package com.employeeCurd.demo.learn;

import com.employeeCurd.demo.learn.Entity.Employee;
import com.employeeCurd.demo.learn.Entity.EmployeeDetails;
import com.employeeCurd.demo.learn.Repository.EmployeeDetailsRepository;
import com.employeeCurd.demo.learn.Repository.EmployeeRepository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeServices {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServices.class);

    private final EmployeeRepository employeeRepository;
    private final EmployeeDetailsRepository employeeDetailsRepository;

    public EmployeeServices(EmployeeRepository employeeRepository, EmployeeDetailsRepository employeeDetailsRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeDetailsRepository = employeeDetailsRepository;
    }

    // Retrieve all employees
    public List<Employee> getAllEmployees() {
        logger.info("Fetching all employees");
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

    // Retrieve an employee by ID
    public Employee getEmployeeById(Long id) {
        logger.info("Fetching employee with ID: {}", id);
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));
    }

    // Retrieve employee details by phone number
    public List<EmployeeDetails> getEmployeeDetailsByPhoneNumber(String phoneNumber) {
        logger.info("Fetching employee details for phone number: {}", phoneNumber);
        List<EmployeeDetails> details = employeeDetailsRepository.findByNewPhoneNumber(phoneNumber);
        if (details.isEmpty()) {
            throw new EmployeeNotFoundException("No employee details found for phone number: " + phoneNumber);
        }
        return details;
    }


    // Update employee details
    @Transactional
    public void updateEmployeeFromDTO(Long id, EmployeeDTO employeeDTO) {
        logger.info("Updating employee with ID: {}", id);
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));

        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setEmployee(employee);
        employeeDetails.setCreateDate(LocalDateTime.now());

        if (employeeDTO.getEmail() != null && !employeeDTO.getEmail().equals(employee.getEmail())) {
            employeeDetails.setOldEmail(employee.getEmail());
            employeeDetails.setNewEmail(employeeDTO.getEmail());
            employeeDetails.setEmailChangeTime(LocalDateTime.now());
            employee.setEmail(employeeDTO.getEmail());
        }

        if (employeeDTO.getPhoneNumber() != null && !employeeDTO.getPhoneNumber().equals(employee.getPhoneNumber())) {
            employeeDetails.setOldPhoneNumber(employee.getPhoneNumber());
            employeeDetails.setNewPhoneNumber(employeeDTO.getPhoneNumber());
            employeeDetails.setPhoneNumberChangeTime(LocalDateTime.now());
            employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        }

        employeeRepository.save(employee);

        if (employeeDetails.getOldEmail() != null || employeeDetails.getOldPhoneNumber() != null) {
            employeeDetailsRepository.save(employeeDetails);
        }

        logger.info("Employee updated successfully with ID: {}", id);
    }

    public Employee saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }
}








