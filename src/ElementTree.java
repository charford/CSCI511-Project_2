/**
 *  class for tree elements
 *  will be used to draw the object, in version2
*/
class ElementTree extends SiteElement {
   /**
    * method for cloneMe
    * @param loc_x  get x coordinate to place the tree
    * @param loc_y  get y coordinate to place the tree
    * @param color  get color to draw tree
   */
  public static void cloneMe(int loc_x, int loc_y, String color) {
    System.out.println("ElementTree created tree at location " + loc_x + ", " + loc_y + " , and painted it " + color); 
  }
};
