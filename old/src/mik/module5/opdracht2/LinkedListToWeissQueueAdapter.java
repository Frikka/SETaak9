package mik.module5.opdracht2;

import java.util.LinkedList;
import weiss.nonstandard.Queue;

/** Adapter class that adapts java.util.LinkedList to the weiss.nonstandard.Queue interface */
public class LinkedListToWeissQueueAdapter<AnyType> implements Queue<AnyType> 
{   
    private LinkedList<AnyType> queue;
    
    public LinkedListToWeissQueueAdapter()
    {
        queue = new LinkedList<>();
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
