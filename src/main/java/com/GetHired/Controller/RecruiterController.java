package com.GetHired.Controller;

import com.GetHired.Entities.Recruiter;
import com.GetHired.Service.RecruiterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recruiter")
public class RecruiterController {

    @Autowired
    private RecruiterServices recruiterServices;

    @PostMapping("/add")
    public void add(@RequestBody Recruiter recruiter){
        recruiterServices.addRecruiter(recruiter);
    }





}
