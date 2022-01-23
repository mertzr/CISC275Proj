import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BinTest {
	Bin b = new Bin("name", "color", 0);
	@Test
	void testsetName(){
		b.setName("garbage");
		assertEquals("garbage","garbage");
    }
	@Test
	void testsetColor(){
		b.setColor("red");
		assertEquals("red","red");
	}
	@Test
    void testsetItems(){
    	b.setItems(1);
    	assertEquals(1,1);
    }
	@Test
    void testgetName(){
    	b.getName();
    	assertEquals("garbage","garbage");
    }
	@Test
    void testgetColor(){
    	b.getColor();
    	assertEquals("red","red");
    }
	@Test
    void testgetItems(){
    	b.getItems();
    	assertEquals(1,1);
    }
}
