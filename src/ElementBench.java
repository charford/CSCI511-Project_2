/**
 *  class for bench elements
 *  will be used to draw the object, in version2
*/
class ElementBench extends SiteElement {

   /**
    * method for cloneMe
    * @param loc_x  get x coordinate to place the bench
    * @param loc_y  get y coordinate to place the bench
    * @param color  get color to draw bench
   */
  public static void cloneMe(int loc_x,int loc_y,String color) {
    System.out.println("ElementBench created bench at location " + loc_x + ", " + loc_y + " , and painted it " + color); 
  }
};
