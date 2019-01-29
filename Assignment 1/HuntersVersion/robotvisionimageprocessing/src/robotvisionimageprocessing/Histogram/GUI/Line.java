/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing.Histogram.GUI;

/**
 * @description
 *  This class contains the data required to draw a specific line on a JPanel.
 * @author im5no
 */
public class Line
{
    /* The start and end points of the line being drawn. */
    private final int startX;
    private final int startY;
    private final int endX;
    private final int endY;
    
    public Line(
        int startX,
        int startY,
        int endX,
        int endY)
    {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }
    
    /* Getters for the line data */
    
    public int getStartX()
    {
        return startX;
    }
    
    public int getStartY()
    {
        return startY;
    }
    
    public int getEndX()
    {
        return endX;
    }
    
    public int getEndY()
    {
        return endY;
    }
}