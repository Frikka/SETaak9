package mik.module5.opdracht2c;

import java.util.Queue;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Robin Langerak
 */
public class DonorArrayListQueue<Patient> extends ArrayList<Patient> implements Queue<Patient>
{
    public DonorArrayListQueue()
    {  
    }
    
    @Override
    public Patient peek()
    {
        return get(0);
    }
    
    @Override
    public Patient element()
    {
        if(size() == 0)
            return null;
        else
            return get(0);
    }
    
    @Override
    public Patient poll()
    {
        if(size() == 0)
            return null;
        else
            return remove(0);
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