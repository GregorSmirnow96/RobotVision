/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing.GlobalUtilities.PatternInterfaces;

/**
 *
 * @author im5no
 */
public interface ISubject
{
    public void register(IObserver observer);
    
    public void unregister(IObserver observer);
    
    public void notifySubscribers();
}