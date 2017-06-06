package mik.module5.opdracht2;

/** Driver voor Kikfonds */
 /* @author Robin Langerak */
public class Kik2015
{    
    /** Montly deposit sum */   
    public static final int DEPOSIT = 500;
    
    public static void main(String[] args)
    {
        StringBuilder results = new StringBuilder();
        Kikfonds fund = new Kikfonds(DEPOSIT);
        
        // jan
        results.append("\n\nResults for January:\n\n");
        fund.nextMonth(results);
        fund.grantRequests(results);
    
        // feb
        results.append("\nResults for February:\n\n");
        fund.nextMonth(results);
        fund.grantRequests(results);
        
        // mar
        results.append("\nResults for March:\n\n");
        fund.nextMonth(results);
        fund.grantRequests(results);
                
        // apr
        results.append("\nResults for April:\n\n");
        fund.addRequest(results, "espressoapparaat", 1600);
        fund.nextMonth(results);
        fund.grantRequests(results);
        
        // may         
        results.append("\nResults for May:\n\n");
        fund.addRequest(results, "laptop", 2000);
        fund.nextMonth(results);
        fund.grantRequests(results);
        
        // jun
        results.append("\nResults for June:\n\n");
        fund.nextMonth(results);
        fund.grantRequests(results);
        
        // jul
        results.append("\nResults for July:\n\n");
        fund.nextMonth(results);
        fund.grantRequests(results);
        
        // aug
        results.append("\nResults for August:\n\n");
        fund.addRequest(results,"beamer", 1100);
        fund.nextMonth(results);
        fund.grantRequests(results);
        
        // sep
        results.append("\nResults for September:\n\n");
        fund.addRequest(results, "stoelen", 4000);
        fund.nextMonth(results);
        fund.grantRequests(results);
                    
        // oct
        results.append("\n\nResults for October:\n\n");
        fund.nextMonth(results);
        fund.grantRequests(results);
        
        // nov
        results.append("\nResults for November:\n\n");
        fund.nextMonth(results);
        fund.grantRequests(results);
        
        // dec
        results.append("\nResults for December:\n\n");
        fund.addRequest(results, "kerstborrel", 200);
        fund.grantRequests(results);
        
        System.out.println("Pending requests:");
        System.out.println(results.toString());
        for (Request pendingRequest : fund) System.out.println(pendingRequest.getDescription());               
        
        System.out.println("Highest balance: " + fund.getHighestBalance());
        System.out.println("Highest deficit: " + fund.getHighestDeficit());        
    }        

}
