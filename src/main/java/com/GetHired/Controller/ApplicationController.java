package com.GetHired.Controller;

import com.GetHired.Entities.Application;
import com.GetHired.Service.ApplicationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private ApplicationServices applicationServices;

    @PostMapping("/apply")
    public ResponseEntity<Application> applyForJob(@RequestBody Application application) {
        Application newApplication = applicationServices.applyForJob(application);
        return new ResponseEntity<>(newApplication, HttpStatus.CREATED);
    }

    @GetMapping("/for-job/{jobId}")
    public ResponseEntity<List<Application>> getApplicationsByJobId(@PathVariable("jobId") Long jobId) {
        List<Application> applications = applicationServices.getApplicationsByJobId(jobId);
        if (applications.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

    @GetMapping("/for-employee/{employeeId}")
    public ResponseEntity<List<Application>> getApplicationsByEmployeeId(@PathVariable("employeeId") Long employeeId) {
        List<Application> applications = applicationServices.getApplicationsByEmployeeId(employeeId);
        if (applications.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }



}
