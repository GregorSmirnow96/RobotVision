/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing.GlobalUtilities.PatternInterfaces;

/**
 *
 * @author im5no
 * @param <TObservered>
 */
public interface IObserver <TObservered>
{
    public void subscribe(ISubject<TObservered> subject);
    
    public void unscubscribe(ISubject<TObservered> subject);
    
    public void updateValue(TObservered newValue);
}