package com.GetHired.Controller;

import com.GetHired.Entities.Employee;
import com.GetHired.Service.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;

    @PostMapping("/add")
    public void add(@RequestBody Employee employee){
        employeeServices.addEmployee(employee);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData, HttpSession session) {
        try {
            String email = loginData.get("email");
            String password = loginData.get("password");

            if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Email and password cannot be empty."));
            }

            Optional<Employee> employee = employeeServices.findByEmail(email);

            if (employee.isPresent() && employee.get().getPassword().equals(password)) {
                // Store email in session
                session.setAttribute("loggedInEmail", email);

                Map<String, Object> response = Map.of(
                        "employeeId", employee.get().getId(),
                        "name", employee.get().getName(),
                        "degree", employee.get().getDegree(),
                        "passoutYear", employee.get().getPassout_year(),
                        "skills", employee.get().getSkills()
                );
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "Invalid email or password."));
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An unexpected error occurred."));
        }
    }

    @GetMapping("/profile")
    public Employee getProfile(HttpSession session) {
        // Get logged-in user's email from session
        String email = (String) session.getAttribute("loggedInEmail");

        if (email == null) {
            throw new RuntimeException("User not logged in");
        }

        Optional<Employee> employeeOpt = employeeServices.findByEmail(email);
        return employeeOpt.orElseThrow(() -> new RuntimeException("Employee not found"));
    }
}
