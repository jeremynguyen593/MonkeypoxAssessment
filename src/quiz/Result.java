package quiz;

import java.util.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/**
 * The results page after the user has finished their assessment. This page contains a colored banner and message indicating 
 * how high their risk is of having Monkeypox.
 * 
 * @author Jeremy Nguyen
 * @version 8-25-2022
 */
public class Result extends JFrame implements ActionListener{

    /**
     * The name of the user.
     */
    private String name;
    /**
     * The ArrayList of the user's answers.
     */
    private ArrayList<String> userAnswers;
    /**
     * The amount of times the user has answered "Yes" to a quesiton.
     */
    private int counter = 0;
    /**
     * Buttons to either leave the program or go to a page with a more detailed result page.
     */
    JButton exit, fullResults;

    /**
     * The results page of the assessment.
     * 
     * @param name the name of the user
     * @param userAnswers the user's answers
     */
    public Result(String name, ArrayList<String> userAnswers) {
        this.name = name;
        this.userAnswers = userAnswers;

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(600, 425);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel header = new JLabel("Results of " + name, SwingConstants.CENTER);
        header.setBounds(-50, -25, 700, 200);
        header.setFont(new Font ("Bahnschrift", Font.BOLD, 40));
        add(header);
        
        for (int i = 0; i < userAnswers.size(); i++) {
            if (userAnswers.get(i).equals("Yes")) {
                counter++;
            }
        }
        if (counter == 3) {
            JLabel highRisk = new JLabel("High Risk", SwingConstants.CENTER);
            highRisk.setBounds(42, 120, 515, 125);
            highRisk.setForeground(Color.WHITE);
            highRisk.setBackground(Color.RED);
            highRisk.setOpaque(true);
            highRisk.setFont(new Font ("Jolly", Font.ITALIC, 50));
            add(highRisk);
        } else if (counter == 1 || counter == 2) {
            JLabel potentialRisk = new JLabel("At Risk", SwingConstants.CENTER);
            potentialRisk.setBounds(42, 120, 515, 125);
            potentialRisk.setForeground(Color.WHITE);
            potentialRisk.setBackground(Color.ORANGE);
            potentialRisk.setOpaque(true);
            potentialRisk.setFont(new Font ("Jolly", Font.ITALIC, 50));
            add(potentialRisk);
        } else {
            JLabel noRisk = new JLabel("No Risk", SwingConstants.CENTER);
            noRisk.setBounds(42, 120, 515, 125);
            noRisk.setForeground(Color.WHITE);
            noRisk.setBackground(Color.GREEN);
            noRisk.setOpaque(true);
            noRisk.setFont(new Font ("Jolly", Font.ITALIC, 50));
            add(noRisk);
        }

        exit = new JButton("Exit");
        exit.setBounds(37, 300, 120, 25);
        exit.addActionListener(this);
        add(exit);

        fullResults = new JButton("Full Results");
        fullResults.setBounds(445, 300, 120, 25);
        fullResults.addActionListener(this);
        add(fullResults);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    
    }
    /**
     * Checks which buttons has been clicked to redirect the user.
     * 
     * @param ae checks if a button has been clicked
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fullResults) {
            new FullResults(getUserAnswers(), getCounter());
        } else if (ae.getSource() == exit){
            System.exit(0);
        }
    }
    /**
     * Gets the name of the user.
     * 
     * @return the name of the user
     */
    public String getName() {
        return name;
    }
    /**
     * Returns an array of strings of the user's answers.
     * 
     * @return an array of the user's answers
     */
    public ArrayList<String> getUserAnswers() {
        return userAnswers;
    }
    /**
     * Retrieves the number of times that the user answered "Yes".
     * 
     * @return the times the user answered "Yes"
     */
    public int getCounter() {
        return counter;
    }
    /**
     * The main method of the classes to open up the results page.
     * 
     * @param args an array of command-line arguments for the application
     */
    public static void main (String[]args) {
        ArrayList<String> holder = new ArrayList<String>();
        new Result("User", holder);
    }
}
