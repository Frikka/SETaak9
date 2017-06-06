package mik.module5.opdracht2;

import java.util.Iterator;
import weiss.nonstandard.Queue;

/** Class representing the KIK fund (KIK-fonds). Each month a monthly deposit is added to the 
 * fund's balance. Requests can be submitted to the fund, but will be dealt with in a strict FIFO order. 
 * @author FJWiesman
 */
public class Kikfonds implements Iterable<Request>
{
    private int monthlyDeposit;      
    private int highestBalance;  // highest balance ever    
    private int highestDeficit;  // highest difference between totalCosts and balance
    private int balance;         // available money in fund
    private int totalCosts;      // costs of all remaining requests 
    private Queue<Request> requests;        
    
    /** Creates a new fund with a default implementation. 
     * @param monthlyDeposit the amount that is added to the fund each month
     */
    public Kikfonds(int monthlyDeposit) 
    {
        this(monthlyDeposit, new ArrayDequeToWeissQueueAdapter<Request>());
    }

    /** Creates a new fund. 
     * @param monthlyDeposit the amount that is added to the fund each month
     * @param requests the queue that is used to store requests (provided such that different implementations can be used)
     */
    Kikfonds(int monthlyDeposit, Queue<Request> requests) 
    {
        this.monthlyDeposit = monthlyDeposit;
        this.requests = requests;
        
        balance = totalCosts = 0;
        highestBalance = highestDeficit = 0;        
    }
        
    /** Adds a new request to the fund.     
     * @param request description of the request
     * @param amount cost of the request
     */
    public void addRequest(String request, int amount) 
    {
        requests.enqueue(new Request(request, amount));
        totalCosts += amount;
        if (totalCosts - balance > highestDeficit) highestDeficit = totalCosts - balance;        
    }
    
    /** Returns true iff there are pending requests. */
    public boolean hasAnyRequest()
    {
        return !requests.isEmpty();
    }
    
    /** Grants all requests for this month for which there are sufficient funds
     * @return Descriptions of all granted requests (or a message that no requests were granted). */ 
    public String grantRequests()
    {                
        if (requests.isEmpty()) return "no requests left";
        
        // Dequeue requests as long as there are requests and suffient funds 
        StringBuilder results = new StringBuilder();               
        while (!requests.isEmpty() && requests.getFront().getAmount() <= balance)
        {
            grantRequest(results);
        }
        
        return results.length() == 0 ? "no requests granted" : results.toString();
    }

    private void grantRequest(StringBuilder results) {
        Request next = requests.getFront();
        requests.dequeue();                
        balance -= next.getAmount();
        totalCosts -= next.getAmount();
        results.append("granted " + next.getDescription() + '\n');
    }
    
    /** Activates a new month  */
    public void nextMonth() 
    {
        balance += monthlyDeposit;                
        if (balance > highestBalance) highestBalance = balance;
    }
    
    /** Returns the highest amount of available money ever of this fund */
    public int getHighestBalance()
    {
        return highestBalance;
    }
    
    /** Returns the highest difference between costs of all requests and balance ever of this fund (a positive number). */
    public int getHighestDeficit()
    {
        return highestDeficit;
    }

    /** Returns an iterator over all requests in this fund. 
     * WARNING: THIS ITERATOR IS DESTRUCTIVE, i.e., each iteration step removes the request concerned! 
     * In principle, iterators must not be destructive, but the Queue interface does not allow a proper 
     * solution.  */
    @Override
    public Iterator<Request> iterator()
    {        
        return new KikFondsIterator();
    }                          
    
    private class KikFondsIterator implements Iterator<Request>
    {
        @Override
        public boolean hasNext()
        {
            return !requests.isEmpty();
        }

        @Override
        public Request next()
        {
            return requests.dequeue();
        }

        @Override
        public void remove()
        {
            requests.dequeue();            
        }                        
    } // KikFondsIterator
                 
    
}