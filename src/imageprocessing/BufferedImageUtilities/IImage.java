/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing.BufferedImageUtilities;

/**
 *
 * @author im5no
 */
public interface IImage
{
    
    /**
     * @summary
     *  Gets the 2D array of integers that encode the
     *  pixel colors in this image.
     * @return 
     *  A 2D array of integers that encode the pixel
     *  colors in this image.
     */
    public int[][] getPixels();
    
    /**
     * @summary
     *  Gets the integer encoding of a pixel in the image
     *  from its coordinates.
     * @param yIndex
     *  The y-coordinate of the pixel.
     * @param xIndex
     *  The x-coordinate of the pixel.
     * @return 
     *  An integer encoding of the pixel at the specified
     *  location.
     */
    public int getPixel(
        int yIndex,
        int xIndex);
    
    /**
     * @summary
     *  Gets height of the image.
     * @return
     *  Returns the height of the image.
     */
    public int getHeight();
    
    /**
     * @summary
     *  Gets width of the image.
     * @return
     *  Returns the width of the image.
     */
    public int getWidth();
}