/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing.EdgeDetection;

import robotvisionimageprocessing.EdgeDetection.Matrices.MatrixOperations;

/**
 * <p>
 *  A class used to detect edges in an image.
 * </p>
 * @author im5no
 */
public class EdgeDetector
{
    /**
     * A 2D array of integers from 0-255. Each int represents the intensity of
     * the red, green, and blue values in a grayscale image. The image this
     * encodes shows the edges found in some original image.
     */
    private final int[][] greyscaleValueArray;
    /**
     * A 3x3 array used to perform convolution on the original image. This
     * filter finds the vertical edges.
     */
    private final int[][] xFilter;
    /**
     * A 3x3 array used to perform convolution on the original image. This
     * filter finds the horizontal edges.
     */
    private final int[][] yFilter;
    
    public EdgeDetector(int[][] originalPixelArray)
    {
        int arrayWidth = originalPixelArray[0].length;
        int arrayHeight = originalPixelArray.length;
        this.greyscaleValueArray = new int[arrayHeight][arrayWidth];
        for (int y = 0; y < arrayHeight; y++)
            for (int x = 0; x < arrayWidth; x++)
                greyscaleValueArray[y][x] = originalPixelArray[y][x] & 0xff;
        
        this.xFilter = new int[][]
        {
            { -1, 0, 1 },
            { -2, 0, 2 },
            { -1, 0, 1 }
        };
        this.yFilter = new int[][]
        {
            { -1, -2, -1 },
            {  0,  0,  0 },
            {  1,  2,  1 }
        };
    }
    
    /**
     * <p>
     *  Calculates the gradient of the light intensities in a grayscale image.
     *  Steeper gradients are represented as whiter pixels.
     * </p>
     * @return
     *  Returns an int[][] where each int in this 2D-array represents the
     *  grayscale color of a pixel in a new image. This new image is also gray-
     *  scale, and it encodes the edges of the original image.
     */
    public int[][] calculateEdges()
    {
        int[][] verticalEdges = calculateVerticalEdges();
        int[][] horizontalEdges = calculateHorizontalEdges();
        
        int newHeight = greyscaleValueArray.length;
        int newWidth = greyscaleValueArray[0].length;
        
        int[][] edges = new int[newHeight][newWidth];
        
        int max = Integer.MIN_VALUE;
        for (int y = 1; y < newHeight-1; y++)
            for (int x = 1; x < newWidth-1; x++)
            {
                int gx = verticalEdges[y-1][x-1];
                int gy = horizontalEdges[y-1][x-1];
                
                int gx2 = gx * gx;
                int gy2 = gy * gy;
                
                int g = (int) Math.sqrt(gx2 + gy2);
                max = max > g
                    ? max
                    : g;
                
                edges[y][x] = g;
            }
        
        for (int y = 1; y < newHeight-1; y++)
            for (int x = 1; x < newWidth-1; x++)
            {
                edges[y][x] = edges[y][x] * 255 / max;
            }
        
        return edges;
    }
    
    /**
     * <p>
     *  Finds the vertical edges in an image.
     * </p>
     * @return
     *  An int[][] representing an image which encodes the vertical edges in
     *  the original image.
     */
    private int[][] calculateVerticalEdges()
    {
        return MatrixOperations.convolute(
            greyscaleValueArray,
            xFilter);
    }
    
    /**
     * <p>
     *  Finds the horizontal edges in an image.
     * </p>
     * @return
     *  An int[][] representing an image which encodes the horizontal edges in
     *  the original image.
     */
    private int[][] calculateHorizontalEdges()
    {
        return MatrixOperations.convolute(
            greyscaleValueArray,
            yFilter);
    }
}