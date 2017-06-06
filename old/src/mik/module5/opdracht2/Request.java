package mik.module5.opdracht2;

/** Represents a request with its cost.
 * @author Robin Langerak
 */
public class Request
{    
    private String description;
    private int amount;

    public Request(String description, int amount)
    {
        this.description = description;
        this.amount = amount;            
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public int getAmount()
    {
        return amount;            
    }        
                       
}