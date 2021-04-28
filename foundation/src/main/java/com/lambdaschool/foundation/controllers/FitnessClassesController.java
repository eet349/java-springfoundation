package com.lambdaschool.foundation.controllers;

import com.lambdaschool.foundation.models.FitnessClass;
import com.lambdaschool.foundation.models.Instructor;
import com.lambdaschool.foundation.services.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
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
//    @PostMapping(value = "/class",
//            consumes = "application/json")
    @PreAuthorize("hasAnyRole('INSTRUCTOR')")
    @PostMapping(value = "/class", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addFitnessClass(
            @Valid
            @RequestBody
                    FitnessClass newfitnessclass) throws URISyntaxException
{
        newfitnessclass.setClassid(0);
        newfitnessclass = classesService.save(newfitnessclass);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{classid}")
                .buildAndExpand(newfitnessclass.getClassid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(newfitnessclass,
                responseHeaders,
                HttpStatus.CREATED);
    }

    //  PATCH	/classes/class/{classid} - edit a class
    @PreAuthorize("hasAnyRole('INSTRUCTOR')")
    @PatchMapping(value = "/class/{classid}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateFitnessClass(@RequestBody FitnessClass updateFitnessClass, @PathVariable long classid) {

        FitnessClass rtnFitnessClass = classesService.update(updateFitnessClass, classid);

        return new ResponseEntity<>(rtnFitnessClass, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('INSTRUCTOR')")
    @PutMapping(value = "/class/{classid}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateCompleteFitnessClass(@Valid @RequestBody FitnessClass updateFitnessClass, @PathVariable long classid) {

        updateFitnessClass.setClassid(classid);
        FitnessClass rtnFitnessClass = classesService.save(updateFitnessClass);

        return new ResponseEntity<>(rtnFitnessClass, HttpStatus.OK);
    }

//     @PreAuthorize("hasAnyRole('CLIENT')")
    @PatchMapping(value = "/class/{classid}/addclient/{clientid}",/* consumes = "application/json",*/ produces = "application/json")
    public ResponseEntity<?> addClientToClass( @PathVariable long classid , @PathVariable long clientid) {

         FitnessClass rtnFitnessClass = classesService.addClient(classid, clientid);

         return new ResponseEntity<>(rtnFitnessClass, HttpStatus.OK);
     }
//    @PreAuthorize("hasAnyRole('CLIENT')")
    @PatchMapping(value = "/class/{classid}/removeclient/{clientid}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> removeClientFromClass( @PathVariable long classid, @PathVariable long clientid) {

        FitnessClass rtnFitnessClass = classesService.removeClient(classid, clientid);

        return new ResponseEntity<>(rtnFitnessClass, HttpStatus.OK);
    }


    //  DELETE		/classes/class/{classid} - delete a class
    @PreAuthorize("hasAnyRole('INSTRUCTOR')")
    @DeleteMapping(value = "/class/{classid}")
    public ResponseEntity<?> deleteFitnessClass(@PathVariable long classid) {
    classesService.delete(classid);
        return new ResponseEntity<>(classid, HttpStatus.OK);
    }

}
