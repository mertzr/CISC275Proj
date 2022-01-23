import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SharkTest {
	Shark s = new Shark(5, 5, "shark", SharkMode.EAT);
	@Test
	void testgetMode() {
		assertEquals(SharkMode.EAT, s.getMode());
	}
	@Test
	void testsetMode() {
		s.setMode(SharkMode.DEFAULT);
		assertEquals(SharkMode.DEFAULT, s.getMode());
	}
}
