package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructors")
public class Instructor extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long instructorid;
    @Column(nullable = false,
            unique = true)
    private String username;
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(nullable = false,
            unique = true)
    @Email
    private String primaryemail;
    //  classes
//    List<InstructorRoles> roles = new ArrayList<>();

    private String role;

    @OneToMany(mappedBy = "instructor",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnoreProperties(value = "instructor", allowSetters = true)
    List<FitnessClass> classes = new ArrayList<>();
    //  punchpasses



    public Instructor() {
    }


    public Instructor(String username, String password, String primaryemail, String role) {
        setUsername(username);
        setPassword(password);
        this.primaryemail = primaryemail;
        this.role = role; //= "INSTRUCTOR";
    }

    public long getInstructorid() {
        return instructorid;
    }

    public void setInstructorid(long instructorid) {
        this.instructorid = instructorid;
    }


    public List<FitnessClass> getClasses() {
        return classes;
    }

    public void setClasses(List<FitnessClass> classes) {
        this.classes = classes;
    }

//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }
    public void setPasswordNoEncrypt(String password) {
        this.password = password;
    }

    public String getPrimaryemail() {
        return primaryemail;
    }

    public void setPrimaryemail(String primaryemail) {
        this.primaryemail = primaryemail;
    }

    @JsonIgnore
    public List<SimpleGrantedAuthority> getAuthority()
    {
        List<SimpleGrantedAuthority> rtnList = new ArrayList<>();
        rtnList.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
//        rtnList.add(new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()));

        return rtnList;
    }
}
