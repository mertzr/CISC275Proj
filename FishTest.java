import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FishTest {
	Fish f = new Fish(5, 5, "fish", "src/assets/fish/bass.gif", 5, 2);
	@Test
	void testgetWidth() {
		assertEquals(5, f.getWidth());
	}
	@Test
	void testgetHeight() {
		assertEquals(3, f.getHeight());
	}
}
