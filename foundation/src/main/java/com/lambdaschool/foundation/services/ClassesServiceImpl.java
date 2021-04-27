package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.exceptions.ResourceNotFoundException;
import com.lambdaschool.foundation.models.FitnessClass;
import com.lambdaschool.foundation.models.User;
import com.lambdaschool.foundation.repository.ClassesRepository;
import com.lambdaschool.foundation.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Transactional
@Service(value = "classesService")
public class ClassesServiceImpl implements ClassesService{
    @Autowired
    private ClassesRepository classesrepo;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private HelperFunctions helperFunctions;

    @Override
    public List<FitnessClass> findAll() {
        List<FitnessClass> list = new ArrayList<>();

        classesrepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public FitnessClass findFitnessClassById(Long id) {
        return classesrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor id " + id + " not found!"));
    }

    @Transactional
    @Override
    public FitnessClass save(FitnessClass fitnessClass) {
        System.out.println("made it this far");
        FitnessClass newFitnessClass = new FitnessClass();
        if (fitnessClass.getClassid() != 0 ){
            classesrepo.findById(fitnessClass.getClassid())
                    .orElseThrow(() -> new ResourceNotFoundException("Fitness class " + fitnessClass.getClassid() + " not found!"));
        }

        newFitnessClass.setName(fitnessClass.getName());
        newFitnessClass.setType(fitnessClass.getType());
        newFitnessClass.setDate(fitnessClass.getDate());
        newFitnessClass.setStarttime(fitnessClass.getStarttime());
        newFitnessClass.setDuration(fitnessClass.getDuration());
        newFitnessClass.setIntensitylevel(fitnessClass.getIntensitylevel().toUpperCase());
        newFitnessClass.setLocation(fitnessClass.getLocation());
        newFitnessClass.setNumregisteredattendees(fitnessClass.getNumregisteredattendees());
        newFitnessClass.setMaxsize(fitnessClass.getMaxsize());
        newFitnessClass.setInstructor(instructorRepository.findInstructorByInstructorid(fitnessClass.getInstructor().getInstructorid()));

        newFitnessClass.getUsers().clear();
        for(User u : fitnessClass.getUsers()) {
            newFitnessClass.getUsers().add(u);
        }

        return classesrepo.save(newFitnessClass);
    }

    @Override
    public FitnessClass update(FitnessClass fitnessClass, long classid) {
        FitnessClass currentFitnessClass = findFitnessClassById(classid);

        if (classesrepo.findById(classid).isPresent()){


            if(helperFunctions.isAuthorizedToMakeChange(classesrepo.findById(classid).get().getInstructor().getUsername())) {
                if(fitnessClass.getName() != null) {
                     currentFitnessClass.setName(fitnessClass.getName());
                }
                if (fitnessClass.getType() != null) {
                    currentFitnessClass.setType(fitnessClass.getType());
                }
                if (fitnessClass.getDate() != null) {
                    currentFitnessClass.setDate(fitnessClass.getDate());
                }
                if (fitnessClass.getStarttime() != null) {
                    currentFitnessClass.setStarttime(fitnessClass.getStarttime());
                }
                if (fitnessClass.hasduration) {
                    currentFitnessClass.setDuration(fitnessClass.getDuration());
                }
                if (fitnessClass.getIntensitylevel() != null) {
                    currentFitnessClass.setIntensitylevel(fitnessClass.getIntensitylevel());
                }
                if (fitnessClass.getLocation() != null) {
                    currentFitnessClass.setLocation(fitnessClass.getLocation());
                }
                if (fitnessClass.hasnumregisteredattendees) {
                    currentFitnessClass.setNumregisteredattendees(fitnessClass.getNumregisteredattendees());
            }
               if (fitnessClass.hasmaxsize) {
                    currentFitnessClass.setMaxsize(fitnessClass.getMaxsize());
                }
            } else {
                throw new ResourceNotFoundException("This user is not authorized to make this change.");
            }
        } else {
            throw new ResourceNotFoundException("Class with id " + classid + " not found!");
        }
        return null;
    }

    public void delete(long id) {
        classesrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Class " + id + " Not Found!"));
        classesrepo.deleteById(id);
    }
}
