import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JColorChooser;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

class SiteDiagramGUI extends JPanel implements ActionListener {
  public static JFrame f;
  private static String create;
  private JButton treeButton = new JButton("Tree");
  private JButton sidewalkButton = new JButton("Sidewalk");
  private JButton buildingButton = new JButton("Building");
  private JButton houseButton = new JButton("House");
  private JButton roadButton = new JButton("Road");
  private JButton benchButton = new JButton("Bench");
  private JButton clearButton = new JButton("Clear");
  private JButton colorButton = new JButton("Color");
  private static int fWidth,fHeight,cellSize;
  private static SiteElement buildObject;
  private static BufferedImage image;
  private Color curColor;
  private int[][] buildTracker;   //track the objects painted, and will be used to repaint objects
  private static ArrayList<SiteElement.alreadyBuilt> builtObjects;
  
  public SiteDiagramGUI() {
    buildTracker = new int[7][4];
    curColor = Color.white;
    add(treeButton);
    add(sidewalkButton);
    add(buildingButton);
    add(houseButton);
    add(roadButton);
    add(benchButton);
    add(clearButton);
    add(colorButton);
    treeButton.addActionListener(this);
    sidewalkButton.addActionListener(this);
    buildingButton.addActionListener(this);
    houseButton.addActionListener(this);
    roadButton.addActionListener(this);
    benchButton.addActionListener(this);
    clearButton.addActionListener(this);
    colorButton.addActionListener(this);
    
    addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        
        //System.out.println("mouse pressed " + e.getX() + ", " + e.getY());
        int x = e.getX() / cellSize;
        int y = e.getY() / cellSize;
        System.out.println("mouse clicked " + x + ", " + y + "object to create = " + create);
        System.out.println("create = " + create);
        if(buildObject.createObject(create,x,y,curColor)) {
          Graphics g = getGraphics();
          Graphics2D g2 = (Graphics2D) g;
          if(create.equalsIgnoreCase("road")) {
            repaint();
          }
          else if(create.equalsIgnoreCase("tree")) {
            repaint();
          }
          else if(create.equalsIgnoreCase("building")) {
            repaint();
          }
          else if(create.equalsIgnoreCase("house")) {
            repaint();
          }
          else if(create.equalsIgnoreCase("sidewalk")) {
            repaint();
          }
          else if(create.equalsIgnoreCase("bench")) {
            repaint();
          }
        }
        else System.out.println("error paint");
        builtObjects = buildObject.getList();
        System.out.println("Size of builtObjects = " + builtObjects.size());
      }
    });
  }

  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    if(source == treeButton) {
      System.out.println("tree button pressed");
      create = "tree";
    }
    else if(source == sidewalkButton) {
      System.out.println("sidewalk button pressed");
      create = "sidewalk";
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
    if(source == benchButton) {
      System.out.println("bench button pressed");
      create = "bench";
    }
    if(source == clearButton) {
      System.out.println("clear button pressed");
      buildObject = new SiteElement();
      repaint();
    }
    if(source == colorButton) {
      curColor = JColorChooser.showDialog( SiteDiagramGUI.this, "Choose a color", curColor);
      System.out.println("Changed color to: " + curColor);
      repaint();
    }
  }

  public void paintComponent(Graphics g) {
    System.out.println("start printComponent");
    int y=cellSize;
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
    System.out.println("end printComponent");

    //paint objects in ArrayList builtObject;
    Graphics2D g2 = (Graphics2D) g;
    builtObjects = buildObject.getList();
    Iterator<SiteElement.alreadyBuilt> itr = builtObjects.iterator();
    while(itr.hasNext()) {
      SiteElement.alreadyBuilt object = itr.next();
      x = object.getX();
      y = object.getY();
      if(object.getType().equalsIgnoreCase("road")) {
        g2.setPaint(Color.gray);
        g2.fill(new Rectangle2D.Double(x*cellSize, y*cellSize, cellSize, cellSize));
        System.out.println("paint road");
      }
      else if(object.getType().equalsIgnoreCase("tree")) {
        Color brown = new Color(156,93,82);
        System.out.println(curColor);
        g2.setPaint(brown);
        g2.fill(new Ellipse2D.Double((x*cellSize)+(cellSize/4), (y*cellSize)+(cellSize/4), cellSize/2, cellSize/2));
        //draw the leaves
        g2.setPaint(Color.green);
        g2.fill(new Ellipse2D.Double(x*cellSize, y*cellSize, cellSize/2, cellSize/2));
        g2.fill(new Ellipse2D.Double((x*cellSize)+(cellSize/2), y*cellSize, cellSize/2, cellSize/2));
        g2.fill(new Ellipse2D.Double((x*cellSize)+(cellSize/2), (y*cellSize)+(cellSize/2), cellSize/2, cellSize/2));
        g2.fill(new Ellipse2D.Double((x*cellSize), (y*cellSize)+(cellSize/2), cellSize/2, cellSize/2));
      }
      else if(object.getType().equalsIgnoreCase("building")) {
        g2.setPaint(object.getColor());
        g2.fill(new Rectangle2D.Double(x*cellSize, y*cellSize, cellSize*3, cellSize*2));
      }
      else if(object.getType().equalsIgnoreCase("house")) {
        g2.setPaint(object.getColor());
        g2.fill(new Rectangle2D.Double(x*cellSize, y*cellSize, cellSize*2, cellSize*2));
        g2.setPaint(Color.black);
        //g2.fill(new Line2D.Double(x*cellSize,y*cellSize, 100,100));
      }
      else if(object.getType().equalsIgnoreCase("sidewalk")) {
      
      }
      else if(object.getType().equalsIgnoreCase("bench")) {

      }
    }
  } 

  public static void main (String [] args) {
    create = "undefined";
    fWidth = 18;
    fHeight = 18;
    buildObject = new SiteElement();
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
