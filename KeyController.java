import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.*;

public class KeyController {

    ArrayList<String> keys = new ArrayList<String>();

    KeyController(Scene scene){

        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event){

				if(event.getCode() == event.getCode().UP) {
                    if(!keys.contains("UP"))
                        keys.add("UP");
				} else if(event.getCode() == event.getCode().DOWN) {
                    if(!keys.contains("DOWN"))
                        keys.add("DOWN");
				} else if(event.getCode() == event.getCode().LEFT) {
                    if(!keys.contains("LEFT"))
                        keys.add("LEFT");
				} else if(event.getCode() == event.getCode().RIGHT) {
                    if(!keys.contains("RIGHT"))
                        keys.add("RIGHT");
				} else if(event.getCode() == event.getCode().DIGIT1) {
                    if(!keys.contains("1"))
                        keys.add("1");
				} else if(event.getCode() == event.getCode().DIGIT2) {
                    if(!keys.contains("2"))
                        keys.add("2");
				} else if(event.getCode() == event.getCode().DIGIT3) {
                    if(!keys.contains("3"))
                        keys.add("3");
				} else if(event.getCode() == event.getCode().SPACE) {
                    if(!keys.contains("SPACE"))
                        keys.add("SPACE");
				}
			}
		});

        scene.setOnKeyReleased(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event){

				if(event.getCode() == event.getCode().UP) {
                    keys.remove(new String("UP"));
				} else if(event.getCode() == event.getCode().DOWN) {
                    keys.remove(new String("DOWN"));
				} else if(event.getCode() == event.getCode().LEFT) {
                    keys.remove(new String("LEFT"));
				} else if(event.getCode() == event.getCode().RIGHT) {
                    keys.remove(new String("RIGHT"));
				} else if(event.getCode() == event.getCode().DIGIT1) {
                    keys.remove(new String("1"));
				} else if(event.getCode() == event.getCode().DIGIT2) {
                    keys.remove(new String("2"));
				} else if(event.getCode() == event.getCode().DIGIT3) {
                    keys.remove(new String("3"));
				} else if(event.getCode() == event.getCode().SPACE) {
                    keys.remove(new String("SPACE"));
				}

			}
		});
    }

    /**
     * gets key values being pressed and adds them to Arraylist of key strings
     * @return returns ArrayList of key presses
     */
    public ArrayList<String> getKeys(){
        return this.keys;
    }
}
