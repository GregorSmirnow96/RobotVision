/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing.Histogram;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description
 *  This class contains data describing the frequencies of various intensities
 *  for a specific color in an image.
 * @author im5no
 */
public class ColorHistogramData
{
    /* The color whose intensity frequencies are being stored. */
    private final Color color;
    /* A container which Maps (color intensity) -> (frequency). */
    private final Map<Integer, Integer> colorIntensityFrequencies;
    
    public ColorHistogramData(Color color)
    {
        this.color = color;
        this.colorIntensityFrequencies = new HashMap<>();
        for (int i = 0; i < 256; i++)
            this.colorIntensityFrequencies.put(i, 0);
    }
    
    /**
     * @summary
     *  Increments the frequency of the provided intensity.
     * @param intensity 
     *  The intensity being incremented.
     */
    public void incrementIntensityFrequency(int intensity)
    {
        colorIntensityFrequencies.replace(
            intensity,
            colorIntensityFrequencies.get(intensity) + 1);
    }
    
    /**
     * @summary
     *  Gets the Color whose intensity frequencies are being stored.
     * @return
     *  The Color whose intensity frequencies are being stored.
     */
    public Color getColor()
    {
        return color;
    }
    
    /**
     * @summary
     *  Gets a collection of IntensityFrequencyTuples. These tuples encode
     *  the frequency at which a color intensity appears in the image being
     *  processed.
     * @return
     *  A List of IntensityFrequencyTuples.
     */
    public List<IntensityFrequencyTuple> getColorIntensityFrequencies()
    {
        List<IntensityFrequencyTuple> intensityFrequencies = new ArrayList<>();
        colorIntensityFrequencies.forEach((intensity, frequency) ->
        {
            intensityFrequencies.add(
                new IntensityFrequencyTuple(
                    intensity,
                    frequency));
        });
        
        return intensityFrequencies;
    }
}