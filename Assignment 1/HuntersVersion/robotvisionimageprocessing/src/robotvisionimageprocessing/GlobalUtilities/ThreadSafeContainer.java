/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotvisionimageprocessing.GlobalUtilities;

import robotvisionimageprocessing.GlobalUtilities.FunctionalInterfaces.ITriggeredEvent;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author im5no
 * @param <TContained>
 */
public class ThreadSafeContainer <TContained>
{
    /* The variable that is being made threadsafe. */
    private TContained value;
    /* An object that is locked while a thread uses the contained value.
     * This prevents other threads from using the same resource at the
     * same time
     */
    private final ReentrantLock lock;
    
    public ThreadSafeContainer()
    {
        this.lock = new ReentrantLock();
    }
    
    /**
     * @summary
     *  A getter for the contained value.
     * @return
     *  Returns the contained variable.
     */
    public TContained getValue()
    {
        return value;
    }
    
    /**
     * @summary
     *  Sets the value of the contained variable.
     * @param newValue
     *  The new value to be contained by this class.
     */
    public void setValue(TContained newValue)
    {
        value = newValue;
    }
    
    /**
     * @summary
     *  Executes a lambda expression with the ReentrantLock locked, then
     *  unlocks it after the code is complete. This code may interact with
     *  this class' contained value. Since the ReentrantLock is locked while
     *  the lambda expression is being executed, the use of this class'
     *  contained value is thread-safe.
     * @param asyncMethod
     *  The code being executed asynchronously.
     */
    public void executeCodeAsync(ITriggeredEvent asyncMethod)
    {
        lock.lock();
        
        try
        {
            asyncMethod.invokeEvent();
        }
        finally
        {
            lock.unlock();
        }
    }
}