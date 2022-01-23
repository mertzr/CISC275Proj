import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HSTest {
	HS TopPlayer = new HS("TRAVGAWD",10000);
	@Test
	void testToString() {
		assertEquals("TRAVGAWD, 10000", "TRAVGAWD, 10000");
	}

}
