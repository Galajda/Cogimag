/*
 * Copyright (C) 2018 Michal G. <Michal.G at cogitatummagnumtelae.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cogimag.java.keyboard.development;

import cogimag.java.keyboard.KeyMap_EN_US;
import cogimag.java.keyboard.RoboSteno;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/**
 * Tests the KeyMap_EN_US by looking up in the HashMap a character that the user
 * types into the input box and repeating this character to the output box through 
 * java.awt.Robot. In order to adapt the tester to other key maps, the user may
 * alter the constructor to take a custom key map as an argument.<br>
 * Known issue: Does not work with Caps Lock. <br>
 * Known issue: main throws StringIndexOutOfBoundsException if user presses Enter
 * when the input box is empty. There is no plan to fix these bugs. The class is
 * intended for development use only.
 * @author MichalG HP Envy
 */
public class RobotTester extends JFrame implements KeyListener, ActionListener {
    //alternative: make components final. declare them in constructor. assign listener in the runnable
    private JSplitPane paneTxtFieldContainer;
    private JTextField txtInput;
    private static final String TXT_INPUT_NAME = "text field input"; 
    private JTextField txtOutput;    
    private static final String TXT_OUTPUT_NAME = "text field ouput";
    
    private JScrollPane paneResultsContainer;    
    private JTextArea txtTestResults;
    
    private JSplitPane paneButtonContainer;    
    private JButton btnSubmit;    
    private static final String BTN_SUBMIT_TEXT = "Submit char to typist";
    private JButton btnClear;
    private static final String BTN_CLEAR_TEXT = "Clear text";
    
    private static RoboSteno typist;
    
    public static void main(String[] args) {        
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        //Schedule a job for event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });     
    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        RobotTester frame = new RobotTester("Robot Map Tester");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Set up the content pane.
        frame.addComponentsToPane();
        
        //Display the window.
        frame.pack();
        frame.setBounds(700, 50, 500, 500);
        frame.setVisible(true);
    }
    
    private RobotTester(String app_title) {
        super(app_title);
        typist = new RoboSteno(new KeyMap_EN_US());        
    }
    
    private void addComponentsToPane() {        
        txtInput = new JTextField(240);
        txtInput.setMinimumSize(new Dimension(240,20));
        txtInput.setName(TXT_INPUT_NAME);
//        txtInput.setMinimumSize(new Dimension(150,20));
        txtInput.addKeyListener(this);
        txtOutput = new JTextField(20);
        txtOutput.setName(TXT_OUTPUT_NAME);
        txtOutput.addKeyListener(this);
        paneTxtFieldContainer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, txtInput, txtOutput);
        paneTxtFieldContainer.setDividerLocation((double)0.5);
        getContentPane().add(paneTxtFieldContainer, BorderLayout.PAGE_START);
        
        txtTestResults = new JTextArea("Test results\n");
        txtTestResults.setEditable(false);
        paneResultsContainer = new JScrollPane(txtTestResults);
        paneResultsContainer.setPreferredSize(new Dimension(500,600));
        getContentPane().add(paneResultsContainer, BorderLayout.CENTER);
        
        btnSubmit = new JButton(RobotTester.BTN_SUBMIT_TEXT);
        btnSubmit.addActionListener(this);        
        btnClear = new JButton(RobotTester.BTN_CLEAR_TEXT);
        btnClear.addActionListener(this);       
        paneButtonContainer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, btnSubmit, btnClear);
        paneButtonContainer.setDividerLocation((double)0.5);
        getContentPane().add(paneButtonContainer, BorderLayout.PAGE_END);
    }
    
    /**
     * 
     * The comparison between the user input and the automated output is done 
     * in this event. Used exclusively to catch events from the output text 
     * field, where the {@code KeyEventDispatcher} sends events. Key typed 
     * events from other text fields are ignored. The ASCII number can be 
     * obtained from the keyTyped event. However, separating the actual key 
     * typed from the Enter key typed is a nuisance. An alternative is to derive
     * the ASCII number from the character in the text field.
     * @param e key event
     */
    @Override
    public void keyTyped(KeyEvent e) {
//        System.out.print("key typed event.");
//        asciiNumber = Character.toString(e.getKeyChar()).codePointAt(0);
//        System.out.println(" ascii number=" + Character.toString(e.getKeyChar()).codePointAt(0));
        if (e.getSource().getClass().equals(javax.swing.JTextField.class)) {
//            System.out.println("key release event from source " + e.getSource().getClass());
            JTextField txtFieldSrc = (JTextField)e.getSource();
            if (txtFieldSrc.getName().equals(TXT_OUTPUT_NAME)) {
//                System.out.println("key typed event from output box. key char = " + e.getKeyChar() + " as int = " + (e.getKeyChar() + 0));
                int inputCharAsciiCode =  KeyMap_EN_US.getAsciiNumber(txtInput.getText());
                System.out.println("key typed event from output box. input as int = " + inputCharAsciiCode);
                int outputCharAsciiCode = (e.getKeyChar() + 0);
                System.out.println("key typed event from output box. output as int = " + outputCharAsciiCode);
                String testResult = "you typed " + txtInput.getText() + ". input ascii number = " + inputCharAsciiCode +
                        ". output ascii number = " + outputCharAsciiCode + ". match ? " + (inputCharAsciiCode == outputCharAsciiCode);
                txtTestResults.append(testResult + "\n");
                
                //wait until key events are done before clearing the text fields
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        txtInput.requestFocusInWindow();
                        txtInput.setText("");
                        txtOutput.setText("");
                    }                    
                });
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * The automatic typing is initiated here. The method responds to
     * the Enter key being pressed in the input text field, indicating that
     * the user has finished typing the character and wishes to submit it for
     * testing against the automatically generated character. KeyReleased
     * events in other fields are ignored.
     * @param e a keyboard event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource().getClass().equals(javax.swing.JTextField.class)) {
//            System.out.println("key release event from source " + e.getSource().getClass());
            JTextField txtFieldSrc = (JTextField)e.getSource();
            //this structure can be reduced. it was used during experimentation.
            if (TXT_INPUT_NAME.equals(txtFieldSrc.getName()) && e.getKeyCode() == KeyEvent.VK_ENTER) {
                System.out.println("key release event for enter");
                echoChar();
            }                        
        }        
    }

    /**
     * Button click handler. The Submit button is an alternate path to submit
     * a character for comparison with the automatically generated character.
     * @param e button click event
     */
    @Override
    public void actionPerformed(ActionEvent e) {        
        JButton btn = (JButton) e.getSource();        
//        System.out.println("button text " + btn.getText());
        
        switch (btn.getText()) {
            case RobotTester.BTN_CLEAR_TEXT:                
//                System.out.println("btnClear click");                
                txtOutput.setText("");
                txtInput.setText("");
                txtInput.requestFocusInWindow();
                break;
            case RobotTester.BTN_SUBMIT_TEXT:
                echoChar();
                break;
            default:                
        }
        
    }
    /**
     * Calls the RoboSteno to repeat the character that the user has typed
     * into the input box. The output appears in the adjoining text field. The
     * comparison is triggered by keyTyped event in the output box.
     */
    private void echoChar() {    
//        System.out.println("input text:" + txtInput.getText());
        txtOutput.requestFocusInWindow();
//        typist.testAccent();
//        typist.type((char)txtInput.getText().codePointAt(0));
        typist.type(KeyMap_EN_US.getAsciiNumber(txtInput.getText()));
        
        //wait for typing to be completed, check in keyTyped event
        
    }    
}
