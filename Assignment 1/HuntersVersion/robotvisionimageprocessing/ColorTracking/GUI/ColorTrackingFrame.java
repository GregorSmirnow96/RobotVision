/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing.ColorTracking.GUI;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import robotvisionimageprocessing.ColorTracking.ColorTrackingThread;
import robotvisionimageprocessing.ColorTracking.IColorTrackingFunction;

/**
 *
 * @author im5no
 */
public class ColorTrackingFrame extends JFrame
{
    private static final int DEFAULT_COLOR_MATCHING_RANGE = 10;
    
    private final IColorTrackingFunction trackingFuncton;
    private final JPanel panel;
    private final ColorSelectorPanel colorSelectorPanel;
    private final JButton trackColorButton;
    private final JRadioButton trackColorRadioButton;
    private int colorMatchingRange;
    private final ColorTrackingThread colorTrackingThread;
    
    public ColorTrackingFrame(IColorTrackingFunction trackingFunction)
    {
        this.colorMatchingRange = DEFAULT_COLOR_MATCHING_RANGE;
        this.trackingFuncton = trackingFunction;
        this.panel = new JPanel();
        this.colorSelectorPanel = new ColorSelectorPanel();
        this.trackColorRadioButton = new JRadioButton();
        
        this.trackColorButton = new JButton("Track");
        trackColorButton.addActionListener(
            (ActionEvent event) ->
            {
                trackColor();
            });
        
        panel.add(trackColorRadioButton);
        panel.add(colorSelectorPanel);
        panel.add(trackColorButton);
        panel.setVisible(true);
        
        add(panel);
        setVisible(true);
        pack();
        
        this.colorTrackingThread = new ColorTrackingThread(this);
        colorTrackingThread.start();
    }
    
    public void setColorMatchingRange(int newRange)
    {
        this.colorMatchingRange = newRange;
    }
    
    public boolean shouldTrackColor()
    {
        return trackColorRadioButton.isSelected();
    }
    
    public void trackColor()
    {
        trackingFuncton.trackColor(
            colorSelectorPanel.getSelectedColor(),
            colorMatchingRange);
    }
}