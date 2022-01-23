import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TimerTest {
	Timer t = new Timer();
	@Test
	void testStart() {
		t.start();
		assertEquals(45,t.getTime());
	}
	@Test
	void testgetGameTime() {
		assertEquals(45,t.getGameTime());
	}
}
