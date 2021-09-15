package org.bootcamp.stuff.instructor.rest;

import org.bootcamp.stuff.instructor.service.Instructor;
import org.bootcamp.stuff.instructor.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class InstructorController {
    private InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/subordinates",produces = {"application/json"})
    public ResponseEntity<List<Instructor>> getSubOrdinates(@RequestParam(value = "id") String instructorId){

        List<Instructor> subordinates = instructorService.getSubordinates(instructorId);
        return new ResponseEntity<>(subordinates, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/subordinates",produces = {"application/json"})
    public List<Instructor> addSubOrdinates(@RequestParam(value = "id") String id, @RequestBody Instructor[] subordinates){

        return instructorService.addSubordinates(id, Arrays.asList(subordinates));
    }

    public void setInstructorService(InstructorService instructorService) {
        this.instructorService = instructorService;
    }
}
