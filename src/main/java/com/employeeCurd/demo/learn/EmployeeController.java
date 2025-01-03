package com.employeeCurd.demo.learn;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeServices employeeServices;
    private EmployeeRepository employeeRepository;

    private final List<Employee> employeeList = new ArrayList<>();

// Constructor injection for EmployeeServices
    public EmployeeController(EmployeeServices employeeServices) {
        this.employeeServices = employeeServices;
    }

    // GET: Retrieve all employees*
    @GetMapping
    public List<Employee> getEmployees() {
        //return employeeList;
        return this.employeeServices.getAllEmployees();
    }

    // POST: Add a new employee*
    @PostMapping("/create")
    public ResponseEntity<String> addEmployee(@Validated @RequestBody EmployeeDTO newEmployee) {
        try {
            employeeServices.createEmployeeFromDTO(newEmployee);
            return ResponseEntity.ok("Employee created successfully");
        } catch (InvalidEmployeeDataException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An internal error occurred: " + e.getMessage());

        }
    }

    // PUT: Update an existing employee*
    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @Validated @RequestBody EmployeeDTO employeeDTO) {
        try {
            employeeServices.updateEmployeeFromDTO(id, employeeDTO);
            return ResponseEntity.ok("Employee updated successfully");
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (InvalidEmployeeDataException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }

    //DELETE: Remove an employee by ID*
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        try {
            // Delete the employee from the database
            employeeServices.deleteEmployee(id); // Highlighted: Deleting via service
            return ResponseEntity.ok("Employee with ID " + id + " has been deleted."); // Highlighted: Success response
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            // Handle the case when the employee is not found
            return ResponseEntity.status(500).body("An internal error occurred: " + e.getMessage());
        }
    }
}

