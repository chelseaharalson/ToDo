package model;

import static org.junit.Assert.*;
import org.junit.Test;

public class ToDoListTest {
	@Test
	public void testIsValidDate1() {
		String input = "01/02/2017";
		ToDoList tdl = new ToDoList();
		assertEquals(tdl.isValidDate(input), true);
	}
	
	@Test
	public void testIsValidDate2() {
		String input = "01-02-2017";
		ToDoList tdl = new ToDoList();
		assertEquals(tdl.isValidDate(input), false);
	}
	
	@Test
	public void testIsValidDate3() {
		String input = "2/2/2017";
		ToDoList tdl = new ToDoList();
		assertEquals(tdl.isValidDate(input), false);
	}
	
	@Test
	public void testIsValidDate4() {
		String input = "02/30/2017";
		ToDoList tdl = new ToDoList();
		assertEquals(tdl.isValidDate(input), false);
	}
	
	@Test
	public void testIsValidDate5() {
		String input = "02/29/2019";
		ToDoList tdl = new ToDoList();
		assertEquals(tdl.isValidDate(input), false);
	}
	
	@Test
	public void testIsValidDate6() {
		String input = "02/29/2020";
		ToDoList tdl = new ToDoList();
		assertEquals(tdl.isValidDate(input), true);
	}
	
	@Test
	public void testIsValidDate7() {
		String input = "Chelsea";
		ToDoList tdl = new ToDoList();
		assertEquals(tdl.isValidDate(input), false);
	}
	
	@Test
	public void testIsValidDate8() {
		String input = "2/14/2017";
		ToDoList tdl = new ToDoList();
		assertEquals(tdl.isValidDate(input), false);
	}
	
	@Test
	public void testIsValidDate9() {
		String input = "05/1/2017";
		ToDoList tdl = new ToDoList();
		assertEquals(tdl.isValidDate(input), false);
	}
	
	@Test
	public void testIsValidDate10() {
		String input = "08/19/20";
		ToDoList tdl = new ToDoList();
		assertEquals(tdl.isValidDate(input), false);
	}
	
	@Test
	public void testIsValidDate11() {
		String input = "35/19/2022";
		ToDoList tdl = new ToDoList();
		assertEquals(tdl.isValidDate(input), false);
	}
	
	@Test
	public void testIsValidDate12() {
		String input = "12/40/2045";
		ToDoList tdl = new ToDoList();
		assertEquals(tdl.isValidDate(input), false);
	}

	/*@Test
	public void testToDoList() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddTask() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteTask() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditTask() {
		fail("Not yet implemented");
	}

	@Test
	public void testCompleteTask() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAllTasks() {
		fail("Not yet implemented");
	}

	@Test
	public void testCompleteAllTasks() {
		fail("Not yet implemented");
	}

	@Test
	public void testShowAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testShowNotCompleted() {
		fail("Not yet implemented");
	}

	@Test
	public void testToDoObjToAjaxString() {
		fail("Not yet implemented");
	}*/

}
