/**
 *  SiteDiagramGUI
 *  @author Casey Harford
 *  Last update 10-16-2011
 * 
*/
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
 *  @param f              main frame which is used to display everything
 *  @param create         variable used to store the current object type that is selected
 *  @param treeButton     the button used to build tree objects
 *  @param waterButton    the button used to build water objects
 *  @param buildingButton the button used to build building objects
 *  @param houseButton    the button used to build house objects
 *  @param roadButton     the button used to build road objects
 *  @param grassButton    the button used to build grass objects
 *  @param clearButton    the button used to clear the build space
 *  @param colorButton    the button used to select color to build with
 *  @param smallButton    the button used to build small objects
 *  @param medButton      the button used to build medium objects
 *  @param largeButton    the button used to build large objects
 *  @param fWidth         number of cells for width of build space
 *  @param fHeight        number of cells for height of build space
 *  @param cellSize       size to make each cell on build space
 *  @param buildObject    contains tracking of build space, where objects are, which ones have been made, etc.
 *  @param curColor       current color that is selected to build with
 *  @param builtObjects   used to store the list of objects built, and used when repainting
 *  @param curSize        current size to build objects
 *
*/
class SiteDiagramGUI extends JPanel implements ActionListener {

  public static JFrame f;
  private static String create;
  private JButton treeButton = new JButton("Tree");
  private JButton waterButton = new JButton("Water");
  private JButton buildingButton = new JButton("Building");
  private JButton houseButton = new JButton("House");
  private JButton roadButton = new JButton("Road");
  private JButton grassButton = new JButton("Grass");
  private JButton clearButton = new JButton("Clear");
  private JButton colorButton = new JButton("Color");
  private JButton smallButton = new JButton("S");
  private JButton medButton = new JButton("M");
  private JButton largeButton = new JButton("L");
  private static int fWidth,fHeight,cellSize;
  private static SiteElement buildObject;
  private Color curColor;
  private static ArrayList<SiteElement.alreadyBuilt> builtObjects;
  private static int curSize;
  
  /**
   *  constructor for SiteDiagramGUI
   *  sets the current color(default) to white, 
   *  then instantiates the buttons, and adds listeners to them
   *  mouse listeners are also setup in this method.
   *  
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
    
    /** creating a new mouseAdapter to handle the mouse event of clicking */
    addMouseListener(new MouseAdapter() {

      /** 
       *  method for mouseClicked events 
       *  @param x  coordinate on the x axis that was clicked
       *  @param y  coordinate on the y axis that was clicked
      */
      public void mouseClicked(MouseEvent e) {
        
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

  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    if(source == treeButton) {
      System.out.println("tree button pressed");
      create = "tree";
    }
    else if(source == waterButton) {
      System.out.println("water button pressed");
      create = "water";
    }
    else if(source == buildingButton) {
      System.out.println("building button pressed");
      create = "building";
    }
    if(source == houseButton) {
      System.out.println("house button pressed");
      create = "house";
    }
    if(source == roadButton) {
      System.out.println("road button pressed");
      create = "road";
    }
    if(source == grassButton) {
      System.out.println("grass button pressed");
      create = "grass"; 
    }
    if(source == clearButton) {
      System.out.println("clear button pressed");
      buildObject.clear();
      builtObjects = buildObject.getList();
      repaint();
    }
    if(source == colorButton) {
      curColor = JColorChooser.showDialog( SiteDiagramGUI.this, "Choose a color", curColor);
      System.out.println("Changed color to: " + curColor);
      repaint();
    }
    if(source == smallButton) {
      System.out.println("Small button clicked");
      curSize = 1;
    }
    if(source == medButton) {
      System.out.println("Med button clicked");
      curSize = 2;
    }
    if(source == largeButton) {
      System.out.println("Large button clicked");
      curSize = 3;
    }
  }

  public void paintComponent(Graphics g) {
    int y=cellSize;
    int objectSize;
    while(y<=fHeight*cellSize) {
      g.setColor(Color.gray);
      g.drawLine(0,y,fWidth*cellSize,y);
      y += cellSize;
    }
    int x=cellSize;
    while(x<=fWidth*cellSize) {
      g.drawLine(x,0,x,fHeight*cellSize);
      x += cellSize;
    }

    //paint objects in ArrayList builtObject;
    Graphics2D g2 = (Graphics2D) g;
    builtObjects = buildObject.getList();
    Iterator<SiteElement.alreadyBuilt> itr = builtObjects.iterator();
    while(itr.hasNext()) {
      SiteElement.alreadyBuilt object = itr.next();
      objectSize = object.getSize();
      objectSize = objectSize*cellSize;
      x = object.getX();
      y = object.getY();
      if(object.getType().equalsIgnoreCase("road")) {
        g2.setPaint(Color.gray);
        g2.fill(new Rectangle2D.Double(x*cellSize, y*cellSize, cellSize, cellSize));
        System.out.println("paint road");
      }
      else if(object.getType().equalsIgnoreCase("tree")) {
        Color brown = new Color(156,93,82);
        g2.setPaint(brown);
        g2.fill(new Ellipse2D.Double((x*cellSize)+(objectSize/4), (y*cellSize)+(objectSize/4), objectSize/2, objectSize/2));
        //draw the leaves
        g2.setPaint(Color.green);
        g2.fill(new Ellipse2D.Double(x*cellSize, y*cellSize, objectSize/2, objectSize/2));
        g2.fill(new Ellipse2D.Double((x*cellSize)+(objectSize/2), y*cellSize, objectSize/2, objectSize/2));
        g2.fill(new Ellipse2D.Double((x*cellSize)+(objectSize/2), (y*cellSize)+(objectSize/2), objectSize/2, objectSize/2));
        g2.fill(new Ellipse2D.Double((x*cellSize), (y*cellSize)+(objectSize/2), objectSize/2, objectSize/2));
      }
      else if(object.getType().equalsIgnoreCase("building")) {
        g2.setPaint(object.getColor());
        g2.fill(new Rectangle2D.Double(x*cellSize, y*cellSize, objectSize*3, objectSize*2));
        g.setColor(Color.black);
        g.drawRect(x*cellSize,y*cellSize,objectSize*3,objectSize*2);
        g.drawRect((x*cellSize)+10,(y*cellSize)+10,(objectSize*3)-20,(objectSize*2)-20);
      }
      else if(object.getType().equalsIgnoreCase("house")) {
        g2.setPaint(object.getColor());
        g2.fill(new Rectangle2D.Double(x*cellSize, y*cellSize, objectSize*2, objectSize*2));
        g.setColor(Color.black);
        //g.drawRect(x*cellSize,y*cellSize,objectSize*3,objectSize*2);
        //chimney
        g.drawRect((x*cellSize)+(objectSize/4),(y*cellSize)+(objectSize/4),(objectSize/4)*3,(objectSize/4)*3);
      }
      else if(object.getType().equalsIgnoreCase("water")) {
        g2.setPaint(Color.blue);
        g2.fill(new Rectangle2D.Double(x*cellSize, y*cellSize, objectSize, objectSize));
      }
      else if(object.getType().equalsIgnoreCase("grass")) {
        g2.setPaint(new Color(0,153,0));
        g2.fill(new Rectangle2D.Double(x*cellSize, y*cellSize, objectSize, objectSize));
      }
    }
  } 

  public static void main (String [] args) {
    create = "undefined";
    curSize = 1;
    fWidth = 24;
    fHeight = 18;
    buildObject = new SiteElement(fWidth,fHeight);
    builtObjects = buildObject.getList();
    cellSize = 35;
    System.out.println("SiteDiagramGUI");
    f = new JFrame("SiteDiagramGUI");
    f.add(new SiteDiagramGUI());
    WindowListener listener = new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);  
      }
    };
    f.addWindowListener(listener);
    f.setSize(fWidth*cellSize,(fHeight*cellSize));
    f.setVisible(true);
    
  }
};
