/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing.Histogram.GUI;

import robotvisionimageprocessing.GlobalUtilities.FunctionalInterfaces.IHistogramDataGeneratorFunction;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import robotvisionimageprocessing.GlobalUtilities.PatternInterfaces.IObserver;
import robotvisionimageprocessing.GlobalUtilities.PatternInterfaces.ISubject;
import robotvisionimageprocessing.Histogram.ImageHistogramData;
import robotvisionimageprocessing.Histogram.IntensityFrequencyTuple;

/**
 * @description
 *  The JFrame which contains the 3 HistogramPanels generated for an image.
 * @author im5no
 */
public class HistogramFrame extends JFrame implements IObserver
{
    /* Instance used for the Singleton pattern */
    private static HistogramFrame instance;
    
    /* HistogramPanels for each of the 3 colors (RGB). */
    private final HistogramPanel redHistogramPanel;
    private final HistogramPanel greenHistogramPanel;
    private final HistogramPanel blueHistogramPanel;
    /* A container for the data represented in the 3 histograms. */
    private ImageHistogramData imageHistogramData;
    /* The method used to update the histogram chart. */
    private IHistogramDataGeneratorFunction updateHistogramDataFunction;
    
    /**
     * @summary
     *  The static method used to get the single instance of this class.
     * @return
     *  The singleton instance of this class.
     */
    public static HistogramFrame getInstance()
    {
        if (instance != null)
            instance.setVisible(true);
        
        return instance == null
            ? instance = new HistogramFrame()
            : instance;
    }
    
    private HistogramFrame()
    {
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
     *  A setter for the histogram data generation function.
     * @param updateHistogramDataFunction
     *  The function used to update the histogram's displayed data.
     */
    public void setHistogramDataGenerationFunction(
        IHistogramDataGeneratorFunction updateHistogramDataFunction)
    {
        this.updateHistogramDataFunction = updateHistogramDataFunction;
    }
    
    /**
     * @summary
     *  Draws the histrograms on each of the red/green/blue HistogramPanels.
     */
    public void drawHistogram()
    {
        if (updateHistogramDataFunction == null)
            return;
        
        imageHistogramData =
            updateHistogramDataFunction.generateHistogramData();
        
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
        int[] greenIntensities = rgbIntensities[1];
        greenHistogramPanel.drawHistogram(
            greenIntensities,
            Color.GREEN);
        int[] blueIntensities = rgbIntensities[2];
        blueHistogramPanel.drawHistogram(
            blueIntensities,
            Color.BLUE);
        
        this.repaint();
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

    
    /* Implementation of the IObserver interface */
    
    @Override
    public void subscribe(ISubject subject)
    {
        subject.register(this);
    }

    @Override
    public void unscubscribe(ISubject subject)
    {
        subject.unregister(this);
    }

    @Override
    public void updateValue(Object newValue)
    {
        drawHistogram();
    }
}