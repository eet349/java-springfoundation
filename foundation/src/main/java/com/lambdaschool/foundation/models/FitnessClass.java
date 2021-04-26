package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "classes")
public class FitnessClass extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long classid;
    private String name;
    private String type;
    private Date starttime;
    private int duration;
    private String intensitylevel;  //  BEGINNER, INTERMEDIATE, ADVANCED
    private String location;
    private int numregisteredattendees;

    @ManyToOne
    @JoinColumn(name = "instructorid", nullable = false)
    @JsonIgnoreProperties(value = "classes", allowSetters = true)
    private Instructor instructor;

    @ManyToMany(mappedBy = "classes")
    private Set<User> users = new HashSet<>();

    public FitnessClass() {
    }
    //
    //  TODO: Create the nonempty constructor once we have all the field/relational fields
    //


    public FitnessClass(String name,
                        String type,
                        Date starttime,
                        int duration,
                        String intensitylevel,
                        String location,
                        int numregisteredattendees,
                        Instructor instructor) {
        this.name = name;
        this.type = type;
        this.starttime = starttime;
        this.duration = duration;
        this.intensitylevel = intensitylevel;
        this.location = location;
        this.numregisteredattendees = numregisteredattendees;
        this.instructor = instructor;
    }

    public long getClassid() {
        return classid;
    }

    public void setClassid(long classid) {
        this.classid = classid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getIntensitylevel() {
        return intensitylevel;
    }

    public void setIntensitylevel(String intensitylevel) {
        this.intensitylevel = intensitylevel;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumregisteredattendees() {
        return numregisteredattendees;
    }

    public void setNumregisteredattendees(int numregisteredattendees) {
        this.numregisteredattendees = numregisteredattendees;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    //
    //  TODO: Create getters and setter for the relational fields
    //

}
