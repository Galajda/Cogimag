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
package cogimag.java.keyboard;

import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
//import java.lang.reflect.Modifier;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
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
 * Main method to be run during application development. <br/>
 * This class provides a simple window with 
 * a text input box and a text output box. The user will type one character from the keyboard 
 * into the text box. The method *************** will run through all possible KeyEvent.VK_ keys, 
 * with and without shift. When a match is found with the input string, the method will print the 
 * 
 * do " and \ need escape char?
 * <br/><br/>
 * adapted from KeyEventDemo.java (attached) by Oracle.
 * Help at https://docs.oracle.com/javase/tutorial/uiswing/events/keylistener.html
 
 * @author Michal G. <Michal.G at cogitatummagnumtelae.com>
 
 */
public class MapGenerator extends JFrame implements KeyListener, ActionListener {
    
    private JTextField txtInput;
    private JScrollPane paneOutputContainer;
    private JTextArea txtOutput;    
    private JSplitPane paneButtonContainer;
    private JButton btnClear;
    private JButton btnCopy;
    
    static final String newline = System.getProperty("line.separator");
    
    private String displayedChar;
    private int vkNumber;
    private boolean isShifted;
    private CharConstruction charCon;
    
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
        MapGenerator frame = new MapGenerator("KeyEvent Demonstration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Set up the content pane.
        frame.addComponentsToPane();
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    private MapGenerator(String app_title) {
        super(app_title);        
    }
    
    private void addComponentsToPane() {        
        txtInput = new JTextField(20);
//        txtInput.addActionListener(this);
        txtInput.addKeyListener(this);
        getContentPane().add(txtInput, BorderLayout.PAGE_START);
        
        txtOutput = new JTextArea();
//        txtOutput.setEditable(false);
        paneOutputContainer = new JScrollPane(txtOutput);
//        paneOutputContainer = new JScrollPane();
        paneOutputContainer.setPreferredSize(new Dimension(500,600));
//        paneOutputContainer.add(txtOutput);
//        paneOutputContainer.revalidate();
        getContentPane().add(paneOutputContainer, BorderLayout.CENTER);
        
        btnClear = new JButton("Clear contents");
        btnClear.addActionListener(this);       
        this.btnCopy = new JButton("Copy to clipboard");
        btnCopy.addActionListener(this);
        paneButtonContainer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,btnClear,btnCopy);
        getContentPane().add(paneButtonContainer, BorderLayout.PAGE_END);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println("key press event");
        if (!(KeyEvent.getKeyText(e.getKeyCode()).equalsIgnoreCase(KeyCode.SHIFT.toString()))) {
            //get key code and modifier
//            txtOutput.append(KeyParser.getDisplayInfo(e, "Key pressed: "));
//            txtOutput.append("key pressed event");
//            txtOutput.append("\tkey code = " + e.getKeyCode() + newline); //VK_ number
//            txtOutput.append("\t\tmodifier = " + KeyEvent.getModifiersExText(e.getModifiersEx()) + newline);
//            txtOutput.setCaretPosition(txtOutput.getDocument().getLength());
            vkNumber = e.getKeyCode();
            isShifted = ("Shift".equals(KeyEvent.getModifiersExText(e.getModifiersEx())));
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        System.out.println("key typed event");        
//        txtOutput.append(KeyParser.getDisplayInfo(e, "Key typed: "));
//        txtOutput.setCaretPosition(txtOutput.getDocument().getLength());
        //get key char
//        txtOutput.append("key typed event");
        int asciiNumber = Character.toString(e.getKeyChar()).codePointAt(0);
//        txtOutput.append("\t\tkey char = " + e.getKeyChar() + newline); //char as printed
//        txtOutput.append("\t\tascii # " + asciiNumber + newline);
//        txtOutput.setCaretPosition(txtOutput.getDocument().getLength());
        displayedChar = String.valueOf(e.getKeyChar());
        charCon = new CharConstruction(displayedChar, vkNumber, isShifted);
        String mapGen = MapGenerator.formatMapPut(asciiNumber, charCon);
//        System.out.println(mapGen);
        txtOutput.append(mapGen + "\n");
        txtOutput.setCaretPosition(txtOutput.getDocument().getLength());
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        System.out.println("key release event");
//        txtOutput.append(KeyParser.getDisplayInfo(e, "Key released: "));
//        txtOutput.setCaretPosition(txtOutput.getDocument().getLength());
    }

    /**
     * Button click listener
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //split clear and copy
//        e.getID()
        System.out.println("btnClear click");
        txtInput.setText("");
        txtOutput.setText("");
        txtInput.requestFocusInWindow();
    }
    public static String formatMapPut(int ascii_number, CharConstruction char_con) {
        //TODO: check for escape chars
        StringBuilder sb = new StringBuilder();
        sb.append("map.put(");
        sb.append(ascii_number);
        sb.append(", new CharConstruction(\"");
        sb.append(char_con.rendering); 
        sb.append("\", ");
        
//        sb.append(char_con.keyEventConstant); //should I write this as KeyEvent.VK_ ?
        sb.append("KeyEvent.");
        sb.append(MapGenerator.getVkCode(char_con.keyEventConstant));
        
        sb.append(", ");
        sb.append(char_con.isShifted);
        sb.append("));");
        //        map.put(33, new CharConstruction("!", KeyEvent.VK_1, true));
        
        return sb.toString();
    }
    public static String getVkCode (int testVal) {
//        System.out.println("iterating through key event static fields");
        String returnVal = null;
        Field[] classFields = KeyEvent.class.getDeclaredFields();
        for (Field f : classFields) {
            
            if (f.getName().startsWith("VK_")) {
                int vkValue = -1;
                try {
                    vkValue = f.getInt(f);
                    if (testVal == vkValue) {
                        returnVal = f.getName();
                    }
                }
                catch (Exception ex) {
                    //all of the VK_ in Java SE7 java.awt.event.KeyEvent are public static final int
                }
//                String keyText = KeyEvent.getKeyText(vkValue);
//                System.out.print("\tfound " + f.getName() + " = " + vkValue + " key text :" 
//                        + keyText + ":" + " matches test value " + testVal);
//                System.out.println("char from code point :" + Character.toString((char)s.codePointAt(0)));
                    //same as s
                
            }
        }
        return returnVal;
    }
}
