package org.bootcamp.stuff.instructor.service;

import org.bootcamp.stuff.instructor.service.Instructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface InstructorService {
    List<Instructor> addSubordinates(String instructorId, List<Instructor> asList) ;

    List<Instructor> getSubordinates(String instructorId);
}
