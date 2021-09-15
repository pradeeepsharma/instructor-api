package org.bootcamp.stuff.instructor.rest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.bootcamp.stuff.instructor.service.Instructor;
import org.bootcamp.stuff.instructor.service.InstructorService;
import org.bootcamp.stuff.instructor.service.InstructorTreeImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(PowerMockRunner.class)

class InstructorControllerTest {
	
	private InstructorController controller;
	@Mock
	private InstructorService mockService;
	
	@BeforeEach
	public void setup() {

		//Whitebox.setInternalState(controller, "instructorService", mockService);
		//controller.setInstructorService(mockService);
		Mockito.when(mockService.addSubordinates(ArgumentMatchers.any(), ArgumentMatchers.anyList())).thenReturn(getMockObject());
		controller = new InstructorController(mockService);
		
	}

	@SuppressWarnings("deprecation")
	@Test
	void can_add_subordinates() {
		Instructor[] subordinates = new Instructor[]{};
		List<Instructor> addedSubOrdinates = controller.addSubOrdinates("", subordinates);
		assertEquals(2, addedSubOrdinates.size());
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
