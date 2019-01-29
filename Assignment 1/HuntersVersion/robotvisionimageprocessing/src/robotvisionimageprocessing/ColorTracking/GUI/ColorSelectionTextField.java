/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing.ColorTracking.GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JTextField;
import robotvisionimageprocessing.GlobalUtilities.PatternInterfaces.IObserver;
import robotvisionimageprocessing.GlobalUtilities.PatternInterfaces.ISubject;

/**
 *
 * @author im5no
 */
public class ColorSelectionTextField
    extends JTextField
    implements IObserver, ISubject
{
    private final Collection<IObserver> observers;
    private ISubject observedSubject;
    private String previousValue = "";
    
    public ColorSelectionTextField()
    {
        this.observers = new ArrayList<>();
        this.addActionListener(this::updateSlider);
        
        setPreferredSize(new Dimension(40, 22));
    }
    
    private void updateSlider(ActionEvent e)
    {
        notifySubscribers();
    }

    @Override
    public void subscribe(ISubject subject)
    {
        if (observedSubject != null)
            observedSubject.unregister(this);
            
        subject.register(this);
        observedSubject = subject;
    }

    @Override
    public void unscubscribe(ISubject subject)
    {
        subject.unregister(this);
    }

    @Override
    public void updateValue(int newValue)
    {
        String newValueAsString = ((Integer) newValue).toString();
        this.setText(newValueAsString);
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
        
        if (previousValue.equals(getText()))
            observers.forEach(
                observer -> observer
                    .updateValue(
                        Integer
                            .parseInt(getText())));
        
        previousValue = getText();
    }
}