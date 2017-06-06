package mik.module5.opdracht2;

import com.horstmann.Stopwatch;
import weiss.nonstandard.ListQueue;
import weiss.nonstandard.Queue;

/** Runs timing experiments with various implementations of the Queue ADT.  */
public class KikfondsExperiments
{
    /** Montly deposit sum */   
    public static final int DEPOSIT = 100;
    
    /** Increment of number of requests */
    public static final int STEP_SIZE = 5000;
    
    /** Maximum number of requests */
    public static final int MAX_SIZE = 100000;    
    
    public static void main(String[] args) 
    {
        System.out.println("SIZE\t LIST\t LIST/N\t\t LIST/N2\t LIST/NLOGN\t DEQUE\t\t DEQUE/N\t DEQUE/N2\t DEQUE/NLOGN\t LINKED\t\t LINKED/N\t LINKED/N2\t LINKED/NLOGN\t");
        for (int i = STEP_SIZE; i < MAX_SIZE; i += STEP_SIZE)
        {        
            int listTime = runExperiment(i, new ListQueue<Request>());
            int dequeTime = runExperiment(i, new ArrayDequeToWeissQueueAdapter<Request>());
            int linkedTime = runExperiment(i, new LinkedListToWeissQueueAdapter<Request>());
            
            System.out.println(i + reportRun(listTime, i) + reportRun(dequeTime, i) + reportRun(linkedTime, i));
        }        
    }
    
    /** Measures the running time of an experiment: Enters each month an request in a KIKfund, 
     * then each month grants requests until the fund is empty.  
     * @param title description of the experiment
     * @param numberOfRequests number of requests to be entered in the fund
     * @param queue Instantiation of the class to be used as a queue for the fund
     * @return the running time of the experiment in milliseconds
     */
    private static int runExperiment(int numberOfRequests, Queue<Request> queue)
    {
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
     
        // Fill fund
        Kikfonds fund = new Kikfonds(DEPOSIT, queue);
        for (int i = 0; i < numberOfRequests; i++)
        {
            fund.addRequest("request" + i, i * 100);
            fund.nextMonth();
        }
        
        // Empty fund
        while (fund.hasAnyRequest()) 
        {
            fund.grantRequests();
            fund.nextMonth();
        }
        
        stopwatch.stop();
        int time = (int) (stopwatch.getElapsedTime());
        
        return time;
    }
        
    private static String reportRun(int runTime, int n)
    {   
        double divideN = runTime / (double) n;
        double divideN2 = runTime / ((double) n * n);
        double divideNLogN = runTime / (n * Math.log(n) / Math.log(2));
        return String.format("%8d\t %1.6f\t %1.6f\t %1.6f\t ", runTime, divideN, divideN2, divideNLogN);                        
    }
    
}   