package org.bootcamp.stuff.instructor.service;

import java.util.ArrayList;
import java.util.List;

public class Instructor {
    private String id;
    private String name;
    private String designation;
    private List<Instructor> subordinates = new ArrayList<>();

    public Instructor(String id, String name, String designation) {
        this.id = id;
        this.name = name;
        this.designation = designation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public List<Instructor> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<Instructor> subordinates) {
        this.subordinates = subordinates;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", subordinates=" + subordinates +
                '}';
    }
}
