/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing.Histogram.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JPanel;

/**
 * @description
 *  This class is used as the JPanel for the color histogram GUI. Three of these
 *  panels are used by the HistogramFrame class (one for each color).
 * @author im5no
 */
public class HistogramPanel extends JPanel
{
    /* The number of pixels around the drawn histogram */
    private static final int HISTOGRAM_BUFFER = 20;
    /* The number width of the histogram. */
    private static final int HISTOGRAM_WIDTH = 256 + HISTOGRAM_BUFFER;
    /* The number height of the histogram. */
    private static final int HISTOGRAM_HEIGHT = 1000 + HISTOGRAM_BUFFER;
    
    /* A collection of Lines that should be drawn to this JPanel's graphics. */
    private final Collection<Line> lines;
    /* The color being represented in the histogram */
    /* AND the color of the histogram.*/
    private Color currentDrawingColor;
    
    public HistogramPanel()
    {
        this.lines = new ArrayList<>();
        
        Dimension frameDimension = new Dimension(
            HISTOGRAM_WIDTH + HISTOGRAM_BUFFER,
            HISTOGRAM_HEIGHT + HISTOGRAM_BUFFER);
        this.setPreferredSize(frameDimension);
    }
    
    /**
     * @summary
     *  The method used to draw on this panel's graphics. It draws a line on the
     *  JPanel for each Line in this class' "lines" collection.
     * @param componentGraphics 
     *  This JPanel's Graphics which is being drawn on.
     */
    @Override
    public void paintComponent(Graphics componentGraphics)
    {
        super.paintComponent(componentGraphics);
        componentGraphics.setColor(currentDrawingColor);
        lines.forEach(line ->
            componentGraphics.drawLine(
                line.getStartX(),
                line.getStartY(),
                line.getEndX(),
                line.getEndY()));
    }
    
    /**
     * @summary
     *  Sets the color of the drawing, then creates all Lines that should be
     *  drawn on this JPanel. (The name is a little misleading; drawing directly
     *  to a JPanel's graphics is poor practice, so this method specifies lines
     *  that should be drawn, and the java.awt engine handles drawing the lines)
     * @param intensityFrequencies
     *  The frequencies of the various intensities found in the image for the
     *  specified color.
     * @param histogramColor
     *  The color being represented in this histogram.
     */
    public void drawHistogram(
        int[] intensityFrequencies,
        Color histogramColor)
    {
        currentDrawingColor = histogramColor;
        int highestFrequency = findHighestFrequency(intensityFrequencies);
        
        for (int i = 0; i < 256; i++)
        {
            int currentFrequency = intensityFrequencies[i];
            int lineLength =
                HISTOGRAM_HEIGHT * currentFrequency / highestFrequency;
            int frequencyXIndex = HISTOGRAM_BUFFER + 1 + i;
            
            addLine(
                frequencyXIndex,
                HISTOGRAM_HEIGHT + HISTOGRAM_BUFFER + 1,
                lineLength);
        }
    }
    
    /**
     * @summary
     *  Finds the highest frequency of all intensities of the color represented
     *  in the panel's histogram. This is used to scale the histogram's size to
     *  fit in the panel.
     * @param intensityFrequencies
     *  A collection of frequencies of all the intensities present in the image
     *  being processed.
     * @return
     *  An integer representing the highest frequency of all intensities of the
     *  color represented in the panel's histogram.
     */
    private int findHighestFrequency(int[] intensityFrequencies)
    {
        int highestFrequency = 0;
        
        for (int i = 0; i < 256; i++)
        {
            int currentFrequency = intensityFrequencies[i];
            highestFrequency = (currentFrequency > highestFrequency)
                ? currentFrequency
                : highestFrequency;
        }
        
        return highestFrequency;
    }
    
    /**
     * @summary
     *  Creates a Line and adds it to this class' Collection of Lines.
     * @param startX
     *  The starting X pixel of the line.
     * @param startY
     *  The starting Y pixel of the line.
     * @param lineLength
     *  The length of the line (used to calculate endX and endY).
     */
    private void addLine(
        int startX,
        int startY,
        int lineLength)
    {
        Line newLine = new Line(
            startX,
            startY,
            startX,
            startY - lineLength);
        
        lines.add(newLine);
    }
}