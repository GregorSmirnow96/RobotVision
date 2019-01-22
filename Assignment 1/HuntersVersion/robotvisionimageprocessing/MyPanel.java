package robotvisionimageprocessing;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/* Why does this class exist? */
public class MyPanel extends JPanel
{
    int startX;
    int flag;
    int startY;
    int endX;
    int endY;
    BufferedImage grid;
    Graphics2D graphics2D;
    
    public MyPanel()
    {
	startX = 0;
        startY = 0;
        endX = 0;
        endY = 100;
    }

    public void clear()
    {
        grid = null;
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics graphicsToBePainted)
    {
        super.paintComponent(graphicsToBePainted);
        
        Graphics2D newGrapbics = (Graphics2D) graphicsToBePainted;
        if (grid == null)
        {
            int width = this.getWidth();
            int height = this.getHeight();
            grid = (BufferedImage) createImage(
                width,
                height);
            graphics2D = grid.createGraphics();
        }
        
        newGrapbics.drawImage(
            grid,
            null,
            0,
            0);
     }
    
    public void drawing()
    {
        graphics2D.drawLine(
            startX,
            startY,
            endX,
            endY);
        
        repaint();
    }
}