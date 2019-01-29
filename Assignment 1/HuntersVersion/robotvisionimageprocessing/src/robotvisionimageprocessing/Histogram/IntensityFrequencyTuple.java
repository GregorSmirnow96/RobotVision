/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing.Histogram;

/**
 *
 * @author im5no
 */
public class IntensityFrequencyTuple
{
    private final int intensity;
    private final int frequency;
    
    public IntensityFrequencyTuple(
        int intensity,
        int frequency)
    {
        this.intensity = intensity;
        this.frequency = frequency;
    }
    
    public int getIntensity()
    {
        return intensity;
    }
    
    public int getFrequency()
    {
        return frequency;
    }
}