package quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The pop-up GUI that shows up when the user wants to go to the next page of the assessment
 * but has not entered their name.
 * 
 * @author Jeremy Nguyen
 * @version 8-24-2022
 */
public class BlankName extends JFrame implements ActionListener{

    /**
     * The button to go back to the login page.
     */
    JButton back;
    
    /**
     * The creation of the warning that pops up when the user did not enter their name.
     */
    public BlankName() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel warning = new JLabel("Your name is blank! Re-enter to continue.");
        warning.setBounds(85, 40, 600, 30);
        warning.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
        add(warning);

        //back button to go back to login page
        back = new JButton("Back");
        back.setBounds(185, 100, 120, 25);
        back.addActionListener(this);
        add(back);

        setSize(500, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    /**
     * Checks if the user has clicked the "back" button where the user will be returned to the login page.
     * 
     * @param ae takes in any clicked button
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new Login();
        }
    }
    /**
     * The main method to open up the warning that the user has not entered their name.
     * 
     * @param args an array of command-line arguments for the application
     */
    public static void main (String[]args) {
        new BlankName();
    }
}
