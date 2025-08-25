package com.GetHired.Service;

import com.GetHired.DAO.RecruiterDAO;
import com.GetHired.Entities.Recruiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecruiterServices {
    @Autowired
    private RecruiterDAO recruiterDAO;

    public void addRecruiter(Recruiter recruiter){
        recruiterDAO.save(recruiter);
    }

    public Recruiter getById(int id){
        return recruiterDAO.findById(id);
    }




}
