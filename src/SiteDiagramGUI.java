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

class SiteDiagramGUI extends JPanel implements ActionListener {
  public static JFrame f;
  private static String create;
  private JButton treeButton = new JButton("Tree");
  private JButton sidewalkButton = new JButton("Sidewalk");
  private JButton buildingButton = new JButton("Building");
  private JButton houseButton = new JButton("House");
  private JButton roadButton = new JButton("Road");
  private JButton benchButton = new JButton("Bench");
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
    treeButton.addActionListener(this);
    sidewalkButton.addActionListener(this);
    buildingButton.addActionListener(this);
    houseButton.addActionListener(this);
    roadButton.addActionListener(this);
    benchButton.addActionListener(this);
    
    
    
    /************************************************
    /*addWindowListener(
      new WindowAdapter() { 
        public void windowClosing( WindowEvent e ) {
        }  
      }
    );**********************************************
    */
    
    addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        System.out.println("mouse pressed");
      }
      public void mouseClicked(MouseEvent e) {
        
        //System.out.println("mouse pressed " + e.getX() + ", " + e.getY());
        int x = e.getX() / cellSize;
        int y = e.getY() / cellSize;
        System.out.println("mouse clicked " + x + ", " + y + "object to create = " + create);
        System.out.println("create = " + create);
        buildObject.createObject(create,x,y,"blue");
        
        
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
