package quiz;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

/**
 * The disclaimer page of what the assessment is about and where the data is taken from.
 * 
 * @author Jeremy Nguyen
 * @version 8-24-2022
 */
public class Disclaimer extends JFrame implements ActionListener{
    /**
     * The name of the user.
     */
    static private String name;
    /**
     * The buttons to either begin the assessment or go back to the login page.
     */
    JButton start, back;
    
    /**
     * Opens up the disclaimer page GUI.
     * 
     * @param name the name of the user 
     */

    public Disclaimer(String name) {
        //keeps the name of the user
        this.name = name;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        //title
        JLabel header = new JLabel("DISCLAIMER");
        header.setBounds(165, -25, 700, 200);
        header.setFont(new Font ("Bahnschrift", Font.BOLD, 75));
        add(header);
        
        //the disclaimer info
        JLabel content = new JLabel();
        content.setBounds(30, 100, 700, 350);
        content.setFont(new Font("Tahoma", Font.PLAIN, 16));
        content.setText("<html>" + 
                "1. The following assessment is not medically proven to diagnose you." + "<br><br>" +
                "2. Should you suspect that you do in fact have Monkeypox, seek out a medical professional." + "<br><br>" +
                "3. The following assessment uses current* medical research on the disease and may be prone to change." + "<br><br>" +
                "4. For more information about the disease, visit the Centers for Disease Control and Prevention website at https://www.cdc.gov/poxvirus/monkeypox/index.html." +"<br><br>" +
                "5. With these in mind, let us begin the assessment." + "<br><br>" +
                "<html>");
        add(content);
        
        //the asterisk portion of the disclaimer
        JLabel asterisk = new JLabel();
        asterisk.setBounds(30, 250, 700, 350);
        asterisk.setFont(new Font("Tahoma", Font.PLAIN, 10));
        asterisk.setText("<html>" + 
                 "*data is from August 5th, 2022" +
                "<html>");
        add(asterisk);
        
        start = new JButton("Start");
        start.setBounds(400, 500, 100, 30);
        start.addActionListener(this);
        add(start);
       
        back = new JButton("Back");
        back.setBounds(250, 500, 100, 30);
        back.addActionListener(this);
        add(back);
        
        setSize(800, 650);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    /**
     * Checks which button has been clicked and then redirects them either back to the login page or to the assessment.
     * 
     * @param ae checks if a button as been pressed
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == start) {
            setVisible(false);
            new Assessment(getName());
        } else {
            setVisible(false);
            new Login();
        }
    }
    /**
     * Gets the name of the user
     * 
     * @return the name of the user
     */
    public String getName() {
        return name;
    }
    /**
     * The main method where the disclaimer page is opened up and stores the name of the user.
     * 
     * @param args an array of command-line arguments for the application
     */
    public static void main (String[]args) {
        new Disclaimer("User");
    }
}
