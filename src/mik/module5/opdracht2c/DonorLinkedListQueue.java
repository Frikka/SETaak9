package mik.module5.opdracht2c;

import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Robin Langerak
 */
public class DonorLinkedListQueue<Patient> extends LinkedList<Patient> implements Queue<Patient>
{
    public DonorLinkedListQueue()
    {  
    }
    
    @Override
    public Patient peek()
    {
        return getFirst();
    }
    
    @Override
    public Patient element()
    {
        if(size() == 0)
            return null;
        else
            return getFirst();
    }
    
    @Override
    public Patient poll()
    {
        if(size() == 0)
            return null;
        else
            return removeFirst();
    }
    
    @Override
    public Patient remove()
    {
        return remove(0);
    }
    
    @Override
    public boolean offer(Patient newPatient)
    {        
        try
        {
            add(newPatient);
        }
        catch(IndexOutOfBoundsException e)
        {
            return false;
        }
        return true;
    }
}