package quiz;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The page that gives the user a more detailed result of their likelihood of having Monkeypox, recommended course of
 * action, and additional information that may be useful to the user.
 * 
 * @author Jeremy Nguyen
 * @version 8-25-2022
 */
public class FullResults extends JFrame implements ActionListener {
    /**
     * The array of strings of the user's answers.
     */
    private ArrayList<String> userAnswers = new ArrayList<>();
    /**
     * The counter for the amount of times the user answered "Yes".
     */
    private int counter = 0;
    /**
     * An exit button for the user to leave the program.
     */
    JButton exit;
    /**
     * The label with the more in-depth description of the user's results.
     */
    JLabel answer;

    /**
     * Opens up the full results page of the user.
     * 
     * @param userAnswers the list of answers that the user gave
     * @param counter the amount of times the user answered "Yes" in the assessment
     */
    public FullResults(ArrayList<String> userAnswers, int counter) {
        this.userAnswers = userAnswers;
        this.counter = counter;

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(600, 650);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel header = new JLabel("Full Results", SwingConstants.CENTER);
        header.setBounds(-50, -25, 700, 200);
        header.setFont(new Font ("Bahnschrift", Font.BOLD, 40));
        add(header);
        
        exit = new JButton("Exit");

        if (counter == 3) {
            exit.setBounds(230, 300, 120, 25);
            setSize(600, 425);
            setLocationRelativeTo(null);
            JLabel answer = new JLabel("<html>" + "You chose \"Yes\" to all questions meaning you are most " +
            "likely to be infected with the Monkeypox virus. It is highly recommended to quarantine yourself " + 
            "and consult a medical professional. For more information visit https://www.cdc.gov/poxvirus/monkeypox/index.html." + "<html>");
            answer.setBounds(25, -70, 550, 500);
            answer.setFont(new Font("Open Sans", Font.PLAIN, 20));
            add(answer);
        } else if (counter == 0) {
            exit.setBounds(230, 300, 120, 25);
            setSize(600, 425);
            setLocationRelativeTo(null);
            JLabel answer = new JLabel("<html>" + "You chose \"No\" to all question meaning you are very " + 
            "unlikely to have been infected with the Monkeypox virus. If you believe you have contracted the virus, " +
            "contact a medical professional and avoid contact with others. For more information visit https://www.cdc.gov/poxvirus/monkeypox/index.html." + "<html>");
            answer.setBounds(25, -60, 550, 500);
            answer.setFont(new Font("Open Sans", Font.PLAIN, 20));
            add(answer);
        } else if (counter == 1) {
            if (getSpecificUserAnswers(0).equals("Yes")) {
                exit.setBounds(230, 450, 120, 25);
                setSize(600, 550);
                setLocationRelativeTo(null);
                JLabel answer = new JLabel("<html>" + "You only chose \"Yes\" to \"Have you been in close, personal, and/or skin-to-skin contact "  +
                "with someone you know or suspect that has Monkeypox?\". Since symptoms, after being infected, can take up to 3 weeks to show, "
                + "it is highly recommended that you quarantine yourself and contact a medical professional to test if you have contracted the virus. "
                + "Currently, it is unknown if those who are asymptomatic can spread the virus so wait to interact with others until you test negative. " 
                + "For more information visit https://www.cdc.gov/poxvirus/monkeypox/index.html." + "<html>");
                answer.setBounds(25, 0, 550, 500);
                answer.setFont(new Font("Open Sans", Font.PLAIN, 20));
                add(answer);
            } else if (getSpecificUserAnswers(1).equals("Yes")) {
                exit.setBounds(230, 450, 120, 25);
                setSize(600, 550);
                setLocationRelativeTo(null);
                JLabel answer = new JLabel("<html>" + "You only chose \"Yes\" to \"Do you have flu-like symptoms or symptoms of the following: " +
                "Fever, Chills, Swollen Lymph Nodes, Exhuastion, Muscle Aches "  +
                "and Backache, Headache, and/or Respiratory Symptoms (e.g. sore throat, nasal congestion, or cough)?\". Since you do " +
                "exhibit symptoms that can be for Monkeypox, it is highly recommended that you quarantine yourself and consult a medical "
                + "professional to identify what illness you may have as other viruses like COVID-19 and the flu exhibit similar symptoms. "
                + "For more information visit https://www.cdc.gov/poxvirus/monkeypox/index.html." + "<html>");
                answer.setBounds(25, 0, 550, 500);
                answer.setFont(new Font("Open Sans", Font.PLAIN, 20));
                add(answer);
            } else if (getSpecificUserAnswers(2).equals("Yes")) {
                exit.setBounds(230, 450, 120, 25);
                setSize(600, 550);
                setLocationRelativeTo(null);
                JLabel answer = new JLabel("<html>" + "You only chose \"Yes\" to \"Do you have a rash that looks like pimples or blisters that may be painful or itchy?\" " +
                "Even though you may not know if you came into contact with a carrier, it is highly possible that you do have Monkeypox. Some people may exhibit a rash as the only symptoms " +
                "while some may have flu-like symptoms and a rash. It is highly recommended to quarantine and seek out a medical professional to confirm it is Monkeypox. "
                + "For more information visit https://www.cdc.gov/poxvirus/monkeypox/index.html." + "<html>");
                answer.setBounds(25, 0, 550, 500);
                answer.setFont(new Font("Open Sans", Font.PLAIN, 20));
                add(answer);
            }
        } else if (counter == 2) {
            if (getSpecificUserAnswers(0).equals("Yes") && getSpecificUserAnswers(1).equals("Yes")) {
                exit.setBounds(230, 550, 120, 25);
                JLabel answer = new JLabel("<html>" + "You chose \"Yes\" to \"Have you been in close, personal, and/or skin-to-skin contact " +
                "with someone you know or suspect that has Monkeypox?\" and \"Do you have flu-like symptoms or symptoms of the following: " +
                "Fever, Chills, Swollen Lymph Nodes, Exhuastion, Muscle Aches "  +
                "and Backache, Headache, and/or Respiratory Symptoms (e.g. sore throat, nasal congestion, or cough)?\". " +
                "Since you have been in contact with someone carrying the virus and you are now exhibiting symptoms, it is very likely " +
                "that you are now infected with Monkeypox. Study shows that, if you are infected with Monkeypox and show flu-like symptoms, you will develop a rash " +
                "1-4 days later. It is recommended to quarantine yourself and consult a medical professional to confirm whether you have Monkeypox. "+
                "For more information visit https://www.cdc.gov/poxvirus/monkeypox/index.html." + "<html>");
                answer.setBounds(25, 50, 550, 500);
                answer.setFont(new Font("Open Sans", Font.PLAIN, 20));
                add(answer);
            } else if (getSpecificUserAnswers(0).equals("Yes") && getSpecificUserAnswers(2).equals("Yes")) {
                exit.setBounds(230, 520, 120, 25);
                JLabel answer = new JLabel("<html>" + "You chose \"Yes\" to \"Have you been in close, personal, and/or skin-to-skin contact " +
                "with someone you know or suspect that has Monkeypox?\" and \"Do you have a rash that looks like pimples or blisters that may be painful or itchy?\". "  +
                "Since you have been in contact with someone carrying the virus and you are now exhibiting symptoms, it is very likely " +
                "that you are now infected with Monkeypox. It is even possible to show only rash symptoms without flu-like symptoms while having Monkeypox. " +
                "It is recommended to quarantine yourself and consult a medical professional to confirm whether you have Monkeypox. "+
                "For more information visit https://www.cdc.gov/poxvirus/monkeypox/index.html." + "<html>");
                answer.setBounds(25, 30, 550, 500);
                answer.setFont(new Font("Open Sans", Font.PLAIN, 20));
                add(answer);
            } else if (getSpecificUserAnswers(1).equals("Yes") && getSpecificUserAnswers(2).equals("Yes")) {
                exit.setBounds(230, 500, 120, 25);
                JLabel answer = new JLabel("<html>" + "You chose \"Yes\" to  \"Do you have flu-like symptoms or symptoms of the following: " +
                "Fever, Chills, Swollen Lymph Nodes, Exhuastion, Muscle Aches "  +
                "and Backache, Headache, and/or Respiratory Symptoms (e.g. sore throat, nasal congestion, or cough)?\" and \"Do you have a rash that looks like pimples or blisters that may be painful or itchy?\". "  +
                "Since you exhibit both flu-like symtoms and rashes, it is very likely that you are infected with Monkeypox. " +
                "It is recommended to quarantine yourself and consult a medical professional to confirm whether you have Monkeypox. "+
                "For more information visit https://www.cdc.gov/poxvirus/monkeypox/index.html." + "<html>");
                answer.setBounds(25, 25, 550, 500);
                answer.setFont(new Font("Open Sans", Font.PLAIN, 20));
                add(answer);
            }
        }

        exit.addActionListener(this);
        add(exit);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    
    }
    /**
     * Checks if the "exit" button has been clicked to close out the program.
     * 
     * @param ae checks if a button has been clicked
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            System.exit(0);
        }
    }
    /**
     * The main method that opens up the full results page.
     * 
     * @param args an array of command-line arguments for the application
     */
    public static void main (String[]args) {
        ArrayList<String> holder = new ArrayList<>();
        new FullResults(holder, 0);
    }  
    /**
     * Retrieves the user's answer to a specific question.
     * 
     * @param num the index of question number that you want the user's answer to (question 1 would be index 0)
     * @return the user's answer for that specifc question
     */
    public String getSpecificUserAnswers(int num) {
        return userAnswers.get(num);
    } 
    /**
     * Gets the array of strings of the user's answers.
     * 
     * @return the array of the user's answers
     */
    public ArrayList<String> getUserAnswers() {
        return userAnswers;
    }
    /**
     * Gets the number of times that the user has answered "Yes".
     * 
     * @return the number of times of "Yes" in the assessment
     */
    public int getCounter() {
        return counter;
    }
}
