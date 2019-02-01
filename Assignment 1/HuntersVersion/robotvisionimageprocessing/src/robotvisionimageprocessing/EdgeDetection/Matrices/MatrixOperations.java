/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing.EdgeDetection.Matrices;

/**
 * <p>
 *  A static class used to defined various matrix operations.
 * </p>
 * @author im5no
 */
public class MatrixOperations
{
    /**
     * <p>
     *  Multiplies 2 matricies.
     * </p>
     * @param m1
     *  The first matrix.
     * @param m2
     *  The second matrix.
     * @return
     *  The product matrix.
     */
    public static int[][] multiply(
        int[][] m1,
        int[][] m2)
    {
        int newHeight = m1.length;
        int newWidth = m2[0].length;
        int numberOfElementsToMultiply = m1[0].length;
        int[][] result = new int[newHeight][newWidth];
        
        for (int y = 0; y < newHeight; y++)
        {
            for (int x = 0; x < newWidth; x++)
            {
                int newValue = 0;
                for (int i = 0; i < numberOfElementsToMultiply; i++)
                {
                    int m1Element = m1[y][i];
                    int m2Element = m2[i][x];
                    newValue += m1Element * m2Element;
                }
                
                result[y][x] = newValue;
            }
        }
        
        return result;
    }
    
    /**
     * <p>
     *  Performs convolution on a matrix given a specific filter.
     * </p>
     * @param matrix
     *  The matrix being convoluted.
     * @param filter
     *  The filter used to convolute the matrix.
     * @return
     *  A new, convoluted matrix.
     */
    public static int[][] convolute(
        int[][] matrix,
        int[][] filter)
    {
        int newHeight = matrix.length - 2;
        int newWidth = matrix[0].length - 2;
        int[][] result = new int[newHeight][newWidth];
        
        for (int x = 1; x <= newWidth; x++)
        {
            for (int y = 1; y <= newHeight; y++)
            {
                int newValue = 0;
                for (int xOffset = -1; xOffset <= 1; xOffset++)
                {
                    for (int yOffset = -1; yOffset <= 1; yOffset++)
                    {
                        int matrixX = x + xOffset;
                        int matrixY = y + yOffset;
                        int filterX = xOffset + 1;
                        int filterY = yOffset + 1;
                        
                        newValue +=
                            matrix[matrixY][matrixX] * filter[filterY][filterX];
                    }
                }
                
                result[y - 1][x - 1] = newValue;
            }
        }
        
        return result;
    }
}