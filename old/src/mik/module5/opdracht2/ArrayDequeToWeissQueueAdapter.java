package mik.module5.opdracht2;

import java.util.ArrayDeque;
import weiss.nonstandard.Queue;

/** Adapter class that adapts java.util.ArrayDeque to the weiss.nonstandard.Queue interface */  
public class ArrayDequeToWeissQueueAdapter<AnyType> implements Queue<AnyType>
{    
    private ArrayDeque<AnyType> queue;      
    
    public ArrayDequeToWeissQueueAdapter() 
    {
        queue = new ArrayDeque<>();
    }

    @Override
    public AnyType dequeue()
    {
        return queue.remove();
    }

    @Override
    public void enqueue(AnyType element)
    {
        queue.addLast(element);
        
    }

    @Override
    public AnyType getFront()
    {
        return queue.peekFirst();
    }

    @Override
    public boolean isEmpty()
    {
        return queue.isEmpty();
    }

    @Override
    public void makeEmpty()
    {
        queue.clear();        
    }
        
}