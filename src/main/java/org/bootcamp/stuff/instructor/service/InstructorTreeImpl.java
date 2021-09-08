package org.bootcamp.stuff.instructor.service;

import org.bootcamp.stuff.instructor.service.Instructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorTreeImpl implements InstructorService {

    private Instructor admin = new Instructor("0", "admin", "administrator");
    private List<Instructor> subordinatesToBeAdded;
    private List<Instructor> subordinatesOfInstructor;
    private boolean foundAlready;

    @Override
    public List<Instructor> addSubordinates(String instructorId, List<Instructor> subordinates) {

        subordinatesToBeAdded = subordinates;
        addChildren(admin, instructorId);
        foundAlready = false;
        return null;
    }

    private void addChildren(Instructor instructor, String idToSearch) {
        if (foundAlready)
            return;         //end recursion
        if (instructor.getId().equalsIgnoreCase(idToSearch)) {
            instructor.getSubordinates().addAll(subordinatesToBeAdded);
            foundAlready = true;
        } else {
            for (Instructor instructor1 : instructor.getSubordinates()) {
                addChildren(instructor1, idToSearch);
            }
        }
    }

    @Override
    public List<Instructor> getSubordinates(String instructorId) {

        getChilderen(admin, instructorId);
        foundAlready = false;
        return subordinatesOfInstructor;
    }

    private void getChilderen(Instructor instructor, String instructorId) {
        if (foundAlready)
            return;         //end recursion
        if (instructor.getId().equalsIgnoreCase(instructorId)) {
            subordinatesOfInstructor = instructor.getSubordinates();
            foundAlready = true;
        } else {
            for (Instructor instructor1 : instructor.getSubordinates()) {
                getChilderen(instructor1, instructorId);
            }
        }
    }


}
