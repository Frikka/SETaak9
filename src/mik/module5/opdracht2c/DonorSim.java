package mik.module5.opdracht2c;

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;

/** Class representing the Donor Matching application
 * @author Robin Langerak
 */
public class DonorSim extends JPanel implements ActionListener
{
    static private final String newline = "\n";
    static private String donorFileName="", receiverFileName="";
    JButton openDonorFileButton, openReceiverFileButton, runSimulationButton;
    JTextArea log;
    
    private DonorArrayListQueue<Patient> listOfDonors;
    private DonorLinkedListQueue<Patient> listOfReceivers;
    JFileChooser donorChooser, receiverChooser;
    
    /** Creates new queues. 
     * @param donorFileName the name of the file that contains all donors
     * @param receiverQueue the name of the file that contains all receivers
     */
    public DonorSim()
    {
        super(new BorderLayout());

        //Create the log first, because the action listeners
        //need to refer to it.
        log = new JTextArea(20,50);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        donorChooser = new JFileChooser();
        receiverChooser = new JFileChooser();

        openDonorFileButton = new JButton("Open donor File...");
        openDonorFileButton.addActionListener(this);

        openReceiverFileButton = new JButton("Open receiver File...");
        openReceiverFileButton.addActionListener(this);
        
        runSimulationButton = new JButton("Run simulation");
        runSimulationButton.addActionListener(this);

        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openDonorFileButton);
        buttonPanel.add(openReceiverFileButton);
        
        JPanel simPanel = new JPanel();
        simPanel.add(runSimulationButton);

        //Add the buttons and the log to this panel.
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
        add(simPanel, BorderLayout.PAGE_END);
        
        listOfDonors = new DonorArrayListQueue();
        listOfReceivers = new DonorLinkedListQueue();
    }
    
    public void actionPerformed(ActionEvent e)
    {
        Patient newDonor, newReceiver;
        
        //Handle open donor button action.
        if (e.getSource() == openDonorFileButton)
        {
            int returnVal = donorChooser.showOpenDialog(DonorSim.this);

            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                File file = donorChooser.getSelectedFile();
                //This is where a real application would open the file.
                log.append(file.getName() + " opened." + newline);
                donorFileName = file.getName();
                readDonors(donorFileName);
            }
            else
            {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());

        //Handle open receiver button action.
        }
        else if (e.getSource() == openReceiverFileButton)
        {
            int returnVal = receiverChooser.showOpenDialog(DonorSim.this);
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                File file = receiverChooser.getSelectedFile();
                //This is where a real application would save the file.
                log.append(file.getName() + " opened." + newline);
                receiverFileName = file.getName();
                readReceivers(receiverFileName);
            }
            else
            {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
        else if (e.getSource() == runSimulationButton)
        {
            //Check if donors loaded
            if(listOfDonors.isEmpty())
            {
                log.append("Simulation cancelled: Donors not loaded" + newline);
                return;
            }
            
            //Check if receivers loaded
            if(listOfReceivers.isEmpty())
            {
                log.append("Simulation cancelled: Receivers not loaded" + newline);
                return;
            }
            
            //While list of donors !empty, try to find a match
            while(!listOfDonors.isEmpty())
            {
                newDonor = listOfDonors.remove(0);
                newReceiver = newDonor.findMatchingReceiver(listOfReceivers);
                if(newReceiver==null)
                    log.append("No match could be found for donor #" + newDonor.getNr() + newline);
                else
                {
                    listOfReceivers.remove(newReceiver);
                    log.append("Receiver #" + newReceiver.getNr() + " with blood type " + newReceiver.getBloodType() + " has been matched to donor #"
                                           + newDonor.getNr() + " with blood type " + newDonor.getBloodType() + newline);
                }
            }
            
            log.append(newline + listOfReceivers.size() + " people still waiting for donors");
        }
    }
    
    private boolean readReceivers(String filename)
    {
        String newLine;
        BufferedReader receiverReader;
                
        try
        {
            receiverReader = new BufferedReader(new FileReader(filename));
        
            while ((newLine = receiverReader.readLine()) != null)
            {
                String[] receiverInfo = newLine.split(";");
                Patient newReceiver = new Patient(Integer.parseInt(receiverInfo[0]), receiverInfo[1], "Receiver");
                listOfReceivers.add(newReceiver);
            }
        
            receiverReader.close();
        }
        catch(IOException e)
        {
            return false;
        }
        return true;
    }
    
    private boolean readDonors(String filename)
    {
        String nextLine;
        BufferedReader donorReader;
                
        try
        {
            donorReader = new BufferedReader(new FileReader(filename));
        
            while ((nextLine = donorReader.readLine()) != null)
            {
                String[] donorInfo = nextLine.split(";");
                Patient newDonor = new Patient(Integer.parseInt(donorInfo[0]), donorInfo[1], "Donor");
                listOfDonors.add(newDonor);
            }
  
            donorReader.close();
        }
        catch(IOException e)
        {
            return false;
        }
        return true;
    }
            
            
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = DonorSim.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    private static void createAndShowGUI()
    {
        //Create and set up the window.
        JFrame frame = new JFrame("DonorSimulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new DonorSim());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE); 
                createAndShowGUI();
            }
        });
    }        
    
    /** Returns true iff there are unprocessed donors. */
    public boolean donorsAvailable()
    {
        return !listOfDonors.isEmpty();
    }
}