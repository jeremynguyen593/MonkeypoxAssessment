package quiz;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import java.sql.*;

/**
 * The actual health assessment where the user is prompted to a few questions. The answer is then recorded to assess
 * their likelihood of having the virus.
 * 
 * @author Jeremy Nguyen
 * @version 8-24-2022
 */
public class Assessment extends JFrame implements ActionListener{
    /**
     * The URL to connect to the database of the assessment.
     */
    static final String URL_2 = "jdbc:mysql://localhost:3306/MONKEYPOXASSESSMENT";

    /**
     * The username for the mySQL login.
     */
    static final String USER = "root";
    /**
     * The password for the mySQL login.
     */
    static final String PASS = "";
    /**
     * The name of the user.
     */
    private String name;
    
    /**
     * The questions of the assessment.
     */
    private String questions[][] = new String[4][2];
    /**
     * The storage where the user's answer will stay.
     */
    private ArrayList<String> userAnswers = new ArrayList<String>();
    
    /**
     * The labels of the questions and the question numbers.
     */
    JLabel qnumber, question, moreQuestion;
    /**
     * Radio buttons of the answers to the assessment.
     */
    JRadioButton yes, no;
    /**
     * The button to proceed to the next question or the results page if finished.
     */
    JButton next;
    /**
     * The grouping of the radio buttons to allow only one selected answer at a time.
     */
    ButtonGroup groupOptions;
    /**
     * A counter to check what question number and question should be prompted up to the user.
     */
    private int count = 0;
    /**
     * A counter to check how many times the user answered "yes" to a question.
     */
    private int counter = 0;
    /**
     * The risk level the user is at based off their assessment answers.
     */
    private String risk;
    
    /**
     * The assessment page where the user will then answer a few questions about potential signs of having
     * Monkeypox.
     * 
     * @param name the name of the user
     */
    public Assessment(String name) {
        this.name = name;
        setBounds(50, 0, 1440, 850);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        
        ImageIcon mainImage = new ImageIcon(ClassLoader.getSystemResource("icons/monkeypox.jpg"));
        JLabel image = new JLabel(mainImage);
        image.setBounds(0, 0, 1440, 392);
        add(image);
        
        qnumber = new JLabel();
        qnumber.setBounds(100, 450, 50, 30);
        qnumber.setFont(new Font("Tahoma", Font.PLAIN, 22));
        add(qnumber);
        
        question = new JLabel();
        question.setBounds(130, 450, 2000, 30);
        question.setFont(new Font("Tahoma", Font.PLAIN, 22));
        add(question);
        
        moreQuestion = new JLabel();
        moreQuestion.setBounds(130, 490, 2000, 30);
        moreQuestion.setFont(new Font("Tahoma", Font.PLAIN, 22));
        add(moreQuestion);
        
        setQuestions(0, 0, "Have you been in close, personal, and/or skin-to-skin contact " +
                                      "with someone you know or suspect that has Monkeypox?");
        setQuestions(1, 0, "Do you have flu-like symptoms or symptoms of the following: Fever, Chills, Swollen Lymph Nodes, Exhuastion, Muscle Aches");
        setQuestions(2, 0, "Do you have a rash that looks like pimples or blisters that may be painful or itchy?");
        
        yes = new JRadioButton("Yes");
        yes.setBounds(170, 550, 700, 30);
        yes.setBackground(Color.WHITE);
        yes.setFont(new Font("Dialog", Font.PLAIN, 20));
        yes.addActionListener(this);
        add(yes);
        
        no = new JRadioButton("No");
        no.setBounds(170, 590, 700, 30);
        no.setBackground(Color.WHITE);
        no.setFont(new Font("Dialog", Font.PLAIN, 20));
        no.addActionListener(this);
        add(no);
        
        groupOptions = new ButtonGroup();
        groupOptions.add(yes);
        groupOptions.add(no);
        
        next = new JButton("Next");
        next.setBounds(1100, 675, 200, 40);
        next.setFont(new Font("Tahoma", Font.PLAIN, 22));
        next.addActionListener(this);
        add(next);
        
        start(0);
        
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
    /**
     * Checks if the user has answered the question and only allows the user to move on if the question is answered. Additionally,
     * this method stores the answer of the user and when finished, will open up the results page.
     * 
     * @param ae checks if any button has been clicked
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next && groupOptions.getSelection() != null && getCount() != 2) {
            addCount();
            if (yes.isSelected()) {
                userAnswers.add("Yes");
            } else if (no.isSelected()) {
                userAnswers.add("No");
            }
            groupOptions.clearSelection();
            start(getCount());
        } else if (ae.getSource() == next && groupOptions.getSelection() != null && getCount() >= 2) {
            if (yes.isSelected()) {
                userAnswers.add("Yes");
            } else if (no.isSelected()) {
                userAnswers.add("No");
            }
            for (int i = 0; i < userAnswers.size(); i++) {
                if (userAnswers.get(i).equals("Yes")) {
                    counter++;
                }
            }
            if (counter == 3) {
                setRisk("High Level");
            } else if (counter == 2 || counter == 1) {
                setRisk("At Risk");
            } else {
                setRisk("No Risk");
            }
            try {
                Connection conn = DriverManager.getConnection(URL_2, USER, PASS);
                Statement stmt = conn.createStatement();
    
                String insertData = "INSERT INTO DATA(NAME, RISK, TIME) VALUES('"+getName()+"', '"+getRisk()+"', CURRENT_TIMESTAMP)";
    
                stmt.executeUpdate(insertData);
                
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            setVisible(false);
            new Result(getName(), getUserAnswers(), getCounter(), getRisk());
        } 
    } 
    /**
     * The method to start the assessment and sets up the text based on the question that the user is on.
     * 
     * @param counter the question number that the user is on
     */
    public void start(int counter) {
        qnumber.setText("" + (counter + 1) + ".");
        question.setText(questions[getCount()][0]);
        if (getCount() == 1) {
            moreQuestion.setText("and Backache, Headache, and/or Respiratory Symptoms (e.g. sore throat, nasal congestion, or cough)?");
        } else {
            moreQuestion.setText("");
        }
    }
    /**
     * returns the list of the user's answers
     * 
     * @return an Arraylist of String of the user's answers
     */
    public ArrayList<String> getUserAnswers() {
        return userAnswers;
    }
    /**
     * This allows the creation of the questions of the assessment.
     * 
     * @param num1 the index that corresponds to the question number
     * @param num2 the index of which the question is to be placed at
     * @param question the question that the assessment will ask the user
     */
    public void setQuestions(int num1, int num2, String question) {
        questions[num1][num2] = question;
    }
    /**
     * Adds one to the count.
     */
    public void addCount() {
        count++;
    }
    /**
     * Gets the question number of the assessment.
     * 
     * @return the question number
     */
    public int getCount() {
        return count;
    }
    /**
     * Gets the name of the user.
     * 
     * @return name the name of the user
     */
    public String getName() {
        return name;
    }
    /**
     * Gets the count of how many times a user answered "yes"
     * 
     * @return the "yes" counter
     */
    public int getCounter() {
        return counter;
    }
    /**
     * Sets the risk level of the user.
     * 
     * @param risk the risk level of the user
     */
    public void setRisk(String risk) {
        this.risk = risk;
    }
    /**
     * Returns the risk level of the user.
     */
    public String getRisk() {
        return risk;
    }
    /**
     * The main method where the assessment GUI will be called.
     * 
     * @param args an array of command-line arguments for the application
     */
    public static void main (String[]args) {
        new Assessment("User");
    }
}
