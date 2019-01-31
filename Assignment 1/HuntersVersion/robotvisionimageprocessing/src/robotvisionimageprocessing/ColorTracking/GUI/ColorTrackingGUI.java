/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing.ColorTracking.GUI;

import robotvisionimageprocessing.ColorTracking.GUI.ColorSelection.ColorSelectorFrame;
import robotvisionimageprocessing.GlobalUtilities.PatternInterfaces.ISubject;

/**
 *
 * @author im5no
 */
public class ColorTrackingGUI
{
    private static ColorTrackingGUI instance;
    private final ColorSelectorFrame colorSelectorFrame;
    private final ColorTrackedImageFrame colorTrackedImageFrame;
    
    public static ColorTrackingGUI getInstance()
    {
        return instance == null
            ? instance = new ColorTrackingGUI()
            : instance;
    }
    
    private ColorTrackingGUI()
    {
        this.colorSelectorFrame = ColorSelectorFrame.getInstance();
        this.colorTrackedImageFrame = ColorTrackedImageFrame.getInstance();
        
        colorSelectorFrame.register(colorTrackedImageFrame);
        colorSelectorFrame.registerTrackedImageFrameToColorSelector(
            colorTrackedImageFrame);
    }
    
    public void trackColor()
    {
        colorTrackedImageFrame.drawImage();
    }
    
    public void registerTrackedImageFrameToIMP(ISubject<Object> subject)
    {
        colorTrackedImageFrame.subscribe(subject);
    }
}