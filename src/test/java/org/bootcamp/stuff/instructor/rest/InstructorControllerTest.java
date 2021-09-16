package org.bootcamp.stuff.instructor.rest;

import org.bootcamp.stuff.instructor.service.Instructor;
import org.bootcamp.stuff.instructor.service.InstructorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
public class InstructorControllerTest {
	
	private InstructorController controller;
	@MockBean
	private InstructorService mockService;
	
	@BeforeEach
	public void setup() {

		//Whitebox.setInternalState(controller, "instructorService", mockService);
		//controller.setInstructorService(mockService);
		controller = new InstructorController(mockService);
		Mockito.when(mockService.addSubordinates(ArgumentMatchers.any(), ArgumentMatchers.anyList())).thenReturn(getMockObject());

		
	}

	@SuppressWarnings("deprecation")
	@Test
	void can_add_subordinates() {
		Instructor[] subordinates = new Instructor[]{};
		ResponseEntity<Void> responseEntity = controller.addSubOrdinates("", subordinates);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}

	private List<Instructor> getMockObject() {
		Instructor admin = new Instructor("0", "Admin", "admin");
		admin.setSubordinates(getSubordinates(2));
		return admin.getSubordinates();
	}
	
	private List<Instructor> getSubordinates(int numberOfSubordinates) {
		List<Instructor> subordinatessToBeAdded = new ArrayList<Instructor>();
		for (int times = 1; times <= numberOfSubordinates; times++) {
			Instructor subordinate = new Instructor("" + times, "Subordinate_" + times, "subordinate");
			subordinatessToBeAdded.add(subordinate);
		}
		return subordinatessToBeAdded;
	}

}
