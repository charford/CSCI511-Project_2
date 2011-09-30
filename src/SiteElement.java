 /**
  * class for SiteElement
  * @author Casey Harford
  * @version 1.0
  * @param buildSpace   defines array for grid of objects
  * @param size_x       defines the size of x axis for buildSpace
  * @param size_y       defines the size of y axis for buildSpace
 */
public class SiteElement {
  int buildSpace[][];
  static int buildSizes[];
  static String buildObjects[];
  int size_x = 25;
  int size_y = 25;

  /**
   *  constructor for SiteElement
  */
  public SiteElement () {
    /**
     *  Creating the build space. Size is based on parameters given
    */
    buildSpace = new int[size_x][size_y];
    /**
     *  Creating of array of object strings, these will be used to convert the numbers to the corresponding 
     *  object when tracking the buildSpace
     */
    buildObjects = new String[7];
      buildObjects[0] = "Empty";
      buildObjects[1] = "Tree";
      buildObjects[2] = "Bench";
      buildObjects[3] = "Road";
      buildObjects[4] = "House";
      buildObjects[5] = "Building";
      buildObjects[6] = "Sidwalk";
    buildSizes = new int[7];
      buildSizes[0] = 0;
      buildSizes[1] = 1;
      buildSizes[2] = 1;
      buildSizes[3] = 2;
      buildSizes[4] = 2;
      buildSizes[5] = 4;
      buildSizes[6] = 1;
  }
  
   /**
    * method for createObject
    * @param type   gets the requested type of object to create
    * @param loc_x  gets the x coordinate to place object
    * @param loc_y  gets the y coordinate to place object
    * @param color  gets the color to be used for the object 
   */
  public void createObject(String type,int loc_x,int loc_y,String color) {
    if(type.equalsIgnoreCase("tree")) {
      if(trackObjects(1,loc_x,loc_y,color)) {
        ElementTree.cloneMe(loc_x,loc_y,color);
      }
    }
    else if(type.equalsIgnoreCase("bench")) {
      if(trackObjects(2,loc_x,loc_y,color)) {
        ElementBench.cloneMe(loc_x,loc_y,color);
      }
    }
    else if(type.equalsIgnoreCase("road")) {
      if(trackObjects(3,loc_x,loc_y,color)) {
        ElementRoad.cloneMe(loc_x,loc_y,color);
      }
    }
    else if(type.equalsIgnoreCase("house")) {
      if(trackObjects(4,loc_x,loc_y,color)) {
        ElementHouse.cloneMe(loc_x,loc_y,color);
      }
    }
    else if(type.equalsIgnoreCase("building")) {
      if(trackObjects(5,loc_x,loc_y,color)) {
        ElementBuilding.cloneMe(loc_x,loc_y,color);
      }
    }
    else if(type.equalsIgnoreCase("sidewalk")) {
      if(trackObjects(6,loc_x,loc_y,color)) {
        ElementSidewalk.cloneMe(loc_x,loc_y,color);
      }
    }
    else {
      System.out.println("Invalid object specified");
    }
  }
  
  /**
   *  method for outputBuildSpace, used to output a textual representation of buildspace
  */ 
  public void outputBuildSpace() {
    for(int i=0; i<=size_x+1; i++) {
      System.out.print("* ");
    }
    System.out.print("\n");
    for(int cord_y=0; cord_y<size_y; cord_y++) {
      System.out.print("* ");
      for(int cord_x=0; cord_x<size_x; cord_x++) {
         if(buildSpace[cord_x][cord_y]==0) {
           System.out.print("  ");
         }
         else {
           switch(buildSpace[cord_x][cord_y]) {
             case 1:
               System.out.print("T ");
             break;
             case 2:
               System.out.print("b ");
             break;
             case 3:
               System.out.print("R ");
             break;
             case 4:
               System.out.print("H ");
             break;
             case 5:
               System.out.print("B ");
             break;
             case 6:
               System.out.print("S ");
             break;
           }
         }
      }
      System.out.print("*\n");
    }
    for(int i=0; i<=size_x+1; i++) {
      System.out.print("* ");
    }
    System.out.print("\n");
  }
  /**
   *  method for trackObjects, used to track objects in the buildSpace
  */
  private boolean trackObjects(int type,int loc_x,int loc_y,String color) {
    int size = buildSizes[type];
    boolean spaceAvailable = true;
    if( ( (loc_x+size)>size_x ) | ( (loc_y+size)>size_y ) ) {
      System.out.println("Error, outside of buildSpace " + loc_x + ", " + loc_y);
      return false;
    }

    /** checking if space is available in buildSpace */
    for(int cord_y=loc_y; cord_y<size+loc_y; cord_y++) {
      for(int cord_x=loc_x; cord_x<size+loc_x; cord_x++) {
        if(buildSpace[cord_x][cord_y]==0) {
          //System.out.println("Setting buildSpace at " + cord_x + ", " + cord_y + " to " + "type: " + type);
        }
        else {
          spaceAvailable=false;
          System.out.println("Error, object already at location " + cord_x + ", " + cord_y);
        }
      }
    }
    /** if spaceAvailable then go ahead and build it */
    if(spaceAvailable) {
      for(int cord_y=loc_y; cord_y<size+loc_y; cord_y++) {
        for(int cord_x=loc_x; cord_x<size+loc_x; cord_x++) {
          buildSpace[cord_x][cord_y]=type;
          //System.out.println("buildSpace recorded for type " + type + " at location " + cord_x + ", " + cord_y);
        }
      }
    }  
    return spaceAvailable;
  }
};
