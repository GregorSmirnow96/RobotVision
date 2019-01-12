/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing.BufferedImageUtilities;

import imageprocessing.BufferedImageUtilities.BufferedImageAlphaChannelStates.AlphaChannelState;
import imageprocessing.BufferedImageUtilities.BufferedImageAlphaChannelStates.DoesNotHaveAlphaChannel;
import imageprocessing.BufferedImageUtilities.BufferedImageAlphaChannelStates.HasAlphaChannel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author im5no
 */
public class BufferedImageAdapter implements IImage
{
    // The BufferedImage we're creating a new interface for.
    private final BufferedImage bufferedImage;
    // A state-pattern implementation that models whether or not the image
    // we're using contains alpha values for each pixel.
    private final AlphaChannelState alphaChannelState;
    // The image's dimensions.
    private final int height;
    private final int width;
    // The image's pixels encoded as integers.
    private final int[][] pixelArray;
            
    /* Constructor */
    public BufferedImageAdapter(String imageFilePath) throws IOException
    {
        File imageFile = new File(imageFilePath);
        
        this.bufferedImage = ImageIO.read(imageFile);
        this.alphaChannelState = bufferedImage.getAlphaRaster() == null
            ? new DoesNotHaveAlphaChannel()
            : new HasAlphaChannel();
        this.height = bufferedImage.getHeight();
        this.width = bufferedImage.getWidth();
        this.pixelArray = alphaChannelState.get2DPixelColorArray(bufferedImage);
    }
    
    /**
     * @summary
     *  Gets the 2D array of integers that encode the
     *  pixel colors in this image.
     * @return 
     *  A 2D array of integers that encode the pixel
     *  colors in this image.
     */
    public int[][] getPixels()
    {
        return pixelArray;
    }
    
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
        int xIndex)
    {
        return pixelArray[xIndex][yIndex];
    }
    
    /**
     * @summary
     *  Gets height of the image.
     * @return
     *  Returns the height of the image.
     */
    public int getHeight()
    {
        return height;
    }
    
    /**
     * @summary
     *  Gets width of the image.
     * @return
     *  Returns the width of the image.
     */
    public int getWidth()
    {
        return width;
    }
}