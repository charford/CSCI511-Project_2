/**
 *  class for sidewalk elements
 *  will be used to draw the object, in version2
*/
class ElementSidewalk extends SiteElement {
   /**
    * method for cloneMe
    * @param loc_x  get x coordinate to place the sidewalk
    * @param loc_y  get y coordinate to place the sidewalk
    * @param color  get color to draw tree
   */
  public static void cloneMe(int loc_x, int loc_y, String color) {
    System.out.println("ElementSidewalk created sidewalk at location " + loc_x + ", " + loc_y + " , and painted it " + color); 
  }
};
