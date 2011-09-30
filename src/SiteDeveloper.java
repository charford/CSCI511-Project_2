import java.util.Scanner;
/**
 *  @author Casey Harford
 *  @version 1.0
 *  Main method
 *  Purpose: Reads user input, returns error if invalid format given
 *  If valid command, makes call to SiteElement method for corresponding 
 *  object that it needs to create.
*/
public class SiteDeveloper {
  public static void main ( String [] args ) {
    /**
     *  Print title and welcome message
    */
    System.out.println("-------------------------------------");
    System.out.println("Site Developer Application - Welcome!");
    System.out.println("-------------------------------------");

    getInput();
    
  }

  /**
   *  method for getInput
   *  used to get user input, performs loop till q command is pressed
  */
  private static void getInput() {


    /**
     *  Instantiate a Scanner object, we'll use this to read input
    */
    Scanner sc = new Scanner(System.in);

    SiteElement se = new SiteElement();

    /**
     *  call to displayMenu, then enter into while loop to accept
     *  user input. Infinte loop, till the user types 'q'
    */
    displayMenu();

    /**
     *  define the global variables, which will be used to store user input 
    */
    String user_input,create,location,color;
    int loc_x,loc_y;


    /**
     *  input loop
    */
    while(sc.hasNext()) {

      /**
       *  retrieve input, store it in user_input
      */
      user_input = sc.next();

      /**
       *  Check if user_input is valid
      */
      if(user_input.equalsIgnoreCase("create")) {
          
         /**
          *  create command
         */
         if(sc.hasNext()) {

           /**
            *  what are we creating?
           */
           create = sc.next();
           if(sc.hasNext()) {

             /**
              *  where will it be located?
             */
             location = sc.next();

             if(location.equalsIgnoreCase("loc")) {
               if(sc.hasNextInt()) {
                 /**
                  *  get x axis
                 */
                 loc_x = sc.nextInt();
                 if(sc.hasNextInt()) {
                  /**
                    *  get y axis
                   */
                   loc_y = sc.nextInt();

                   if(sc.hasNext()) {
                     /**
                       *  time to find out what color to make it
                      */
                      color = sc.next();
                      if(color.equalsIgnoreCase("color")) {
                        if(sc.hasNext()) {
                          color = sc.next();
                          /**
                            * now that we have a valid command, with all required parameters, lets create it
                           */
                          se.createObject(create,loc_x,loc_y,color);
                        }
                        
                      }
                      else {
                        System.out.println("Error, invalid command(expecting color)");
                      }
                   }
                 }
                 else {
                   System.out.println("Error, invalid location (Y) given");
                 }
               }
               else {
                 System.out.println("Error, invalid location (X) given");
               }
             }
             else {
               System.out.println("Error, invalid argument for loc");
              //error goes here TODO
             }
           }
         }
         else {
           System.out.println("Error, invalid argument for create");
         }
      }

      /**
        * quit program
       */
      if(user_input.equalsIgnoreCase("q")) {
        System.out.println("Quiting SiteDeveloper. Have a nice day!");
        break;
      }
      /**
       *
      */
      if(user_input.equalsIgnoreCase("p")) {
        se.outputBuildSpace();
      }
    }
  }
  
  /**
   *  method for displayMenu. This method can be called to display a menu for the user to choose from(commands)
  */
  private static void displayMenu() {
    System.out.println("Enter a command to continue.\n");
    System.out.println("Example: create tree loc 3 4  color green\n");
    System.out.println("Available objects: Tree, Building, Bench, Sidewalk, House, and Road\n");
    System.out.println("Available colors: \n");
    System.out.println("To quit, press q.");
  }
};
