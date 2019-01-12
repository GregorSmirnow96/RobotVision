/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import imageprocessing.BufferedImageUtilities.BufferedImageAdapter;
import imageprocessing.ImageProcessingFunctions.ImageProcessingTechniques;
import imageprocessing.BufferedImageUtilities.IImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author im5no
 */
public class ImageProcessing
{
    public static String inputImageFilePath =
        "C:\\Users\\im5no\\Documents\\NetBeansProjects" +
        "\\ImageProcessing\\test_images\\APrettyFuckinMountainDawg.jpg";
    public static String outputImageFilePath =
        "C:\\Users\\im5no\\Documents\\NetBeansProjects" +
        "\\ImageProcessing\\test_images\\ProcessedImage.jpg";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {
        // Instatiate this as an instance of a concrete IImage.
        IImage image = new BufferedImageAdapter(inputImageFilePath);
        
        ImageProcessor imageProcessor = new ImageProcessor(
            image,
            ImageProcessingTechniques::removeRed);
        
        BufferedImage processedImage = imageProcessor.generateProcessedImage();
        File outputFile = new File(outputImageFilePath);
        ImageIO.write(
            processedImage,
            "jpg",
            outputFile);
    }
}
