package org.bootcamp.stuff.instructor.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.powermock.reflect.Whitebox;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class InstructorTreeImplTest {

	private InstructorService instructorService;

	@BeforeEach
	public void init() {
		instructorService = new InstructorTreeImpl();
	}

	@Test
	void can_add_subordinates() {
		instructorService.addSubordinates("0", getSubordinates(2));
		Instructor admin = Whitebox.getInternalState(instructorService, "admin");
		assertEquals(2, admin.getSubordinates().size());
	}

	@Test
	void can_not_add_subordinates() {
		instructorService.addSubordinates("0", getSubordinates(0));
		Instructor admin = Whitebox.getInternalState(instructorService, "admin");
		assertEquals(0, admin.getSubordinates().size());
	}

	@Test
	void can_add_subordinatesa_at_deep_level() {
		Whitebox.setInternalState(instructorService, "admin", getMockAdmin());
		instructorService.addSubordinates("2", getSubordinates(2));
		Instructor admin = Whitebox.getInternalState(instructorService, "admin");
		assertEquals(2, admin.getSubordinates().get(1).getSubordinates().size());
	}

	@Test
	void can_get_subordinates_of_admin() {
		Whitebox.setInternalState(instructorService, "admin", getMockAdmin());
		List<Instructor> subordinates = instructorService.getSubordinates("0");
		assertEquals(2, subordinates.size());
	}

	@Test
	void can_get_subordinates_of_subordinate() {
		Instructor mockAdmin = getMockAdmin();
		mockAdmin.getSubordinates().get(0).setSubordinates(getSubordinates(3));
		Whitebox.setInternalState(instructorService, "admin", mockAdmin);
		List<Instructor> subordinates = instructorService.getSubordinates("1");
		assertEquals(3, subordinates.size());
	}

	private List<Instructor> getSubordinates(int numberOfSubordinates) {
		List<Instructor> subordinatessToBeAdded = new ArrayList<Instructor>();
		for (int times = 1; times <= numberOfSubordinates; times++) {
			Instructor subordinate = new Instructor("" + times, "Subordinate_" + times, "subordinate");
			subordinatessToBeAdded.add(subordinate);
		}
		return subordinatessToBeAdded;
	}

	private Instructor getMockAdmin() {
		Instructor admin = new Instructor("0", "Admin", "admin");
		admin.setSubordinates(getSubordinates(2));
		return admin;
	}

}
