/**
 *  class for road elements
 *  will be used to draw the object, in version2
*/
class ElementRoad extends SiteElement {
   /**
    * method for cloneMe
    * @param loc_x  get x coordinate to place the road
    * @param loc_y  get y coordinate to place the road
    * @param color  get color to draw road
   */
  public static void cloneMe(int loc_x,int loc_y,String color) {
    System.out.println("ElementRoad created road at location " + loc_x + ", " + loc_y + " , and painted it " + color); 
  }
};
