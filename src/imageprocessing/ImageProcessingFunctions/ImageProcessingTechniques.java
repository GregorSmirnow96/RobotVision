package imageprocessing.ImageProcessingFunctions;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import imageprocessing.BufferedImageUtilities.IImage;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * @summary
 *  A class with static methods. Each method
 *  is an algorithm for a particular image processing
 *  technique. The image processing functions can be
 *  passed as pointers to an instance of ImageProcessor.
 *  The function will be applied to the ImageProcessor's
 *  BufferedImageAdapter, and a new, processed image will
 *  be created.
 * @author im5no
 */
public class ImageProcessingTechniques
{
    /**
     * @summary
     *  Removes all red color from the provided image.
     * @param image
     *  The image being processed.
     * @return 
     *  Returns a copy of the provided image with all
     *  the red removed.
     */
    public static BufferedImage removeRed(IImage image)
    {
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] pixels = image.getPixels();
        
        int[] pixels1D = new int[width * height];
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
            {
                int pixelRGB = pixels[i][j];
                int newRGB = 0;
                newRGB += pixelRGB & 0xff;          // blue
                newRGB += pixelRGB & 0xff << 8;     // green
                // newRGB += pixelRGB & 0xff << 16;    // red
                pixels1D[j * width + i] = newRGB;
            }
        
        BufferedImage bufferedImage = new BufferedImage(
            width,
            height,
            BufferedImage.TYPE_INT_RGB);
        final int[] copiedArray = ((DataBufferInt) bufferedImage
            .getRaster()
            .getDataBuffer())
            .getData();
        System.arraycopy(
            pixels1D,
            0,
            copiedArray,
            0,
            copiedArray.length);
        
        return bufferedImage;
    }
    
    /* Add more techniques down here... */
}