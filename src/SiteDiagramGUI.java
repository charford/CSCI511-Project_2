import javax.swing.*;
import java.awt.*;

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
 
};
