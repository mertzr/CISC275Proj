import java.util.List;
import java.util.ArrayList;

public class Bin{
   String name;
   String color;
   int items;
   //test

   /**
    * constructor of bin with a name, color, and number of items
    * @param name String name of bin
    * @param color String color of bin
    * @param items int number of items
    */
   public Bin(String name, String color, int items){
      this.name = name;
      this.color = color;
      this.items = items;
   }

   /**
    * Sets name of bin
    * @param name String name of bin
    * @return void
    */
   public void setName(String name){
      this.name = name;
   }

   /**
    * Sets color of bin
    * @param color
    * @return void
    */
   public void setColor(String color){
      this.color = color;
   }

   /**
    * Sets number of items in the bin
    * @param items
    * @return void
    */
   public void setItems(int items){
      this.items = items;
   }

   /**
   * Bin name getter
   * @return string of name of bin
   */
   public String getName(){
      return name;
   }

   /**
   * Bin color getter
   * @return string of color of bin
   */
   public String getColor(){
      return color;
   }

   /**
   * Bin num of items getter
   * @return int number of items
   */
   public int getItems(){
      return items;
   }

   /**
   * Determines if garbage is trash or plastic
   * @param name String name of garbage
   * @return string of plastic or trash
   */
   public String getType(String name){
      if(name.equals("Water Bottle")){
         return("Plastic");
      }
      else{
         return("Trash");
      }
   }

   /**
   * Adds item to itemlist
   * @param item String of item being added to ArrayList itemlist
   * @return void
   */
   public void addItem(String item){
      ArrayList<String> itemList = new ArrayList<String>();
      itemList.add(item);
   }
}
