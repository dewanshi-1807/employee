package com.employeeCurd.demo.learn;

import com.employeeCurd.demo.learn.Entity.Employee;
import com.employeeCurd.demo.learn.Entity.EmployeeDetails;
import com.employeeCurd.demo.learn.Repository.EmployeeDetailsRepository;
import jakarta.persistence.EntityNotFoundException;
import com.employeeCurd.demo.learn.InvalidEmployeeDataException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeServices employeeServices;

    @Autowired
    private EmployeeServices employeeService;

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);  // Initialize logger

    public EmployeeController(EmployeeServices employeeServices) {
        this.employeeServices = employeeServices;
    }


    // GET: Retrieve employee details by phone number
    @GetMapping("/{phoneNumber}")
    public ResponseEntity<EmployeeDetails> getEmployeeDetailsByPhoneNumber(@PathVariable String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            log.warn("Invalid phone number provided: {}", phoneNumber);
            throw new InvalidEmployeeDataException("Phone number cannot be null or blank");
        }

        try {
            // Retrieve employee details by phone number
            List<EmployeeDetails> employeeDetailsList = employeeServices.getEmployeeDetailsByPhoneNumber(phoneNumber);

            // If no employee details are found, return 404
            if (employeeDetailsList.isEmpty()) {
                log.warn("No employee details found for phone number: {}", phoneNumber);
                throw new EntityNotFoundException("No employee details found for phone number: " + phoneNumber);
            }
            // Return the first employee details found
            EmployeeDetails employeeDetails = employeeDetailsList.get(0);
            log.info("Employee details retrieved for phone number: {}", phoneNumber);
            return ResponseEntity.ok(employeeDetails);

        } catch (EntityNotFoundException ex) {
            log.error("Employee details not found for phone number: {}", phoneNumber);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (InvalidEmployeeDataException ex) {
            log.error("Invalid data for phone number: {}", phoneNumber);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception ex) {
            log.error("Unexpected error while retrieving employee details", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    // POST: Add a new employee
    @PostMapping ("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        try {
            Employee savedEmployee = employeeService.saveEmployee(employee);
            return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}



//    //DELETE: Remove an employee by ID*
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
//        try {
//            // Delete the employee from the database
//            employeeServices.deleteEmployee(id); // Highlighted: Deleting via service
//            return ResponseEntity.ok("Employee with ID " + id + " has been deleted."); // Highlighted: Success response
//        } catch (EmployeeNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        } catch (Exception e) {
//            // Handle the case when the employee is not found
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An internal error occurred: " + e.getMessage());
//        }
//    }


