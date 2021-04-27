package com.lambdaschool.foundation;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.lambdaschool.foundation.models.*;
import com.lambdaschool.foundation.repository.ClassesRepository;
import com.lambdaschool.foundation.repository.InstructorRepository;
import com.lambdaschool.foundation.services.ClassesService;
import com.lambdaschool.foundation.services.InstructorService;
import com.lambdaschool.foundation.services.RoleService;
import com.lambdaschool.foundation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * SeedData puts both known and random data into the database. It implements CommandLineRunner.
 * <p>
 * CoomandLineRunner: Spring Boot automatically runs the run method once and only once
 * after the application context has been loaded.
 */
@Transactional
@ConditionalOnProperty(
    prefix = "command.line.runner",
    value = "enabled",
    havingValue = "true",
    matchIfMissing = true)
@Component
public class SeedData
    implements CommandLineRunner
{
    /**
     * Connects the Role Service to this process
     */
    @Autowired
    RoleService roleService;

    /**
     * Connects the user service to this process
     */
    @Autowired
    UserService userService;

    @Autowired
    InstructorService instructorService;

    @Autowired
    InstructorRepository instructorrepo;

    @Autowired
    ClassesService classesService;

    @Autowired
    ClassesRepository classesrepo;
    /**
     * Generates test, seed data for our application
     * First a set of known data is seeded into our database.
     * Second a random set of data using Java Faker is seeded into our database.
     * Note this process does not remove data from the database. So if data exists in the database
     * prior to running this process, that data remains in the database.
     *
     * @param args The parameter is required by the parent interface but is not used in this process.
     */
    @Transactional
    @Override
    public void run(String[] args) throws
                                   Exception
    {
        userService.deleteAll();
//        roleService.deleteAll();
//        Role r1 = new Role("client");
//        Role r2 = new Role("instructor");
//
//        r1 = roleService.save(r1);
//        r2 = roleService.save(r2);


        // admin, data, user
        User u1 = new User("admin",
            "password",
            "admin@lambdaschool.local", "INSTRUCTOR");
//        u1.getRoles()
//            .add(new UserRoles(u1,
//                r2));
//        u1.setRole("INSTRUCTOR");

        u1.getUseremails()
            .add(new Useremail(u1,
                "admin@email.local"));
        u1.getUseremails()
            .add(new Useremail(u1,
                "admin@mymail.local"));


        userService.save(u1);


        // data, user
        User u2 = new User("cinnamon",
            "1234567",
            "cinnamon@lambdaschool.local", "CLIENT");
//        u2.getRoles()
//            .add(new UserRoles(u2,
//                r2));
        u2.getUseremails()
            .add(new Useremail(u2,
                "cinnamon@mymail.local"));
        u2.getUseremails()
            .add(new Useremail(u2,
                "hops@mymail.local"));
        u2.getUseremails()
            .add(new Useremail(u2,
                "bunny@email.local"));
        userService.save(u2);

        // user
        User u3 = new User("barnbarn",
            "ILuvM4th!",
            "barnbarn@lambdaschool.local", "CLIENT");
//        u3.getRoles()
//            .add(new UserRoles(u3,
//                r2));
        u3.getUseremails()
            .add(new Useremail(u3,
                "barnbarn@email.local"));
        userService.save(u3);

        User u4 = new User("puttat",
            "password",
            "puttat@school.lambda", "CLIENT");
//        u4.getRole()
//            .add(new UserRoles(u4,
//                r2));
        userService.save(u4);

        User u5 = new User("misskitty",
            "password",
            "misskitty@school.lambda", "CLIENT");
//        u5.getRole()
//            .add(new UserRoles(u5,
//                r2));
        userService.save(u5);

        // Instructor
        Instructor i1 = new Instructor("instructor", "password", "instructor@lambda.com", "INSTRUCTOR" );
        i1.getClasses().add(new FitnessClass("Pilates for beginners",
                "PILATES",
//                new Date(),
                "May 28th 21",
                "6:15 am",
                45,
                "BEGINNER",
                "online",
                0,
                30,
                i1));

        Instructor i2 = new Instructor("Chad", "fit4ever", "sofit@sofit.com", "INSTRUCTOR");

        Instructor i3 = new Instructor("Brad", "xfit4lyfe", "xfit@xfit.com", "INSTRUCTOR");
//        i3.setInstructorid(3);

        instructorrepo.save(i1);
        instructorrepo.save(i2);
        instructorrepo.save(i3);
//        instructorService.save(i1);
//        instructorService.save(i2);
//        instructorService.save(i3);
        // Fitness Classes
        FitnessClass f1 = new FitnessClass("Itermediate Cross Fit", "XFIT"/* new Date(),*/,"May 28th 21", "6:15 am",  60, "INTERMEDIATE", "downtown la", 0, 30,i3 );
        f1.setInstructor(i3);

//        classesService.save(f1);
        classesrepo.save(f1);

        //-----------------------------------------------------------
        if (false)
        {
            // using JavaFaker create a bunch of regular users
            // https://www.baeldung.com/java-faker
            // https://www.baeldung.com/regular-expressions-java

            FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"),
                new RandomService());
            Faker nameFaker = new Faker(new Locale("en-US"));

            for (int i = 0; i < 25; i++)
            {
                new User();
                User fakeUser;

                fakeUser = new User(nameFaker.name()
                    .username(),
                    "password",
                    nameFaker.internet()
                        .emailAddress(),  "CLIENT");
//                fakeUser.getRoles()
//                    .add(new UserRoles(fakeUser,
//                        r2));
                fakeUser.getUseremails()
                    .add(new Useremail(fakeUser,
                        fakeValuesService.bothify("????##@gmail.com")));
                userService.save(fakeUser);
            }
        }
    }
}