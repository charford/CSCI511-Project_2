/**
 *  class for house elements
 *  will be used to draw the object, in version2
*/
class ElementHouse extends SiteElement {
  /**
   *  method for cloneMe
   *  @param loc_x    gets x coordinate to place house
   *  @param loc_y    gets y coordinate to place house
   *  @param color    gets color to make house
  */
  public static void cloneMe(int loc_x,int loc_y,String color) {
    System.out.println("ElementHouse created house at location " + loc_x + ", " + loc_y + " , and painted it " + color); 
  }
};
