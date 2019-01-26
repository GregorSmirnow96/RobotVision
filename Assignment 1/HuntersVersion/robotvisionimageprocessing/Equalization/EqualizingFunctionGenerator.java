/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing.Equalization;

import java.util.Collection;
import robotvisionimageprocessing.Histogram.IntensityFrequencyTuple;

/**
 *
 * @author im5no
 */
public class EqualizingFunctionGenerator
{
    public IEqualizingFunction generateFunction(
        Collection<IntensityFrequencyTuple> intensityFrequencies)
    {
        int lowestIntensity = getLowestIntensity(intensityFrequencies);
        int intensityRange = getIntensityRange(intensityFrequencies);
        
        return unequalizedValue ->
        {
            float normalizedValue =
                (unequalizedValue - lowestIntensity) / intensityRange;
            
            return scaleNormalizedValueToRGBRange(normalizedValue);
        };
    }
    
    private int getLowestIntensity(
        Collection<IntensityFrequencyTuple> intensityFrequencies)
    {
        int lowestIntensity = Integer.MAX_VALUE;
        
        for (IntensityFrequencyTuple tuple : intensityFrequencies)
        {
            if (tuple.getFrequency() == 0)
                continue;
            
            int currentIntensity = tuple.getIntensity();
            
            lowestIntensity = currentIntensity < lowestIntensity
                ? currentIntensity
                : lowestIntensity;
        }
        
        return lowestIntensity;
    }
    
    private int getIntensityRange(
        Collection<IntensityFrequencyTuple> intensityFrequencies)
    {
        int lowestIntensity = Integer.MAX_VALUE;
        int highestIntensity = Integer.MIN_VALUE;
        
        for (IntensityFrequencyTuple tuple : intensityFrequencies)
        {
            if (tuple.getFrequency() == 0)
                continue;
            
            int currentIntensity = tuple.getIntensity();
            
            lowestIntensity = currentIntensity < lowestIntensity
                ? currentIntensity
                : lowestIntensity;
            
            highestIntensity = currentIntensity > highestIntensity
                ? currentIntensity
                : highestIntensity;
        }
        
        return highestIntensity - lowestIntensity;
    }
    
    private int scaleNormalizedValueToRGBRange(float normalizedValue)
    {
        return (int) (normalizedValue * 255);
    }
}