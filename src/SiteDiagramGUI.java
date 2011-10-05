import javax.swing.*;
import java.awt.*;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class SiteDiagramGUI extends JPanel implements ActionListener {
  String create;
  private JButton treeButton = new JButton("Tree");
  private JButton sidewalkButton = new JButton("Sidewalk");
  private JButton buildingButton = new JButton("Building");
  private JButton houseButton = new JButton("House");
  private JButton roadButton = new JButton("road");
  private JButton benchButton = new JButton("bench");
  private static int fWidth,fHeight,cellSize;
  
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
    if(source == sidewalkButton) {
      System.out.println("sidewalk button pressed");
      create = "sidewalk";
      System.out.println("create = " + create);
    }
  }

  public void paintComponent(Graphics g) {
    System.out.println("start printComponent");
  
    int y=cellSize;
    while(y<=fHeight*cellSize) {
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
    fWidth = 18;
    fHeight = 18;
    cellSize = 35;
    System.out.println("SiteDiagramGUI");
    JFrame f = new JFrame();
    f.add(new SiteDiagramGUI());
    f.setSize(fWidth*cellSize,(fHeight*cellSize));
    f.setVisible(true);
    
    //JFrame t = new JFrame();
    //t.setSize(100,500);
    //t.setVisible(true);
  }
 
};
