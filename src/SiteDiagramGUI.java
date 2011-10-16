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
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;

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
  private static int fWidth,fHeight,cellSize;
  private static SiteElement buildObject;
  private static BufferedImage image;
  
  
  
  public SiteDiagramGUI() {
    add(treeButton);
    add(sidewalkButton);
    add(buildingButton);
    add(houseButton);
    add(roadButton);
    add(benchButton);
    add(clearButton);
    treeButton.addActionListener(this);
    sidewalkButton.addActionListener(this);
    buildingButton.addActionListener(this);
    houseButton.addActionListener(this);
    roadButton.addActionListener(this);
    benchButton.addActionListener(this);
    clearButton.addActionListener(this);
    
    
    
    /************************************************
    /*addWindowListener(
      new WindowAdapter() { 
        public void windowClosing( WindowEvent e ) {
        }  
      }
    );**********************************************
    */
    
    addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        
        //System.out.println("mouse pressed " + e.getX() + ", " + e.getY());
        int x = e.getX() / cellSize;
        int y = e.getY() / cellSize;
        System.out.println("mouse clicked " + x + ", " + y + "object to create = " + create);
        System.out.println("create = " + create);
        if(buildObject.createObject(create,x,y,"blue")) {
          Graphics g = getGraphics();
          Graphics2D g2 = (Graphics2D) g;
          if(create.equalsIgnoreCase("road")) {
            g2.setPaint(Color.gray);
            g2.fill(new Rectangle2D.Double(x*cellSize, y*cellSize, cellSize, cellSize));
            //g2.fill(new Rectangle2D(x*cellSize,y*cellSize,cellSize,cellSize));
            //g.drawOval(x*cellSize,y*cellSize,cellSize,cellSize);
            //g.fill(g.drawRect(x*cellSize,y*cellSize,cellSize,cellSize));
            System.out.println("paint road");
          }
          else if(create.equalsIgnoreCase("tree")) {
            Color brown = new Color(156,93,82);
            //draw stump
            g2.setPaint(brown);
            g2.fill(new Ellipse2D.Double((x*cellSize)+(cellSize/4), (y*cellSize)+(cellSize/4), cellSize/2, cellSize/2));
            //draw the leaves
            g2.setPaint(Color.green);
            g2.fill(new Ellipse2D.Double(x*cellSize, y*cellSize, cellSize/2, cellSize/2));
            g2.fill(new Ellipse2D.Double((x*cellSize)+(cellSize/2), y*cellSize, cellSize/2, cellSize/2));
            g2.fill(new Ellipse2D.Double((x*cellSize)+(cellSize/2), (y*cellSize)+(cellSize/2), cellSize/2, cellSize/2));
            g2.fill(new Ellipse2D.Double((x*cellSize), (y*cellSize)+(cellSize/2), cellSize/2, cellSize/2));
          }
          else if(create.equalsIgnoreCase("building")) {
            g2.setPaint(Color.red);
            g2.fill(new Rectangle2D.Double(x*cellSize, y*cellSize, cellSize*3, cellSize*3));
          }
          else if(create.equalsIgnoreCase("house")) {
            g2.setPaint(Color.orange);
            g2.fill(new Rectangle2D.Double(x*cellSize, y*cellSize, cellSize*2, cellSize*2));
            
            
          }
          //g.setColor(Color.black);
          //g.drawOval(x*cellSize,y*cellSize,100,100);
        }
        else System.out.println("error paint");
      }
    });
  }

  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    if(source == treeButton) {
      System.out.println("tree button pressed");
      create = "tree";
      System.out.println("create = " + create);
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
      Graphics g = f.getGraphics();
      g.drawLine(0,0,10,10);
      g.dispose();
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
  } 

  public static void main (String [] args) {
    create = "undefined";
    fWidth = 18;
    fHeight = 18;
    buildObject = new SiteElement();
    cellSize = 35;
    System.out.println("SiteDiagramGUI");
    f = new JFrame("SiteDiagramGUI");
    f.add(new SiteDiagramGUI());
    f.setSize(fWidth*cellSize,(fHeight*cellSize));
    f.setVisible(true);
    
  }
 
};
