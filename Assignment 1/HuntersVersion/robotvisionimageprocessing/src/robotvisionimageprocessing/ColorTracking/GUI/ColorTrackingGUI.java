/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing.ColorTracking.GUI;

import robotvisionimageprocessing.ColorTracking.GUI.ColorSelection.ColorSelectorFrame;
import robotvisionimageprocessing.GlobalUtilities.PatternInterfaces.ISubject;

/**
 * @summary
 *  A GUI class which contains 2 smaller GUIS: 1 for selecting a color to track,
 *  and one for displaying the tracked image.
 * @author im5no
 */
public class ColorTrackingGUI
{
    /* An instance of this class used to implement the singleton pattern */
    private static ColorTrackingGUI instance;
    /*  The GUI for selecting a color to track */
    private final ColorSelectorFrame colorSelectorFrame;
    /*  The GUI for displaying the tracked image */
    private final ColorTrackedImageFrame colorTrackedImageFrame;
    
    public static ColorTrackingGUI getInstance()
    {
        if (instance != null)
        {
            instance.colorSelectorFrame.setVisible(true);
            instance.colorTrackedImageFrame.setVisible(true);
        }
        
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
    
    /**
     * @summary
     *  Tracks the selected color (updates the GUI).
     */
    public void trackColor()
    {
        colorTrackedImageFrame.drawImage();
    }
    
    /**
     * @summary
     *  Registers the tracked image display GUI as a subscriber to the color
     *  tracking GUI.
     */
    public void registerTrackedImageFrameToIMP(ISubject<Object> subject)
    {
        colorTrackedImageFrame.subscribe(subject);
    }
}