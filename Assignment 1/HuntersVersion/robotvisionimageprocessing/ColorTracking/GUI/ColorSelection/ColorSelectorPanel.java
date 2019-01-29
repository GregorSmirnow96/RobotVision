/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing.ColorTracking.GUI.ColorSelection;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JLabel;
import javax.swing.JPanel;
import robotvisionimageprocessing.GlobalUtilities.PatternInterfaces.IObserver;
import robotvisionimageprocessing.GlobalUtilities.PatternInterfaces.ISubject;

/**
 *
 * @author im5no
 */
public class ColorSelectorPanel
    extends JPanel
    implements
        IObserver<Integer>,
        ISubject<Object>
{
    private final ColorSliderTextBoxPair redSelector;
    private final ColorSliderTextBoxPair greenSelector;
    private final ColorSliderTextBoxPair blueSelector;
    private final JLabel redSelectorLabel;
    private final JLabel greenSelectorLabel;
    private final JLabel blueSelectorLabel;
    private final Collection<IObserver> subscribers;
    
    public ColorSelectorPanel()
    {
        this.subscribers = new ArrayList<>();
        
        this.redSelector = new ColorSliderTextBoxPair();
        redSelector.registerObserverToSlider(this);
        this.greenSelector = new ColorSliderTextBoxPair();
        greenSelector.registerObserverToSlider(this);
        this.blueSelector = new ColorSliderTextBoxPair();
        blueSelector.registerObserverToSlider(this);
        
        this.redSelectorLabel = new JLabel("Red");
        redSelectorLabel.setForeground(Color.RED);
        this.greenSelectorLabel = new JLabel("Green");
        greenSelectorLabel.setForeground(Color.GREEN);
        this.blueSelectorLabel = new JLabel("Blue");
        blueSelectorLabel.setForeground(Color.BLUE);
        
        createGUILayout();
    }
    
    private void createGUILayout()
    {
        this.setLayout(new GridBagLayout());
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.VERTICAL;
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(
            redSelectorLabel,
            constraints);
        
        constraints.gridy = 1;
        add(
            greenSelectorLabel,
            constraints);
        
        constraints.gridy = 2;
        add(
            blueSelectorLabel,
            constraints);
        
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(
            redSelector,
            constraints);
        
        constraints.gridy = 1;
        add(
            greenSelector,
            constraints);
        
        constraints.gridy = 2;
        add(
            blueSelector,
            constraints);
    }
    
    public Color getSelectedColor()
    {
        return new Color(
            redSelector.getValue(),
            greenSelector.getValue(),
            blueSelector.getValue());
    }
    
    
    /* Implementation of the IObserver / ISubject interfaces */

    @Override
    public void subscribe(ISubject<Integer> subject)
    {
        subject.register(this);
    }

    @Override
    public void unscubscribe(ISubject<Integer> subject)
    {
        subject.unregister(this);
    }

    @Override
    public void updateValue(Integer newValue)
    {
        notifySubscribers();
    }

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
        Color newColor = getSelectedColor();
        subscribers.forEach(subscriber -> subscriber.updateValue(newColor));
    }
}