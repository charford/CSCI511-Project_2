import javax.swing.*;
import java.awt.*;
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
};
