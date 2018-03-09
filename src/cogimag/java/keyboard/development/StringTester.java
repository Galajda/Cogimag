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

import cogimag.java.keyboard.KeyEventDispatcher;
import cogimag.java.keyboard.KeyMap_EN_US;
import cogimag.java.keyboard.RoboSteno;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author MichalG HP Envy
 */
public class StringTester extends JFrame implements KeyListener, ActionListener{
    private JSplitPane paneInputAndRadioContainer;
    private JTextField txtInput;
    private static final String TXT_INPUT_NAME = "text field input"; 
    private JPanel panelRadioContainer;
    private JRadioButton rdoKeyEvent;
    private static final String TXT_RDO_KEY_EVENT = "Use key event dispatcher";
    private JRadioButton rdoRoboSteno;
    private static final String TXT_RDO_ROBO_STENO = "Use robo steno";
    private ButtonGroup grpRadioGroup;
    
    private JScrollPane paneOutputContainer;    
//    private JTextArea txtTestResults;
    private JTextArea txtOutput;    
    private static final String TXT_OUTPUT_HEADER = "Test results\n";
    private static final String TXT_OUTPUT_NAME = "text field ouput";
    
    private JSplitPane paneButtonContainer;    
    private JButton btnSubmit;    
    private static final String BTN_SUBMIT_TEXT = "Submit string to typist";
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
        StringTester frame = new StringTester("Robot Map Tester");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Set up the content pane.
        frame.addComponentsToPane();
        
        //Display the window.
        frame.pack();
        frame.setBounds(700, 50, 400, 500);
        frame.setVisible(true);
    }
    
    private StringTester(String app_title) {
        super(app_title);
        typist = new RoboSteno(new KeyMap_EN_US());        
    }
    
    private void addComponentsToPane() {        
        txtInput = new JTextField(200);        
        txtInput.setMinimumSize(new Dimension(150,20));
        txtInput.setName(TXT_INPUT_NAME);
        txtInput.addKeyListener(this);
        
        rdoKeyEvent = new JRadioButton(StringTester.TXT_RDO_KEY_EVENT);
        rdoKeyEvent.setSelected(true);
        rdoRoboSteno = new JRadioButton(StringTester.TXT_RDO_ROBO_STENO);
        rdoRoboSteno.setSelected(false);
//        txtOutput = new JTextField(20);
//        txtOutput.setName(StringTester.TXT_OUTPUT_NAME);
//        txtOutput.addKeyListener(this);
        grpRadioGroup = new ButtonGroup();
        grpRadioGroup.add(rdoKeyEvent);
        
        grpRadioGroup.add(rdoRoboSteno);
        panelRadioContainer = new JPanel();
        panelRadioContainer.add(this.rdoKeyEvent);
        panelRadioContainer.add(this.rdoRoboSteno);
        paneInputAndRadioContainer = new JSplitPane(JSplitPane.VERTICAL_SPLIT, txtInput, panelRadioContainer);
        paneInputAndRadioContainer.setDividerLocation((double)0.5);
        getContentPane().add(paneInputAndRadioContainer, BorderLayout.PAGE_START);
        
        
        
        txtOutput = new JTextArea(TXT_OUTPUT_HEADER);
        txtOutput.setName(StringTester.TXT_OUTPUT_NAME);
//        txtOutput.setEditable(false);
        paneOutputContainer = new JScrollPane(txtOutput);
        
        paneOutputContainer.setPreferredSize(new Dimension(500,600));
        getContentPane().add(paneOutputContainer, BorderLayout.CENTER);
        
        btnSubmit = new JButton(StringTester.BTN_SUBMIT_TEXT);
        btnSubmit.addActionListener(this);        
        btnClear = new JButton(StringTester.BTN_CLEAR_TEXT);
        btnClear.addActionListener(this);       
        paneButtonContainer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, btnSubmit, btnClear);
        paneButtonContainer.setDividerLocation((double)0.5);
        getContentPane().add(paneButtonContainer, BorderLayout.PAGE_END);
    }
    
    /**
     * The ASCII number can be obtained from the keyTyped event. However, 
     * separating the actual key typed from the Enter key typed is a nuisance. 
     * An alternative is to derive the ASCII number from the character in the 
     * text field.
     * @param e key event
     */
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
        if (e.getSource().getClass().equals(javax.swing.JTextField.class)) {
//            System.out.println("key release event from source " + e.getSource().getClass());
            JTextField txtFieldSrc = (JTextField)e.getSource();
            //this structure can be reduced. it was used during experimentation.
            if (TXT_INPUT_NAME.equals(txtFieldSrc.getName()) && e.getKeyCode() == KeyEvent.VK_ENTER) {
//                System.out.println("key release event for enter");
                repeatString();
            }                        
        }        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {        
        JButton btn = (JButton) e.getSource();        
//        System.out.println("button text " + btn.getText());
        
        switch (btn.getText()) {
            case StringTester.BTN_CLEAR_TEXT:                
//                System.out.println("btnClear click");                
                txtOutput.setText(TXT_OUTPUT_HEADER);
                txtInput.setText("");
                txtInput.requestFocusInWindow();
                break;
            case StringTester.BTN_SUBMIT_TEXT:
//                System.out.println("btnSubmit click");                
                repeatString();
                break;
            default:                
        }
        
    }
    private void repeatString() {
        
        txtOutput.requestFocusInWindow();
        txtOutput.setCaretPosition(txtOutput.getDocument().getLength());
        if (this.grpRadioGroup.getSelection().equals(this.rdoKeyEvent.getModel())) {
            System.out.println("using key event");
            KeyEventDispatcher.fireEvent(new KeyMap_EN_US(), this.txtInput.getText());
        }
        else {
            System.out.println("using robo steno");
            typist.type(txtInput.getText());
        }
        
    }
}
