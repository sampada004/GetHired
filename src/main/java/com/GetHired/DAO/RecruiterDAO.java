package com.GetHired.DAO;

import com.GetHired.Entities.Recruiter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecruiterDAO extends CrudRepository<Recruiter , Integer> {

    Iterable<Recruiter> findAll();

    Recruiter findById(int id);

    Optional<Recruiter> findRecruiterByEmail(String email);






}
