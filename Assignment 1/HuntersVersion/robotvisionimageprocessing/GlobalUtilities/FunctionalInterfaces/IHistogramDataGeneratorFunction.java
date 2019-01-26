/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing.GlobalUtilities.FunctionalInterfaces;

import robotvisionimageprocessing.Histogram.ImageHistogramData;

/**
 * @summary
 *  A functional interface for a method that generates histogram data.
 * @author im5no
 */
public interface IHistogramDataGeneratorFunction
{
    public ImageHistogramData generateHistogramData();
}