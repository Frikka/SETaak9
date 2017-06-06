package mik.module5.opdracht2;

/** Driver voor Kikfonds */
public class Kik2013
{    
    /** Montly deposit sum */   
    public static final int DEPOSIT = 500;
    
    public static void main(String[] args)
    {        
        // jan
        Kikfonds fund = new Kikfonds(DEPOSIT);
        fund.nextMonth();
    
        // feb
        fund.nextMonth();
        
        // mar
        fund.nextMonth();
                
        // apr
        fund.addRequest("espressoapparaat", 1600);
        System.out.println(fund.grantRequests());
        fund.nextMonth();
        
        // may                
        fund.addRequest("laptop", 2000);
        System.out.println(fund.grantRequests());
        fund.nextMonth();
        
        // jun
        System.out.println(fund.grantRequests());
        fund.nextMonth();
        
        // jul
        System.out.println(fund.grantRequests());
        fund.nextMonth();
        
        // aug
        fund.addRequest("beamer", 1100);
        System.out.println(fund.grantRequests());
        fund.nextMonth();
        
        // sep
        fund.addRequest("stoelen", 4000);
        System.out.println(fund.grantRequests());
        fund.nextMonth();
                    
        // oct
        System.out.println(fund.grantRequests());
        fund.nextMonth();
        
        // nov
        System.out.println(fund.grantRequests());
        fund.nextMonth();
        
        // dec
        System.out.println(fund.grantRequests());
        fund.addRequest("kerstborrel", 200);
        
        System.out.println("Pending requests:");
        for (Request pendingRequest : fund) System.out.println(pendingRequest.getDescription());               
        
        System.out.println("Highest balance: " + fund.getHighestBalance());
        System.out.println("Highest deficit: " + fund.getHighestDeficit());        
    }        

}
