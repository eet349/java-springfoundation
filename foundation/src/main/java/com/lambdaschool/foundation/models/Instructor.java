package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructors")
public class Instructor extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long instructorid;
    //  name
    private String name;
    //  classes
    @OneToMany(mappedBy = "instructor",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnoreProperties(value = "instructor", allowSetters = true)
    List<FitnessClass> classes = new ArrayList<>();
    //  punchpasses
}
