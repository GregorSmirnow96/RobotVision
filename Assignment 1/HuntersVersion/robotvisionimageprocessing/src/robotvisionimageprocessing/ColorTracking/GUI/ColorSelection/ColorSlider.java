/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing.ColorTracking.GUI.ColorSelection;

import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JSlider;
import robotvisionimageprocessing.GlobalUtilities.PatternInterfaces.IObserver;
import robotvisionimageprocessing.GlobalUtilities.PatternInterfaces.ISubject;

/**
 *
 * @author im5no
 */
public class ColorSlider
    extends JSlider
    implements
        IObserver<Integer>,
        ISubject<Integer>
{
    private static final int MINIMUM_VALUE = 0;
    private static final int MAXIMUM_VALUE = 255;
    private static final int MINOR_TICK_SPACING = 1;
    private static final int MAJOR_TICK_SPACING = 64;
    private static final int INITIAL_VALUE = 0;
    
    private final Collection<IObserver> observers;
    private ISubject<Integer> observedSubject;
    
    public ColorSlider()
    {
        super(
            JSlider.HORIZONTAL,
            MINIMUM_VALUE,
            MAXIMUM_VALUE,
            INITIAL_VALUE);
        setMinorTickSpacing(MINOR_TICK_SPACING);
        setMajorTickSpacing(MAJOR_TICK_SPACING);
        
        addChangeListener(changeEvent ->
        {
            notifySubscribers();
        });
        
        this.observers = new ArrayList<>();
        
        setVisible(true);
    }
    
    
    /* Implementation of the IObserver / ISubscriber interfaces */

    @Override
    public void subscribe(ISubject<Integer> subject)
    {
        if (observedSubject != null)
            observedSubject.unregister(this);
            
        subject.register(this);
        observedSubject = subject;
    }

    @Override
    public void unscubscribe(ISubject<Integer> subject)
    {
        subject.unregister(this);
    }

    @Override
    public void updateValue(Integer newValue)
    {
        setValue(newValue);
    }

    @Override
    public void register(IObserver observer)
    {
        observers.add(observer);
    }

    @Override
    public void unregister(IObserver observer)
    {
        observers.remove(observer);
    }

    @Override
    public void notifySubscribers()
    {
        observers.forEach(observer -> observer.updateValue(getValue()));
    }
}