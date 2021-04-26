package com.lambdaschool.foundation.controllers;

import com.lambdaschool.foundation.models.FitnessClass;
import com.lambdaschool.foundation.services.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/classes")
public class FitnessClassesController {
    @Autowired
    private ClassesService classesService;

    //  GET         /classes/classes
    @GetMapping(value = "/classes", produces = "application/json")
    public ResponseEntity<?> getAllFitnessClasses() {
        List<FitnessClass> list = classesService.findAll();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    //  POST 		/classes/class - create a class
    @PreAuthorize("hasAnyRole('INSTRUCTOR')")
    @PostMapping(value = "/class", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addFitnessClass() {


        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    //  PUT/PATCH	/classes/class/{classid} - edit a class
    @PatchMapping(value = "/class/{classid}")
    public ResponseEntity<?> updateFitnessClass() {

        return new ResponseEntity<>(HttpStatus.OK);
    }
    //  DELETE		/classes/class/{classid} - delete a class
    @DeleteMapping(value = "/class/{classid}")
    public ResponseEntity<?> deleteFitnessClass() {

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
