package mik.module5.opdracht2c;

import com.horstmann.Stopwatch;
import java.util.*;

/** Runs timing experiments with various implementations of the Queue ADT.  */
 /* @author Robin Langerak */

public class DonorExperiments
{    
    /** Increment of number of requests */
    public static final int STEP_SIZE = 5000;
    
    /** Maximum number of requests */
    public static final int MAX_SIZE = 10000000;    
    
    public static void main(String[] args) 
    {
        //System.out.println("SIZE\t C\t\t N\t\t NLogN\t\t N2\t\t C\t\t N\t\t NLogN\t\t N2\t\t C\t\t N\t\t NLogN\t\t N2\t\t C\t\t N\t\t NLogN\t\t N2\t\t");
        /*for (int i = STEP_SIZE; i < MAX_SIZE; i += STEP_SIZE)
        {        
            int donorTime = runExperiment(i, new DonorArrayListQueue(), new DonorLinkedListQueue(), 1, 2);
            int donorTime2 = runExperiment(i, new DonorLinkedListQueue(), new DonorLinkedListQueue(), 2, 2);
            int donorTime3 = runExperiment(i, new DonorArrayListQueue(), new DonorArrayListQueue(), 1, 1);
            int donorTime4 = runExperiment(i, new DonorLinkedListQueue(), new DonorArrayListQueue(), 2, 1);
            System.out.println(i + reportRun(donorTime, i) + reportRun(donorTime2, i) + reportRun(donorTime3, i) + reportRun(donorTime4, i));
        }*/    
        System.out.println("Proportion A\t Proportion B\t Proportion 0\t Proportion AB\t # of Receivers left");
        for(int i = 0; i < 70; i++)
        {
            int propA, propB, prop0, propAB;
            
            propA = i;
            propB = 70-i;
            prop0 = 10;
            propAB = 20;
            
            System.out.println(propA + "\t\t" + propB + "\t\t" + prop0 + "\t\t" + propAB + "\t\t" + runProportionTest(1000, propA, propB, prop0, propAB));
        }
    }
    
    private static int runProportionTest(int maxNumber, int propA, int propB, int prop0, int propAB)
            //q1Type and q2Type are coded as follows: 1 is ArrayListQueue, 2 is LinkedListQueue
    {
        int average = 0;
        Patient newDonor, newReceiver;
        DonorArrayListQueue donors, receivers;
        
        for(int number = 0; number < maxNumber; number++)
        {
            donors = new DonorArrayListQueue();
            receivers = new DonorArrayListQueue();
            
            for(int i=0; i < 1000; i++)
            {
                addRandomPatient("Donor", 30, 30, 10, 20, donors);
                addRandomPatient("Receiver", propA, propB, prop0, propAB, receivers);
            }

            while(!donors.isEmpty())
            {
                newDonor = (Patient)donors.remove();
                newReceiver = null;
                newReceiver = newDonor.findMatchingReceiver((DonorArrayListQueue)receivers);
                if(newReceiver!=null)
                    receivers.remove(newReceiver);
            }
            
            average += receivers.size();
        }
        
        return average/maxNumber;
    }
    
    private static int runExperiment(int number, Queue<Patient> donors, Queue<Patient> listOfReceivers, int q1Type, int q2Type)
    //q1Type and q2Type are coded as follows: 1 is ArrayListQueue, 2 is LinkedListQueue
    {
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        StringBuilder results = new StringBuilder();
        Patient newDonor, newReceiver;
     
        for(int i=0; i < number; i++)
        {
            addRandomPatient("Donor", 30, 30, 10, 20, donors);
            addRandomPatient("Receiver", 30, 30, 10, 20, listOfReceivers);
        }
        
        while(!donors.isEmpty())
        {
            newDonor = donors.remove();
            newReceiver = null;
            if(q2Type==1)
                newReceiver = newDonor.findMatchingReceiver((DonorArrayListQueue)listOfReceivers);
            else if(q2Type==2)
                newReceiver = newDonor.findMatchingReceiver((DonorLinkedListQueue)listOfReceivers);
            listOfReceivers.remove(newReceiver);
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
        return String.format("%8d\t %1.6f\t %1.6f\t %1.6f\t ", runTime, divideN, divideNLogN, divideN2);                        
    }
    
    public static void addRandomPatient(String type, int percentageA, int percentageB, int percentage0, int percentageAB, Queue patients)
    {
        Random rand = new Random();
        Patient newDonor = new Patient((int)(rand.nextInt(9000) + 1000), "", type);

        int newBloodType = rand.nextInt(99) + 1;
        
        if(newBloodType < percentageA)
            newDonor.bloodType = "A";
        else if(newBloodType < percentageA + percentageB)
            newDonor.bloodType = "B";
        else if(newBloodType < percentageA + percentageB + percentage0)
            newDonor.bloodType = "0";
        else
            newDonor.bloodType = "AB";
        
        patients.add(newDonor);
    }
}   