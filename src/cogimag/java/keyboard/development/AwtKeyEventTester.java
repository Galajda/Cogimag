/*
 * Copyright (C) 2018 MichalG HP Envy
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

import cogimag.java.keyboard.AwtKeyEventSteno;
import cogimag.java.keyboard.AwtKeyMap_EN_US;

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
 * Tests the AwtKeyMap_EN_US by looking up in the HashMap a character that the user
 types into the input box and repeating this character to the output box through 
 java.awt.event.KeyEvent. In order to adapt the tester to other key maps, the 
 user may alter the call to AwtKeyEventSteno.fireEvent in dispatchKeyEvent 
 to take a custom key map as an argument.<br>
 * Known issue: main throws StringIndexOutOfBoundsException if user presses Enter
 * when the input box is empty. There is no plan to fix this bug. The class is 
 * intended for development use only.
 * @author MichalG HP Envy
 */
public class AwtKeyEventTester extends JFrame implements KeyListener, ActionListener {
    private JSplitPane paneTxtFieldContainer;
    private JTextField txtInput;
    private static final String TXT_INPUT_NAME = "text field input"; 
    private JTextField txtOutput;    
    private static final String TXT_OUTPUT_NAME = "text field ouput";
    private JScrollPane paneResultsContainer;    
    private JTextArea txtTestResults;
    
    private JSplitPane paneButtonContainer;    
    private JButton btnSubmit;    
    private static final String BTN_SUBMIT_TEXT = "Submit char to event queue";
    private JButton btnClear;
    private static final String BTN_CLEAR_TEXT = "Clear text";
    
//    private static final String NEWLINE = System.getProperty("line.separator");
    
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
        AwtKeyEventTester frame = new AwtKeyEventTester("KeyEvent Map Tester");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Set up the content pane.
        frame.addComponentsToPane();
        
        //Display the window.
        frame.pack();
        frame.setBounds(700, 50, 500, 500);
        frame.setVisible(true);
    }
    
    private AwtKeyEventTester(String app_title) {
        super(app_title);
    }
    
    private void addComponentsToPane() {        
        txtInput = new JTextField(240);
        txtInput.setMinimumSize(new Dimension(240,20));
        txtInput.setName(TXT_INPUT_NAME);
//        txtInput.setFocusTraversalKeysEnabled(false);
        txtInput.addKeyListener(this);
        
        txtOutput = new JTextField(200);
        txtOutput.setName(TXT_OUTPUT_NAME);
        txtOutput.setFocusTraversalKeysEnabled(false); //catch tab press in the box
        txtOutput.addKeyListener(this);
        paneTxtFieldContainer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, txtInput, txtOutput);
        paneTxtFieldContainer.setDividerLocation((double)0.5);
        getContentPane().add(paneTxtFieldContainer, BorderLayout.PAGE_START);
        
        txtTestResults = new JTextArea("Test results\n");
//        txtTestResults.setEditable(false);
        paneResultsContainer = new JScrollPane(txtTestResults);
        paneResultsContainer.setPreferredSize(new Dimension(500,600));
        getContentPane().add(paneResultsContainer, BorderLayout.CENTER);
        
        btnSubmit = new JButton(AwtKeyEventTester.BTN_SUBMIT_TEXT);
        btnSubmit.addActionListener(this);        
        btnClear = new JButton(AwtKeyEventTester.BTN_CLEAR_TEXT);
        btnClear.addActionListener(this);       
        paneButtonContainer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, btnSubmit, btnClear);
        paneButtonContainer.setDividerLocation((double)0.5);
        getContentPane().add(paneButtonContainer, BorderLayout.PAGE_END);
    }
    /**
     * The comparison between the user input and the automated output is done 
     * in this event. Used exclusively to catch events from the output text 
     * field, where the {@code AwtKeyEventSteno} sends events. Key typed 
     * events from other text fields are ignored. 
     * @param e a keyboard event
     */
    @Override
    public void keyTyped(KeyEvent e) {
//        System.out.println("key typed event. key char" + e.getKeyChar());
//        asciiNumber = e.getKeyChar() + 0;
//        asciiNumber = Character.toString(e.getKeyChar()).codePointAt(0);
//        System.out.println("ascii number=" + Character.toString(e.getKeyChar()).codePointAt(0));
        if (e.getSource().getClass().equals(javax.swing.JTextField.class)) {
//            System.out.println("key release event from source " + e.getSource().getClass());
            JTextField txtFieldSrc = (JTextField)e.getSource();
            if (txtFieldSrc.getName().equals(TXT_OUTPUT_NAME)) {
//                System.out.println("key typed event from output box. key char = " + e.getKeyChar() + " as int = " + (e.getKeyChar() + 0));
                int inputCharAsciiCode =  AwtKeyMap_EN_US.getAsciiNumber(txtInput.getText());
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
//        System.out.println("key press event for source " + ((JTextField)e.getSource()).getName());        
    }
    /**
     * The automatic key event is initiated here. The method responds to
     * the Enter key being pressed in the input text field, indicating that
     * the user has finished typing the character and wishes to submit it for
     * testing against the automatically generated character. keyReleased
     * events in other fields are ignored.
     * @param e a keyboard event
     */
    @Override
    public void keyReleased(KeyEvent e) {
//        System.out.println("key release event from source " + e.getSource().getClass());
        if (e.getSource().getClass().equals(javax.swing.JTextField.class)) {
//            System.out.println("key release event from source " + e.getSource().getClass());
            JTextField txtFieldSrc = (JTextField)e.getSource();
            if (TXT_INPUT_NAME.equals(txtFieldSrc.getName()) && (e.getKeyCode() == KeyEvent.VK_ENTER)) {
                System.out.println("key release event for enter");
                dispatchKeyEvent();
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
            case AwtKeyEventTester.BTN_CLEAR_TEXT:                
                System.out.println("btnClear click");                
                txtOutput.setText("");
                txtInput.setText("");
                txtTestResults.setText("Test results\n");
                txtInput.requestFocusInWindow();
                break;
            case AwtKeyEventTester.BTN_SUBMIT_TEXT:                
                dispatchKeyEvent();
                break;
            default:                
        }        
    }
    /**
     * Initiates a KeyEvent corresponding to the character that the user has
     * typed into the input text field.
     */
    private void dispatchKeyEvent() {
//        System.out.println("dispatching key event for ascii number " + txtInput.getText().codePointAt(0));
        txtOutput.requestFocusInWindow();
        //change this to suit your custom key map
        System.out.println("input text content = " + txtInput.getText() + " length = " + txtInput.getText().length());
        AwtKeyEventSteno.fireEvent(new AwtKeyMap_EN_US(), AwtKeyMap_EN_US.getAsciiNumber(txtInput.getText()));
    }
}
