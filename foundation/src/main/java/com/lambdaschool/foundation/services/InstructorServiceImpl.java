package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.exceptions.ResourceNotFoundException;
import com.lambdaschool.foundation.models.*;
import com.lambdaschool.foundation.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "instructorService")
public class InstructorServiceImpl implements InstructorService{
    @Autowired
    private InstructorRepository instructorrepo;

    @Autowired
    private HelperFunctions helperFunctions;

    @Override
    public List<Instructor> findAll() {
        List<Instructor> list = new ArrayList<>();

        instructorrepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Instructor findUserById(long id) throws ResourceNotFoundException {

        return instructorrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor id " + id + " not found!"));
    }
    @Transactional
    @Override
    public Instructor save(Instructor instructor) {

        Instructor newInstructor = new Instructor();

        if (instructor.getInstructorid() != 0)
        {
            instructorrepo.findById(instructor.getInstructorid())
                    .orElseThrow(() -> new ResourceNotFoundException("Instructor id " + instructor.getInstructorid() + " not found!"));
            newInstructor.setInstructorid(instructor.getInstructorid());
        }

        newInstructor.setUsername(instructor.getUsername()
                .toLowerCase());
        newInstructor.setPasswordNoEncrypt(instructor.getPassword());
        newInstructor.setPrimaryemail(instructor.getPrimaryemail()
                .toLowerCase());

//        newInstructor.getRole()
//                .clear();
//        for (UserRoles ur : instructor.getRole())
//        {
//            Role addRole = roleService.findRoleById(ur.getRole()
//                    .getRoleid());
//            newUser.getRoles()
//                    .add(new UserRoles(newUser,
//                            addRole));
////        }

        newInstructor.getClasses()
                .clear();
        for (FitnessClass fc : instructor.getClasses())
        {
            newInstructor.getClasses()
                    .add(new FitnessClass(fc.getName(), fc.getType(), fc.getStarttime(), fc.getDuration(), fc.getIntensitylevel(), fc.getLocation(), fc.getUsers().size(), newInstructor));
        }

        return instructorrepo.save(newInstructor);
    }
}
