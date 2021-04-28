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
    private String date;
    private String starttime;
    private int duration;
    private String intensitylevel;  //  BEGINNER, INTERMEDIATE, ADVANCED
    private String location;
    private int numregisteredattendees;
    private int maxsize;

    @Transient
    public boolean hasduration = false;
    @Transient
    public boolean hasnumregisteredattendees = false;
    @Transient
    public boolean hasmaxsize = false;


    @ManyToOne
    @JoinColumn(name = "instructorid", nullable = false)
    @JsonIgnoreProperties(value = "classes", allowSetters = true)
    private Instructor instructor;

    @ManyToMany(mappedBy = "classes")
    @JsonIgnoreProperties(value = "classes")
    private Set<User> users = new HashSet<>();

    public FitnessClass() {
    }
    //
    //  TODO: Create the nonempty constructor once we have all the field/relational fields
    //


    public FitnessClass(String name,
                        String type,
                        String date,
                        String starttime,
                        int duration,
                        String intensitylevel,
                        String location,
                        int numregisteredattendees,
                        int maxsize,
                        Instructor instructor) {
        this.name = name;
        this.type = type;
        this.date = date;
        this.starttime = starttime;
        this.duration = duration;
        this.intensitylevel = intensitylevel;
        this.location = location;
        this.numregisteredattendees = numregisteredattendees;
        this.maxsize = maxsize;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        hasduration = true;
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
        hasnumregisteredattendees = true;
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


    public int getMaxsize() {
        return maxsize;
    }

    public void setMaxsize(int maxsize) {
        hasmaxsize = true;
        this.maxsize = maxsize;
    }
}
