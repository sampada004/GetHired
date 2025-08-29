package com.GetHired.Controller;

import com.GetHired.Entities.Job;
import com.GetHired.Service.JobServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobServices jobServices;

    @PostMapping("/post")
    public void postJob(@RequestBody Job job){
        jobServices.postJob(job);
    }

}
