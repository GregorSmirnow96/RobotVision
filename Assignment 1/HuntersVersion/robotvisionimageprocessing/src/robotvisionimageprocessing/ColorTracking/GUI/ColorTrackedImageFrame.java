/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing.ColorTracking.GUI;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import robotvisionimageprocessing.GlobalUtilities.PatternInterfaces.IObserver;
import robotvisionimageprocessing.GlobalUtilities.PatternInterfaces.ISubject;

/**
 *
 * @author im5no
 */
public class ColorTrackedImageFrame extends JFrame implements IObserver<Object>
{
    private static final int DEFAULT_COLOR_MATCHING_RANGE = 16;
    
    private static ColorTrackedImageFrame instance;
    
    private int colorMatchingRange;
    private int[][] pretrackedImageData;
    private int[][] pixelArray2D;
    private final JPanel panel;
    private Color trackedColor;
    
    public static ColorTrackedImageFrame getInstance()
    {
        return instance == null
            ? instance = new ColorTrackedImageFrame()
            : instance;
    }
    
    private ColorTrackedImageFrame()
    {
        this.colorMatchingRange = DEFAULT_COLOR_MATCHING_RANGE;
        this.panel = new JPanel();
        
        add(panel);
        
        panel.setVisible(true);
        setVisible(true);
    }
    
    public void setColorMatchingRange(int newRange)
    {
        this.colorMatchingRange = newRange;
    }
    
    public void drawImage()
    {
        if (pretrackedImageData == null ||
            trackedColor == null)
            return;
        
        generateTrackedPixelArray2D();
        
        resetPicture();
    }
    
    private void generateTrackedPixelArray2D()
    {
        int imageHeight = pretrackedImageData.length;
        int imageWidth = pretrackedImageData[0].length;
        pixelArray2D = new int[imageHeight][imageWidth];
        
        int trackedRed = trackedColor.getRed();
        int trackedGreen = trackedColor.getGreen();
        int trackedBlue = trackedColor.getBlue();

        for (int i = 0; i < imageHeight; i++)
            for (int j = 0; j < imageWidth; j++)
            {
                int[] rgbArray = getPixelArray(pretrackedImageData[i][j]);
                int red = rgbArray[1];
                int green = rgbArray[2];
                int blue = rgbArray[3];
                boolean pixelMatchesTrackedColor =
                    red <= trackedRed + colorMatchingRange &&
                    red >= trackedRed - colorMatchingRange &&
                    green <= trackedGreen + colorMatchingRange &&
                    green >= trackedGreen - colorMatchingRange &&
                    blue <= trackedBlue + colorMatchingRange &&
                    blue >= trackedBlue - colorMatchingRange;

                int newIntensity = pixelMatchesTrackedColor
                    ? 255
                    : 0;

                int[] trackedRgbArray =
                    getPixelArray(pretrackedImageData[i][j]);
                trackedRgbArray[0] = 255;
                trackedRgbArray[1] = newIntensity;
                trackedRgbArray[2] = newIntensity;
                trackedRgbArray[3] = newIntensity;

                pixelArray2D[i][j] = getPixels(trackedRgbArray);
            }
    }
    
    private boolean colorsMatch(
        Color previousTrackedColor,
        Color trackedColor)
    {
        return previousTrackedColor.getRGB() == trackedColor.getRGB();
    }
    
    /*
     * This method takes a single integer value and breaks it down doing bit manipulation to 4 individual int values for A, R, G, and B values
     */
    private int[] getPixelArray(int pixel)
    {
        int temp[] = new int[4];
        temp[0] = (pixel >> 24) & 0xff;
        temp[1] = (pixel >> 16) & 0xff;
        temp[2] = (pixel >>  8) & 0xff;
        temp[3] = pixel & 0xff;
        return temp;
    }
    
    /*
     * This method takes an array of size 4 and combines the first 8 bits of each to create one integer. 
     */
    private int getPixels(int rgb[])
    {
        int rgba = (rgb[0] << 24) | (rgb[1] <<16) | (rgb[2] << 8) | rgb[3];
        return rgba;
    }
    
    private void resetPicture()
    {
        int imageHeight = pixelArray2D.length;
        int imageWidth = pixelArray2D[0].length;
        
        int[] pixelArray1D = new int[imageWidth * imageHeight];

        for(int i = 0; i < imageHeight; i++)
            System.arraycopy(
                pixelArray2D[i],
                0,
                pixelArray1D,
                i * imageWidth,
                imageWidth);
        
        MemoryImageSource memoryImageSource = new MemoryImageSource(
            imageWidth,
            imageHeight,
            pixelArray1D,
            0,
            imageWidth);
        
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image newImage = toolkit.createImage(memoryImageSource);

        JLabel newLabel = new JLabel(new ImageIcon(newImage));
        
        panel.removeAll();
        panel.add(newLabel);
        panel.revalidate();
        
        pack();
    }

    
    /* Implementation of the IObserver interface */
    
    @Override
    public void subscribe(ISubject<Object> subject)
    {
        subject.register(this);
    }

    @Override
    public void unscubscribe(ISubject<Object> subject)
    {
        subject.unregister(this);
    }

    @Override
    public void updateValue(Object newValue)
    {
        if (newValue instanceof int[][])
            pretrackedImageData = (int[][]) newValue;
        else if (newValue instanceof Color)
            trackedColor = (Color) newValue;
        
        drawImage();
    }
}