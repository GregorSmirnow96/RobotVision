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
public interface IObserver
{
    public void subscribe(ISubject subject);
    
    public void unscubscribe(ISubject subject);
    
    public void updateValue(int newValue);
}