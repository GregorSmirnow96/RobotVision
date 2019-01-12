/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing.BufferedImageUtilities.BufferedImageAlphaChannelStates;

import java.awt.image.BufferedImage;

/**
 * @summary
 *  An implementation of the state-pattern used
 *  by the BufferedImageAdapter. This state is
 *  determined by whether or not the image in the
 *  BufferedImageAdapter has an alpha channel.
 *  This state influences how information is read
 *  the buffered image.
 * @author im5no
 */
public abstract class AlphaChannelState
{
    /**
     * @summary
     *  Gets a 2D array of integers from the
     *  buffered image. This array contains
     *  information on the image's pixels.
     * @param image
     *  The image being analyzed.
     * @return
     *  Returns a 2D integer array describing the
     *  pixels in the image.
     */
    public abstract int[][] get2DPixelColorArray(BufferedImage image);
}