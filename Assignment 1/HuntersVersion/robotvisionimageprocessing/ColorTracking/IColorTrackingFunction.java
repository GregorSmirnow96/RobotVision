/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing.ColorTracking;

import java.awt.Color;

/**
 *
 * @author im5no
 */
public interface IColorTrackingFunction
{
    public void trackColor(
        Color trackedColor,
        int colorMatchingRange);
}