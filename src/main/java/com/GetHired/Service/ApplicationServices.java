package com.GetHired.Service;

import com.GetHired.DAO.ApplicationDAO;
import com.GetHired.Entities.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationServices {

    @Autowired
    private ApplicationDAO applicationDAO;

    public Application applyForJob(Application application) {
        return applicationDAO.save(application);
    }

    public List<Application> getApplicationsByJobId(Long jobId) {
        List<Application> applications = new ArrayList<>();
        Iterable<Application> applicationIterable = applicationDAO.findByJobId(jobId);

        for (Application application : applicationIterable) {
            applications.add(application);
        }

        return applications;
    }

    /**
     * Retrieves all applications submitted by a specific employee, including job details.
     * The Job details are automatically fetched due to the @ManyToOne relationship.
     * @param employeeId The ID of the employee.
     * @return A list of Application objects for the given employee.
     */
    public List<Application> getApplicationsByEmployeeId(Long employeeId) {
        return applicationDAO.findByEmployeeId(employeeId);
    }
}
