package mik.module5.opdracht2;

import java.util.Queue;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Robin Langerak
 */
public class KikQueueArrayList<Request> extends ArrayList<Request> implements List<Request> 
{
    public ArrayList<Request> kikQueue;

    
    public Request peek()
    {
        return kikQueue.get(0);
    }
    
    public Request element()
    {
        return kikQueue.get(0);
    }
    
    public Request poll()
    {
        Request result = kikQueue.get(0);
        int size = kikQueue.size();
        
        for(int index = 0; index <= size; index++)
        {
            kikQueue.set(index, kikQueue.get(index+1));
        }
        
        return result;
    }
    
    public Request remove()
    {
        Request result = kikQueue.get(0);
        int size = kikQueue.size();
        
        for(int index = 0; index <= size; index++)
        {
            kikQueue.set(index, kikQueue.get(index+1));
        }
        
        return result;
    }
    
    public boolean offer(Request element)
    {
        int size = kikQueue.size();
        
        kikQueue.set(size+1, element);
        
        return true;
    }
    
    public boolean add(Request element)
    {
        int size = kikQueue.size();
        
        kikQueue.set(size+1, element);
        
        return true;
    }
}
