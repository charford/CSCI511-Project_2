import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JColorChooser;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *  class for SiteDiagramGUI
 *  @author Casey Harford
 *  Last update 10-17-2011
 *
*/
class SiteDiagramGUI extends JPanel implements ActionListener {

  /** main frame to be used for user gui */
  public static JFrame f;
  
  /** button to make tree objects */
  private JButton treeButton = new JButton("Tree");

  /** button to make water objects */
  private JButton waterButton = new JButton("Water");

  /** button to make building objects */
  private JButton buildingButton = new JButton("Building");

  /** button to make house objects */
  private JButton houseButton = new JButton("House");

  /** button to make road objects */
  private JButton roadButton = new JButton("Road");

  /** button to make grass objects */
  private JButton grassButton = new JButton("Grass");

  /** button to clear build space */
  private JButton clearButton = new JButton("Clear");

  /** button to change current color */
  private JButton colorButton = new JButton("Color");

  /** button to change current size to small */
  private JButton smallButton = new JButton("S");

  /** button to change current size to medium */
  private JButton medButton = new JButton("M");

  /** button to change current size to large */
  private JButton largeButton = new JButton("L");

  /** button to undo last object built */
  private JButton undoButton = new JButton("Undo");

  /** dimensions for build space */
  private static int fWidth,fHeight,cellSize;
  
  /** current size to build with */
  private static int curSize;

  /** object to store build space */
  private static SiteElement buildObject;

  /** current color to build with */
  private static Color curColor;

  /** current object selected to build */
  private static String create;

  /** list of current objects already on map */
  private static ArrayList<SiteElement.alreadyBuilt> builtObjects;
  
  /**
   *  constructor for SiteDiagramGUI
   *  sets the current color(default) to white, 
   *  then instantiates the buttons, and adds listeners to them
   *  mouse listeners are also setup in this method.
  */
  public SiteDiagramGUI() {

    curColor = Color.white;
    add(treeButton);
    add(waterButton);
    add(buildingButton);
    add(houseButton);
    add(roadButton);
    add(grassButton);
    add(clearButton);
    add(colorButton);
    add(smallButton);
    add(medButton);
    add(largeButton);
    add(undoButton);
    treeButton.addActionListener(this);
    waterButton.addActionListener(this);
    buildingButton.addActionListener(this);
    houseButton.addActionListener(this);
    roadButton.addActionListener(this);
    grassButton.addActionListener(this);
    clearButton.addActionListener(this);
    colorButton.addActionListener(this);
    smallButton.addActionListener(this);
    medButton.addActionListener(this);
    largeButton.addActionListener(this);
    undoButton.addActionListener(this);
    
    /** creating a new mouseAdapter to handle the mouse event of clicking */
    addMouseListener(new MouseAdapter() {

      /** 
       *  method for mouseClicked events 
       *  @param e  the mouse event being clicked
      */
      public void mouseClicked(MouseEvent e) {
        
        /** get the coordinates that were clicked, then convert it to the actual cell that the user clicked */
        int x = e.getX() / cellSize;
        int y = e.getY() / cellSize;

        /** build object requested, if successful, repaint */
        if(buildObject.createObject(create,x,y,curColor,curSize)) {
          repaint();
        }
        /** error, invalid object specified, no painting occured */
        else System.out.println("error paint");
      }
    });
  }

  /**
   *  method to handle button actions
   *  @param e  the event being performed
  */
  public void actionPerformed(ActionEvent e) {
    
    /** get the source button that was clicked */
    Object source = e.getSource();
  
    /** response to build object buttons */
    if(source == treeButton) create = "tree";
    else if(source == waterButton) create = "water";
    else if(source == buildingButton) create = "building";
    else if(source == houseButton) create = "house";
    else if(source == roadButton) create = "road";
    else if(source == grassButton) create = "grass"; 

    /** response to clear button */
    else if(source == clearButton) {
      buildObject.clear();
      builtObjects = buildObject.getList();
      repaint();
    }
    
    /** reponse to color selector button */
    if(source == colorButton) {
      curColor = JColorChooser.showDialog( SiteDiagramGUI.this, "Choose a color", curColor);
      repaint();
    }
    
    /** response to size buttons */
    if(source == smallButton) curSize = 1;
    if(source == medButton) curSize = 2;
    if(source == largeButton) curSize = 3;

    if(source == undoButton) { 
      buildObject.undo(); 
      repaint();
    }
  }
  
  /**
   *  paint method used for SiteDiagramGUI
   *  @param g Graphics object to paint with
  */
  public void paintComponent(Graphics g) {
    
    /** size of object that is being painted */
    int objectSize;

    /** cell size of build space */
    int y=cellSize;

    /** cell size of build space */
    int x=cellSize;

    Graphics2D g2 = (Graphics2D) g;
    builtObjects = buildObject.getList();
    Iterator<SiteElement.alreadyBuilt> itr = builtObjects.iterator();
    
    g2.setPaint(Color.white);
    g2.fill(new Rectangle2D.Double(0,0,1000,1000)); 

    /** draw horizontal graph lines */
    while(y<=fHeight*cellSize) {
      g.setColor(Color.gray);
      g.drawLine(0,y,fWidth*cellSize,y);
      y += cellSize;
    }

    /** draw verticle graph lines */
    while(x<=fWidth*cellSize) {
      g.drawLine(x,0,x,fHeight*cellSize);
      x += cellSize;
    }

    /** paint objects in ArrayList builtObject */
    while(itr.hasNext()) {
      
      /** get object, and info about it */
      SiteElement.alreadyBuilt object = itr.next();
      objectSize = object.getSize();
      objectSize = objectSize*cellSize;
      x = object.getX();
      y = object.getY();

      /** if the object is valid, paint it */
      if(object.getType().equalsIgnoreCase("road")) {
        g2.setPaint(Color.gray);
        g2.fill(new Rectangle2D.Double(x*cellSize, y*cellSize, cellSize, cellSize));
      }
      else if(object.getType().equalsIgnoreCase("tree")) {
        
        /** this color can't be changed by user, tree trunks will always be brown */
        Color brown = new Color(156,93,82);
        
        /** a tree trunk */
        g2.setPaint(brown);
        g2.fill(new Ellipse2D.Double((x*cellSize)+(objectSize/4), (y*cellSize)+(objectSize/4), objectSize/2, objectSize/2));
        
        /** some leaves for the tree */

        /** leaves will always be green */
        g2.setPaint(Color.green);

        /** draw the leaves */
        g2.fill(new Ellipse2D.Double(x*cellSize, y*cellSize, objectSize/2, objectSize/2));
        g2.fill(new Ellipse2D.Double((x*cellSize)+(objectSize/2), y*cellSize, objectSize/2, objectSize/2));
        g2.fill(new Ellipse2D.Double((x*cellSize)+(objectSize/2), (y*cellSize)+(objectSize/2), objectSize/2, objectSize/2));
        g2.fill(new Ellipse2D.Double((x*cellSize), (y*cellSize)+(objectSize/2), objectSize/2, objectSize/2));
      }
      else if(object.getType().equalsIgnoreCase("building")) {
        
        /** get color of building */
        g2.setPaint(object.getColor());

        /** draw the building */
        g2.fill(new Rectangle2D.Double(x*cellSize, y*cellSize, objectSize*3, objectSize*2));
    
        /** this last part is the border for the building, and the small square thing on top */
        g.setColor(Color.black);
        g.drawRect(x*cellSize,y*cellSize,objectSize*3,objectSize*2);
        g.drawRect((x*cellSize)+10,(y*cellSize)+10,(objectSize*3)-20,(objectSize*2)-20);
      }
      else if(object.getType().equalsIgnoreCase("house")) {
        
        /** get color of house */
        g2.setPaint(object.getColor());

        /** draw the house */
        g2.fill(new Rectangle2D.Double(x*cellSize, y*cellSize, objectSize*2, objectSize*2));
        g.setColor(Color.black);
        
        /** chimney */
        g.drawRect((x*cellSize)+(objectSize/4),(y*cellSize)+(objectSize/4),(objectSize/4)*3,(objectSize/4)*3);
      }
      else if(object.getType().equalsIgnoreCase("water")) {
        
        /** water will always be blue */
        g2.setPaint(Color.blue);

        /** draw water */
        g2.fill(new Rectangle2D.Double(x*cellSize, y*cellSize, objectSize, objectSize));
      }
      else if(object.getType().equalsIgnoreCase("grass")) {
        
        /** grass always this dark green color */
        g2.setPaint(new Color(0,153,0));
        g2.fill(new Rectangle2D.Double(x*cellSize, y*cellSize, objectSize, objectSize));
      }
    }
  } 
  
  /**
   *  method for main program operation
   *  @param args   not used in this implementation
  */
  public static void main (String [] args) {
    /** set default values for object and size */
    create = "undefined";
    curSize = 1;

    /** set dimensions for site diagram */
    fWidth = 24;
    fHeight = 18;
    cellSize = 35;

    /** create the build object */
    buildObject = new SiteElement(fWidth,fHeight);

    /** get inital values for builtObjects */
    builtObjects = buildObject.getList();
  
    /** create the frame to draw stuff */
    f = new JFrame("SiteDiagramGUI");
    f.add(new SiteDiagramGUI());

    /** when user presses the x to close application, exit program without errors */
    WindowListener listener = new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);  
      }
    };
    f.addWindowListener(listener);

    /** set dimensions of window, then make visible */
    f.setSize(fWidth*cellSize,(fHeight*cellSize));
    f.setVisible(true);
  }
};
