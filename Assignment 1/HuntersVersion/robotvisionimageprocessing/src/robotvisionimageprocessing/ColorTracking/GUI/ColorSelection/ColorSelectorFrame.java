/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing.ColorTracking.GUI.ColorSelection;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import robotvisionimageprocessing.GlobalUtilities.PatternInterfaces.IObserver;
import robotvisionimageprocessing.GlobalUtilities.PatternInterfaces.ISubject;

/**
 *
 * @author im5no
 */
public class ColorSelectorFrame
    extends JFrame
    implements ISubject<Object>
{
    private static final int DEFAULT_FRAME_WIDTH = 540;
    private static final int DEFAULT_FRAME_HEIGHT = 280;
    
    private static ColorSelectorFrame instance;
    private final JPanel panel;
    private final ColorSelectorPanel colorSelectorPanel;
    private final JLabel instructionsLabel;
    private final Collection<IObserver<Object>> subscribers;
    
    public static ColorSelectorFrame getInstance()
    {
        return instance == null
            ? instance = new ColorSelectorFrame()
            : instance;
    }
    
    private ColorSelectorFrame()
    {
        this.subscribers = new ArrayList<>();
        this.instructionsLabel = new JLabel("Select a color to track");
        instructionsLabel.setFont(
            new Font(
                "Calibri",
                Font.BOLD,
                24));
        this.panel = new JPanel();
        this.colorSelectorPanel = new ColorSelectorPanel();
        
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.VERTICAL;
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(
            instructionsLabel,
            constraints);
        
        constraints.gridy = 1;
        constraints.ipady = 0;
        panel.add(
            colorSelectorPanel,
            constraints);
        
        panel.setVisible(true);
        
        add(panel);
        setVisible(true);
        
        setPreferredSize(new Dimension(
            DEFAULT_FRAME_WIDTH,
            DEFAULT_FRAME_HEIGHT));
        pack();
    }
    
    public Color getSelectedColor()
    {
        return this.colorSelectorPanel.getSelectedColor();
    }

    
    /* Implementation of the IObserver / ISubject interfaces */
    
    @Override
    public void register(IObserver<Object> observer)
    {
        if (!subscribers.contains(observer))
        {
            subscribers.add(observer);
            observer.updateValue(getSelectedColor());
        }
    }

    @Override
    public void unregister(IObserver<Object> observer)
    {
        subscribers.remove(observer);
    }

    @Override
    public void notifySubscribers()
    {
        subscribers.forEach(
            subscriber -> subscriber.updateValue(getSelectedColor()));
    }

    public void registerTrackedImageFrameToColorSelector(
        IObserver<Object> colorTrackedImageFrame)
    {
        colorSelectorPanel.register(colorTrackedImageFrame);
    }
}