import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AnimalTest {
	Animal s = new Animal(5,5,"shark","src/assets/shark/default.png");
	@Test
	void testmoveDown() {
		s.moveDown(5);
		assertEquals(10,s.yloc);
	}
	@Test
	void testmoveUp() {
		s.moveUp(5);
		assertEquals(0,s.yloc);
	}
	@Test
	void testmoveRight() {
		s.moveRight(5);
		assertEquals(10,s.xloc);
	}
	@Test
	void testmoveLeft() {
		s.moveLeft(5);
		assertEquals(0,s.xloc);
	}
	@Test
	void testgetName() {
		assertEquals("shark",s.getName());
	}
	@Test
	void testgetImage() {
		assertEquals("src/assets/shark/default.png",s.getImage());
	}
}
