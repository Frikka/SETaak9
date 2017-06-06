package mik.module5.opdracht2c;

/** Represents a donor with its blood type
 * @author Robin Langerak
 */

public class Patient
{    
    public String bloodType;
    public int donorNr;
    public String patientType;

    public Patient()
    {
    }
    
    public Patient(int newNr, String newBloodType, String newPatientType)
    {
        this.bloodType = newBloodType;
        this.donorNr = newNr;
        this.patientType = newPatientType;
    }
    
    public String getBloodType()
    {
        return bloodType;
    }
    
    public int getNr()
    {
        return donorNr;            
    }
    
    public String getPatientType()
    {
       return patientType;         
    }
   
    public boolean isMatchingReceiver(Patient target)
    {
        if(this.getPatientType() != "Donor" || target.getPatientType() != "Receiver")
            return false;
        
        switch(target.getBloodType())
        {
            case "0":
                if(this.bloodType.equals("0"))
                    return true;
                else
                    return false;
            case "AB":
                if(this.bloodType.equals("A") || this.bloodType.equals("B") || this.bloodType.equals("AB") || this.bloodType.equals("0"))
                    return true;
                else
                    return false;
            case "A":
                if(this.bloodType.equals("0") || this.bloodType.equals("A"))
                    return true;
                else
                    return false;
            case "B":
                if(this.bloodType.equals("0") || this.bloodType.equals("B"))
                    return true;
                else
                    return false;
        }
        
        return true;
    }
    
    public boolean isMatchingDonor(Patient target)
    {
        if(this.getPatientType() != "Receiver" || target.getPatientType() != "Donor")
            return false;
        
        switch(target.getBloodType())
        {
            case "0":
                if(this.bloodType.equals("AB") || this.bloodType.equals("B") || this.bloodType.equals("AB") || this.bloodType.equals("0"))
                    return true;
                else
                    return false;
            case "AB":
                if(this.bloodType.equals("AB"))
                    return true;
                else
                    return false;
            case "A":
                if(this.bloodType.equals("AB") || this.bloodType.equals("A"))
                    return true;
                else
                    return false;
            case "B":
                if(this.bloodType.equals("AB") || this.bloodType.equals("B"))
                    return true;
                else
                    return false;
        }
        
        return false;
    }
    
    /** Returns the first receiver in the given queue that matches the donor's bloodtype*/
    public Patient findMatchingReceiver(DonorArrayListQueue listOfReceivers)
    {
        DonorArrayListQueue tempQueue = (DonorArrayListQueue) listOfReceivers.clone();

        while(!tempQueue.isEmpty())
        {
            Patient newReceiver = (Patient) tempQueue.remove(0);

            if(this.isMatchingReceiver(newReceiver))
                return newReceiver;
        }

        return null;
    }
    
    public Patient findMatchingReceiver(DonorLinkedListQueue listOfReceivers)
    {
        DonorLinkedListQueue tempQueue = (DonorLinkedListQueue) listOfReceivers.clone();

        while(!tempQueue.isEmpty())
        {
            Patient newReceiver = (Patient) tempQueue.remove(0);

            if(this.isMatchingReceiver(newReceiver))
                return newReceiver;
        }

        return null;
    }
}