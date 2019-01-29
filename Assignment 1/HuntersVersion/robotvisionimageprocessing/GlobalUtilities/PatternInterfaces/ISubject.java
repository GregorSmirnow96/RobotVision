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
public interface ISubject <TObservered>
{
    public void register(IObserver<TObservered> observer);
    
    public void unregister(IObserver<TObservered> observer);
    
    public void notifySubscribers();
}