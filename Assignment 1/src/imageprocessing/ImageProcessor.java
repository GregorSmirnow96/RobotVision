/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import imageprocessing.BufferedImageUtilities.IImage;
import imageprocessing.ImageProcessingFunctions.IImageProcessingLambda;
import java.awt.image.BufferedImage;

/**
 * @summary
 *  Uses an IImageProcessingLamda to process an image.
 *  and output a new image.
 * @author im5no
 */
public class ImageProcessor
{
    // The image being processed.
    private IImage image;
    // The function pointer that will be used to process the image.
    private IImageProcessingLambda imageProcessingFunction;
    
    /* Constructor */
    public ImageProcessor(
        IImage image,
        IImageProcessingLambda imageProcessingFunction)
    {
        this.image = image;
        this.imageProcessingFunction = imageProcessingFunction;
    }
    
    /**
     * @summary
     *  Sets the image that this class will process
     *  and convert into a new image.
     * @param image
     *  The image being set.
     */
    public void setProcessedImage(IImage image)
    {
        this.image = image;
    }
    
    /**
     * @summary
     *  Sets the function pointer to the image processing
     *  function that will be used.
     * @param imageProcessingFunction
     *  An image processing function. It's signature is
     *  defined by the IImageProcessingLambda interface.
     */
    public void setProcessingFunction(
        IImageProcessingLambda imageProcessingFunction)
    {
        this.imageProcessingFunction = imageProcessingFunction;
    }
    
    /**
     * @summary
     *  Applies an imaging processing function to an image
     *  to generate a new image.
     * @return
     *  Returns the new, processed image.
     */
    public BufferedImage generateProcessedImage()
    {
        return imageProcessingFunction.processImage(image);
    }
}