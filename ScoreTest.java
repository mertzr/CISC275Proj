import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ScoreTest {
	Score low = new Score(10);

	@Test
	void testAddPoints() {
		low.addPoints(5);
		assertEquals(15,low.getScore());
	}
	@Test
	void testSubPoints() {
		low.subPoints(5);
		assertEquals(5,low.getScore());
	}
	@Test
	void testsetScore() {
		low.addPoints(0);
		assertEquals(0,low.getScore());
	}
	@Test
	void testgetPoints() {
		assertEquals(0,low.getScore());
	}

}
