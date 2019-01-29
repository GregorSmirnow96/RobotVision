/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing.Histogram;

import robotvisionimageprocessing.Histogram.GUI.HistogramFrame;

/**
 * @summary
 *  This is a thread that runs in parallel to the main image processing thread.
 *  Its job is to update the histogram's displayed data in real time as the
 *  image is processed.
 * @author im5no
 */
public class HistogramUpdaterThread extends Thread
{
    /* The GUI that the thread is updating. */
    private final HistogramFrame gui;
    /* A boolean that - when false - keeps this thread alive. */
    private boolean shouldTerminateThread;
    
    public HistogramUpdaterThread(HistogramFrame gui)
    {
        this.gui = gui;
        this.shouldTerminateThread = false;
    }
    
    /**
     * @summary
     *  A method that can be called externally to terminate this thread.
     */
    public void terminate()
    {
        shouldTerminateThread = true;
    }
    
    /**
     * @summary
     *  The "main" method for this thread. It repeatedly updates the histogram
     *  until the terminate method is called.
     */
    @Override
    public void run()
    {
        while (!shouldTerminateThread)
        {
            gui.drawHistogram();
        }
    }
}