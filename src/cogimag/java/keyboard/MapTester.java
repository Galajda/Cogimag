/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cogimag.java.keyboard;

import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
//import java.lang.reflect.Modifier;
//import java.awt.Robot;
//import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

//import java.util.ArrayList;
//import java.util.HashMap;
import javafx.scene.input.KeyCode;
import javax.swing.JButton;
import javax.swing.JFrame;
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
public class MapTester extends JFrame implements KeyListener, ActionListener {
    //alternative: make components final. declare them in constructor. assign listener in the runnable
    private JTextField txtInput;
//    private JScrollPane paneOutputContainer;
    private JTextArea txtOutput;    
    private JSplitPane paneButtonContainer;
    
    private JButton btnSubmit;    
    private static final String BTN_SUBMIT_TEXT = "Submit char to typist";
    private JButton btnClear;
    private static final String BTN_CLEAR_TEXT = "Clear text";
    
//    private static final String NEWLINE = System.getProperty("line.separator");    
//    private String displayedChar;
//    private int vkNumber;
//    private boolean isShifted;
//    private CharConstruction charCon;
    private int asciiNumber;
    private RoboSteno typist;
    static final String NEWLINE = System.getProperty("line.separator");
    
    
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
        MapTester frame = new MapTester("KeyEvent Map Tester");
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Set up the content pane.
        frame.addComponentsToPane();
        
        //Display the window.
        frame.pack();
        frame.setBounds(700, 50, 400, 500);
        frame.setVisible(true);
    }
    
    private MapTester(String app_title) {
        super(app_title);
        typist = new RoboSteno();
        
    }
    
    
    private void addComponentsToPane() {        
        txtInput = new JTextField(20);
        
//        txtInput.addActionListener(this);
        txtInput.addKeyListener(this);
        getContentPane().add(txtInput, BorderLayout.PAGE_START);
        
        txtOutput = new JTextArea();
        txtOutput.setMinimumSize(new Dimension(200,300));
//        txtOutput.setEditable(false);
//        paneOutputContainer = new JScrollPane(txtOutput);
//        paneOutputContainer = new JScrollPane();
//        paneOutputContainer.setPreferredSize(new Dimension(500,600));
//        paneOutputContainer.add(txtOutput);
//        paneOutputContainer.revalidate();
        getContentPane().add(txtOutput, BorderLayout.CENTER);
        
        this.btnSubmit = new JButton(MapTester.BTN_SUBMIT_TEXT);
        this.btnSubmit.addActionListener(this);
        
        btnClear = new JButton(MapTester.BTN_CLEAR_TEXT);
        btnClear.addActionListener(this);       
//        this.btnCopy = new JButton(MapGenerator.BTN_COPY_TEXT);
//        btnCopy.addActionListener(this);
        paneButtonContainer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,btnSubmit, btnClear);
        getContentPane().add(paneButtonContainer, BorderLayout.PAGE_END);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        asciiNumber = Character.toString(e.getKeyChar()).codePointAt(0);
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.echoChar();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {        
        JButton btn = (JButton) e.getSource();        
//        System.out.println("button text " + btn.getText());
        
        switch (btn.getText()) {
            case MapTester.BTN_CLEAR_TEXT:                
//                System.out.println("btnClear click");                
                txtOutput.setText("");
                txtInput.setText("");
                break;
            case MapTester.BTN_SUBMIT_TEXT:
                this.echoChar();
                break;
            default:                
        }
        
//        txtInput.requestFocusInWindow();
        
        
    }
    private void echoChar() {    
        System.out.println("input text:" + txtInput.getText());
        txtOutput.append(NEWLINE + "you typed " + txtInput.getText() + " the robot types " );
        txtOutput.requestFocusInWindow();
        txtOutput.setCaretPosition(txtOutput.getDocument().getLength());
        typist.type((char)txtInput.getText().codePointAt(0));
        txtOutput.append(NEWLINE);   
        txtInput.setText("");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtInput.requestFocusInWindow();
            }
        });       

    }
    
    
    
    
    
    
    
    
    
}
