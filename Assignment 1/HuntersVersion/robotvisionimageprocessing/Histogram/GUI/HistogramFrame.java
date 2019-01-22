/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing.Histogram.GUI;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import robotvisionimageprocessing.Histogram.ImageHistogramData;
import robotvisionimageprocessing.Histogram.IntensityFrequencyTuple;

/**
 * @description
 *  The JFrame which contains the 3 HistogramPanels generated for an image.
 * @author im5no
 */
public class HistogramFrame extends JFrame
{
    /* HistogramPanels for each of the 3 colors (RGB). */
    private final HistogramPanel redHistogramPanel;
    private final HistogramPanel greenHistogramPanel;
    private final HistogramPanel blueHistogramPanel;
    /* A container for the data represented in the 3 histograms. */
    private final ImageHistogramData imageHistogramData;
    
    public HistogramFrame(ImageHistogramData imageHistogramData)
    {
        this.imageHistogramData = imageHistogramData;
        this.redHistogramPanel = new HistogramPanel();
        this.greenHistogramPanel = new HistogramPanel();
        this.blueHistogramPanel = new HistogramPanel();
        
        JPanel panel = new JPanel();
        panel.add(redHistogramPanel);
        panel.add(greenHistogramPanel);
        panel.add(blueHistogramPanel);
        this.add(panel);
        
        this.pack();
        this.setVisible(true);
    }
    
    /**
     * @summary
     *  Draws the histrograms on each of the red/green/blue HistogramPanels.
     */
    public void drawHistogram()
    {
        int[][] rgbIntensities = getRGBIntensities();
        
        /* This is a result of a mistake I couldn't fix. The HistogramPanels'
         * Graphics was null for the first ~0.1 seconds of the object lifespan.
         */
        try { Thread.sleep(100); }
        catch (InterruptedException e) { }
        
        int[] redIntensities = rgbIntensities[0];
        redHistogramPanel.drawHistogram(
            redIntensities,
            Color.RED);
        greenHistogramPanel.drawHistogram(
            rgbIntensities[1],
            Color.GREEN);
        blueHistogramPanel.drawHistogram(
            rgbIntensities[2],
            Color.BLUE);
    }
    
    /**
     * @summary
     *  Extracts color-intensity data from imageHistogramData, and puts the data
     *  in 3 integer arrays (one array for each color).
     * @return
     *  Returns an int[3][256]. These arrays contain color-intensity data for
     *  each color (RGB).
     */
    private int[][] getRGBIntensities()
    {
        int[] redIntensities = new int[256];
        int i = 0;
        for (IntensityFrequencyTuple tuple
            : imageHistogramData.getRedIntensityFrequencies())
            redIntensities[i++] = tuple.getFrequency();
        
        int[] greenIntensities = new int[256];
        i = 0;
        for (IntensityFrequencyTuple tuple
            : imageHistogramData.getGreenIntensityFrequencies())
            greenIntensities[i++] = tuple.getFrequency();
        
        int[] blueIntensities = new int[256];
        i = 0;
        for (IntensityFrequencyTuple tuple
            : imageHistogramData.getBlueIntensityFrequencies())
            blueIntensities[i++] = tuple.getFrequency();
        
        return new int[][]
        {
            redIntensities,
            greenIntensities,
            blueIntensities
        };
    }
}