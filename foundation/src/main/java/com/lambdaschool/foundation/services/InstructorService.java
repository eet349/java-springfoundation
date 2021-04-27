package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.Instructor;

import java.util.List;

public interface InstructorService {
    List<Instructor> findAll();
    Instructor findUserById(long id);
    Instructor findByName(String name);
    Instructor save(Instructor instructor);
}
