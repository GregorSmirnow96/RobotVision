/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing.ColorTracking;

import robotvisionimageprocessing.ColorTracking.GUI.ColorTrackingFrame;

/**
 *
 * @author im5no
 */
public class ColorTrackingThread extends Thread
{
    private final ColorTrackingFrame gui;
    private boolean shouldTerminateThread;
    
    public ColorTrackingThread(ColorTrackingFrame gui)
    {
        this.gui = gui;
        this.shouldTerminateThread = false;
    }
    
    @Override
    public void run()
    {
        while (!shouldTerminateThread)
        {
            if (gui.shouldTrackColor())
                gui.trackColor();
        }
    }
}