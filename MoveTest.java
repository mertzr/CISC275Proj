import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MoveTest {
	Move m = new Move(5,5);
	@Test
	void testUpdateLocation() {
		m.updateLocation(5, 5);
		assertEquals(new Move(10,10).getX(),m.getX());
		assertEquals(new Move(10,10).getY(),m.getY());
	}
	@Test
	void testSetLocation() {
		m.setLocation(7, 7);
		assertEquals(new Move(7,7).getX(),m.getX());
		assertEquals(new Move(7,7).getY(),m.getY());
	}
	@Test
	void testgetX() {
		assertEquals(7,m.getX());
	}
	@Test
	void testgetY() {
		assertEquals(7,m.getY());
	}
}
