package com.employeeCurd.demo.learn;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

public class EmployeeDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number format. It should contain exactly 10 digits.")
    private String phoneNumber;  // Ensure the phone number is exactly 10 digits

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {  // Fixed the typo here
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {  // Fixed the parameter name here
        this.phoneNumber = phoneNumber;
    }
}
