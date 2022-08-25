package quiz;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/**
 * Sets up the login page of the health assessment with a pop-up GUI that allows the user to enter their name in which
 * they can either proceed with the assessment or exit out of the program.
 * 
 * @author Jeremy Nguyen
 * @version 8-24-2022
 */
public class Login extends JFrame implements ActionListener{
    
    /**
     * The buttons for the next page and to exit out of the program.
     */
    JButton nextButton, exit;
    /**
     * The textfield where the user can input their name.
     */
    JTextField tfname;
    /**
     * The name of the user.
     */
    private String name;
    
    /**
     * The creation of the login page.
     */
    public Login() {
        //background
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        //adding the login image to the left
        ImageIcon image_1 = new ImageIcon(ClassLoader.getSystemResource("icons/login-image.jpg"));
        JLabel loginImage = new JLabel(image_1);
        loginImage.setBounds(0,-10,600,500);
        add(loginImage);

        //adding the header of the login page
        JLabel header = new JLabel("Monkeypox Health Assessment");
        header.setBounds(660, 60, 700, 45);
        header.setFont(new Font ("Bahnschrift", Font.BOLD, 30));
        add(header);

        //adding the prompt
        JLabel name = new JLabel("Enter your name");
        name.setBounds(825, 150, 300, 30);
        name.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
        add(name);
        
        //creating the textbox for the name
        tfname = new JTextField();
        tfname.setBounds(755, 200, 300, 25);
        tfname.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        add(tfname);
        
        //Button for disclaimer page
        nextButton = new JButton("Next");
        nextButton.setBounds(935, 270, 120, 25);
        nextButton.addActionListener(this);
        add(nextButton);
        
        //Exit Button
        exit = new JButton("Exit");
        exit.setBounds(755, 270, 120, 25);
        exit.addActionListener(this);
        add(exit);
        
        //setting the location of the pop-up
        setSize(1200, 500);
        //sets the pop-up in the center of the screen
        setLocationRelativeTo(null);
        //does not allow user to close out application
        setResizable(false);
        //lets the user see the GUI
        setVisible(true);

        //clicking "x" will now stop the program and close it
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    /**
     * The method to check if the "next" or "exit" button is clicked where the program
     * opens up another pop-up indicating that the user has not entered their name, the next page
     * of the assessment, or closes out the program.
     * 
     * @param ae takes in any clicked buttons
     */
    public void actionPerformed(ActionEvent ae) {
        //takes the name of user and opens up the next page when "next" button is hit
        if (ae.getSource() == nextButton) {
            setName(tfname.getText());
            setVisible(false);
            //if blank then opens up blank name warning
            if (getName().equals("")) {
                new BlankName();
            } else {
                //if successful then go to the disclaimer page
                new Disclaimer(getName());
            }
            //closes out the program if the user clicks exit
        } else if (ae.getSource() == exit) {
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
     * Sets the name of the user.
     * 
     * @param name the name that the user enters
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * The main method where the login is called to open up the GUI.
     * 
     * @param args an array of command-line arguments for the application
     */
    public static void main(String[]args){
        new Login();
    }
}