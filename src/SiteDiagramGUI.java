import javax.swing.*;
import java.awt.*;
<<<<<<< HEAD
import java.awt.geom.Line2D;

class SiteDiagramGUI extends JFrame {
public void init() {
  setBackground(Color.white);
  setForeground(Color.white);
}
  public static void main (String [] args) {
    int gui_width = 50;
    int gui_height = 50;
    int cell_size = 15;
    
    System.out.println("SiteDiagramGUI");
    JFrame f = new JFrame("Site Developer");
    f.setSize(gui_width*cell_size,gui_height*cell_size);
    f.setLocation(300,200);
    f.setVisible(true);
  } 
  public void paint(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setPaint(Color.gray);
    int x = 5;
    int y = 7;
    
    g2.draw(new Line2D.Double(x,y,200,200));
    g2.drawString("Line2D",x,250);
  }
=======
class SiteDiagramGUI extends JPanel {
  private static int fWidth,fHeight,cellSize;
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
    fWidth = 20;
    fHeight = 20;
    cellSize = 35;
    System.out.println("SiteDiagramGUI");
    JFrame f = new JFrame();
    f.add(new SiteDiagramGUI());
    f.setSize(fWidth*cellSize,fHeight*cellSize);
    f.setVisible(true);

    JFrame t = new JFrame();
    t.setSize(100,500);
    t.setVisible(true);
  }
 
>>>>>>> jpanel_test
};
