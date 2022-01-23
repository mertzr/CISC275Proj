import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ItemTest {
	Item s = new Item(5, 5, "can", "src/assets/game_1/items/recycle-2.png");
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
		assertEquals("net",s.getName());
	}
	@Test
	void testgetImage() {
		assertEquals("src/assets/game_1/items/recycle-2.png",s.getImage());
	}
}
