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

import cogimag.java.keyboard.RoboSteno;
import cogimag.java.keyboard.KeyMap;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * Tests the KeyMap by looking up in the HashMap a character that the user types
 * into the input box and repeating this character to the output box through 
 * java.awt.Robot. <br>
 * Known issue: Does not work with Caps Lock. <br>
 * Known issue: main() throws StringIndexOutOfBoundsException if user presses Enter
 * when the input box is empty. There is no plan to fix this. The class is intended
 * for development use only.
 * @author MichalG HP Envy
 */
public class RobotTester extends JFrame implements KeyListener, ActionListener {
    //alternative: make components final. declare them in constructor. assign listener in the runnable
    private JSplitPane paneTxtFieldContainer;
    private JTextField txtInput;
    private JTextField txtOutput;    
    
    private JScrollPane paneOutputContainer;    
    private JTextArea txtTestResults;
    
    private JSplitPane paneButtonContainer;    
    private JButton btnSubmit;    
    private static final String BTN_SUBMIT_TEXT = "Submit char to typist";
    private JButton btnClear;
    private static final String BTN_CLEAR_TEXT = "Clear text";
    
    private RoboSteno typist;
    
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
        frame.setBounds(700, 50, 400, 500);
        frame.setVisible(true);
    }
    
    private RobotTester(String app_title) {
        super(app_title);
        typist = new RoboSteno(new KeyMap());        
    }
    
    private void addComponentsToPane() {        
        txtInput = new JTextField(200);
        txtInput.setMinimumSize(new Dimension(150,20));
        txtInput.addKeyListener(this);
        txtOutput = new JTextField(20);
        txtOutput.addKeyListener(this);
        paneTxtFieldContainer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, txtInput, txtOutput);
        paneTxtFieldContainer.setDividerLocation((double)0.5);
        getContentPane().add(paneTxtFieldContainer, BorderLayout.PAGE_START);
        
        txtTestResults = new JTextArea("Test results\n");
        txtTestResults.setEditable(false);
        paneOutputContainer = new JScrollPane(txtTestResults);
        paneOutputContainer.setPreferredSize(new Dimension(500,600));
        getContentPane().add(paneOutputContainer, BorderLayout.CENTER);
        
        btnSubmit = new JButton(RobotTester.BTN_SUBMIT_TEXT);
        btnSubmit.addActionListener(this);        
        btnClear = new JButton(RobotTester.BTN_CLEAR_TEXT);
        btnClear.addActionListener(this);       
        paneButtonContainer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, btnSubmit, btnClear);
        paneButtonContainer.setDividerLocation((double)0.5);
        getContentPane().add(paneButtonContainer, BorderLayout.PAGE_END);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
//        System.out.print("key typed event.");
//        asciiNumber = Character.toString(e.getKeyChar()).codePointAt(0);
//        System.out.println(" ascii number=" + Character.toString(e.getKeyChar()).codePointAt(0));
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            echoChar();
        }
    }

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
    private void echoChar() {    
//        System.out.println("input text:" + txtInput.getText());
//        txtOutput.append(NEWLINE + "you typed " + txtInput.getText() + " the robot types " );
        txtOutput.requestFocusInWindow();
//        typist.testAccent();
//        txtOutput.setCaretPosition(txtOutput.getDocument().getLength());
        typist.type((char)txtInput.getText().codePointAt(0));
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                String inputChar = txtInput.getText().substring(0, 1);
                //output box may be empty if the typist is slow
                if (txtOutput.getText().length() > 0) {
                    String robotOutput = txtOutput.getText().substring(txtOutput.getText().length()-1);

    //                System.out.println("output window last char\t" + lastChar);                
                    String testResult = "you typed " + inputChar + ". robot typed " + 
                            robotOutput + ". do they match? " + inputChar.equals(robotOutput);
    //                System.out.println(testResult);    
                    txtTestResults.append(testResult + "\n");
                }
                txtInput.requestFocusInWindow();
                txtInput.setText("");
                txtOutput.setText("");
            }
        });       

    }
    
    
}
