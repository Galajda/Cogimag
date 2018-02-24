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

import cogimag.java.keyboard.CharConstruction;
import cogimag.java.keyboard.KeyMap;
import cogimag.java.keyboard.KeyMap_EN_US;
//import cogimag.java.keyboard.development.SampleExtendedKeyMap;

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
 * Main method to be run during application development. <br>
 Automates the process of creating a HashMap for use by Robot or manually triggered KeyEvent.
 The class provides a simple window with a text input box and a text output box. The user will 
 type one character from the keyboard into the text input  box. The keyPressed event captures the 
 java.awt.event.KeyEvent.VK_ code as an integer and checks the status of the shift key (is this character 
 on the first level of the keyboard, or must the user press shift to obtain it, as for upper-case letters?).
 The keyTyped event captures the ASCII number of the displayed character and its String representation.
 With these four parameters, an entry in the KeyMap HashMap can be constructed. For readability, the VK_
 integer is converted into its class name, e.g., KeyEvent.VK_A. The developer may press every printable
 character on the local keyboard, obtaining a complete list of the KeyMap entries needed to read and type 
 the characters on the client's keyboard. The built-in KeyMap_EN_US class contains such a list for the US-English
 QWERTY keyboard. If the client uses a different keyboard, the developer may extend the built-in class
 and hide the MakeMap() method with a new method containing the client's keyboard map.
 <br><br>
 * Known issue: " and \ need escape char. Currently this must be fixed manually after the put statements are generated.
 * <br><br>
 * Adapted from KeyEventDemo.java (attached) by Oracle. Read the full article  at 
 * <a href="https://docs.oracle.com/javase/tutorial/uiswing/events/keylistener.html">Oracle key listener tutorial</a>
 * @author Michal G. <Michal.G at cogitatummagnumtelae.com>
 */
public class MapGenerator extends JFrame implements KeyListener, ActionListener {
    //alternative: make components final. declare them in constructor. assign listener in the runnable
    private JTextField txtInput;
    private JScrollPane paneOutputContainer;
    private JTextArea txtOutput;    
    private JSplitPane paneButtonContainer;
    private JButton btnClear;
    private static final String BTN_CLEAR_TEXT = "Clear contents";
    private JButton btnCopy;    
    private static final String BTN_COPY_TEXT = "Copy output to clipboard";
//    private static final String NEWLINE = System.getProperty("line.separator");    
    private String displayedChar;
    private int vkNumber;
    private boolean isShifted;
    private CharConstruction charCon;
    
    public static void main(String[] args) {
        //demonstration of static method hiding
             
        System.out.println("parent key map size " + KeyMap.KEY_MAP.size());
        try {
            System.out.println("first value from parent key map " + KeyMap.KEY_MAP.get(33).rendering);
        }
        catch (Exception ex) {
            System.out.println("cannot get first element from parent because " + ex.toString() + " " + ex.getMessage());
        }
        
        System.out.println("en-us key map size " + KeyMap_EN_US.KEY_MAP.size());
        System.out.println("first value from en-us key map " + KeyMap_EN_US.KEY_MAP.get(33).rendering);
        System.out.println("sample key map size " + SampleExtendedKeyMap.KEY_MAP.size());
        System.out.println("first value from sample key map " + SampleExtendedKeyMap.KEY_MAP.get(33).rendering);
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
        MapGenerator frame = new MapGenerator("KeyEvent Mapper");
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
        
        btnClear = new JButton(MapGenerator.BTN_CLEAR_TEXT);
        btnClear.addActionListener(this);       
        this.btnCopy = new JButton(MapGenerator.BTN_COPY_TEXT);
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
//            txtOutput.append("\tkey code = " + e.getKeyCode() + NEWLINE); //VK_ number
//            txtOutput.append("\t\tmodifier = " + KeyEvent.getModifiersExText(e.getModifiersEx()) + NEWLINE);
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
//        txtOutput.append("\t\tkey char = " + e.getKeyChar() + NEWLINE); //char as printed
//        txtOutput.append("\t\tascii # " + asciiNumber + NEWLINE);
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
        JButton btn = (JButton) e.getSource();
        
//        System.out.println("button text " + btn.getText());
        switch (btn.getText()) {
            case MapGenerator.BTN_CLEAR_TEXT:                
//                System.out.println("btnClear click");
                txtInput.setText("");
                txtOutput.setText("");
                break;
            case MapGenerator.BTN_COPY_TEXT:
                txtOutput.selectAll();
                txtOutput.copy();
                break;
            default:
                
        }
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
        
        return sb.toString();
    }
    public static String getVkCode (int testVal) {
//        System.out.println("iterating through key event static fields");
        String returnVal = null;
        Field[] classFields = KeyEvent.class.getDeclaredFields();
        for (Field f : classFields) {            
            if (f.getName().startsWith("VK_")) {
                int vkValue;
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
