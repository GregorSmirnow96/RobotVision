/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing.Histogram;

import java.awt.Color;
import java.util.List;

/**
 * @description
 *  This class contains color-histogram data for the red, green, and blue light
 *  in an image.
 * @author im5no
 */
public class ImageHistogramData
{
    /* Histogram data for each color (RGB) */
    private final ColorHistogramData redHistogramData;
    private final ColorHistogramData greenHistogramData;
    private final ColorHistogramData blueHistogramData;
    
    public ImageHistogramData()
    {
        this.redHistogramData = new ColorHistogramData(Color.RED);
        this.greenHistogramData = new ColorHistogramData(Color.GREEN);
        this.blueHistogramData = new ColorHistogramData(Color.BLUE);
    }
    
    /**
     * @summary
     *  Increments the frequency of which the provided red intensity occurs in
     *  the image being processed.
     * @param intensity
     *  The intensity whose frequency is being incremented.
     */
    public void incrementRedHistogramTuple(int intensity)
    {
        redHistogramData.incrementIntensityFrequency(intensity);
    }
    
    /**
     * @summary
     *  Increments the frequency of which the provided green intensity occurs in
     *  the image being processed.
     * @param intensity
     *  The intensity whose frequency is being incremented.
     */
    public void incrementGreenHistogramTuple(int intensity)
    {
        greenHistogramData.incrementIntensityFrequency(intensity);
    }
    
    /**
     * @summary
     *  Increments the frequency of which the provided blue intensity occurs in
     *  the image being processed.
     * @param intensity
     *  The intensity whose frequency is being incremented.
     */
    public void incrementBlueHistogramTuple(int intensity)
    {
        blueHistogramData.incrementIntensityFrequency(intensity);
    }
    
    /**
     * @summary
     *  Gets a list of red IntensityFrequencyTuples. This list has all the data
     *  required to draw the red color-intensity histogram.
     * @return
     *  A List of IntensityFrequencyTuples which encodes the red light
     *  histogram.
     */
    public List<IntensityFrequencyTuple> getRedIntensityFrequencies()
    {
        return redHistogramData.getColorIntensityFrequencies();
    }
    
    /**
     * @summary
     *  Gets a list of green IntensityFrequencyTuples. This list has all the
     *  data required to draw the green color-intensity histogram.
     * @return
     *  A List of IntensityFrequencyTuples which encodes the green light
     *  histogram.
     */
    public List<IntensityFrequencyTuple> getGreenIntensityFrequencies()
    {
        return greenHistogramData.getColorIntensityFrequencies();
    }
    
    /**
     * @summary
     *  Gets a list of blue  IntensityFrequencyTuples. This list has all the data
     *  required to draw the blue color-intensity histogram.
     * @return
     *  A List of IntensityFrequencyTuples which encodes the blue light
     *  histogram.
     */
    public List<IntensityFrequencyTuple> getBlueIntensityFrequencies()
    {
        return blueHistogramData.getColorIntensityFrequencies();
    }
}