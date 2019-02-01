/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing;

import robotvisionimageprocessing.EdgeDetection.Matrices.MatrixOperations;

/**
 *
 * @author im5no
 */
public class RobotVisionImageProcessing
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        /*
        int[][] m = new int[][]
        {
            { 10, 10, 10, 0, 0, 0 },
            { 10, 10, 10, 0, 0, 0 },
            { 10, 10, 10, 0, 0, 0 },
            { 10, 10, 10, 0, 0, 0 },
            { 10, 10, 10, 0, 0, 0 },
            { 10, 10, 10, 0, 0, 0 }
        };
        
        int[][] f = new int[][]
        {
            { 1, 0, -1 },
            { 1, 0, -1 },
            { 1, 0, -1 }
        };
        int[][] result = MatrixOperations.convolute(m, f);
        
        for (int[] row : result)
        {
            for (int e : row)
                System.out.print(e + " ");
            System.out.println();
        }
        */
        IMP imageProcessor = new IMP();
    }
}