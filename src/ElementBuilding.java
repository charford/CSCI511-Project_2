/**
 *  class for building elements
 *  will be used to draw the object, in version2
*/
class ElementBuilding extends SiteElement {

   /**
    * method for cloneMe
    * @param loc_x  get x coordinate to place the building
    * @param loc_y  get y coordinate to place the building
    * @param color  get color to draw building
   */
  public static void cloneMe(int loc_x,int loc_y,String color) {
    System.out.println("ElementBuilding created building at location " + loc_x + ", " + loc_y + " , and painted it " + color); 
  }
};
