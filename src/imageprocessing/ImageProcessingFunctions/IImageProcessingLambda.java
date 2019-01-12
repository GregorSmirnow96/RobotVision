package imageprocessing.ImageProcessingFunctions;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import imageprocessing.BufferedImageUtilities.IImage;
import java.awt.image.BufferedImage;

/**
 * @summary
 *  A functional interface defining the signature of
 *  a method used to process a BufferedImage.
 * @author im5no
 */
public interface IImageProcessingLambda
{
    public BufferedImage processImage(IImage image);
}