package com.lambdaschool.foundation.repository;

import com.lambdaschool.foundation.models.Instructor;
import org.springframework.data.repository.CrudRepository;

public interface InstructorRepository extends CrudRepository<Instructor, Long> {
//    Instructor findInstructorByUsername();
    Instructor findInstructorByInstructorid(Long id);
    Instructor findInstructorByUsername(String name);
//    Instructor save(Instructor instructor);
}
