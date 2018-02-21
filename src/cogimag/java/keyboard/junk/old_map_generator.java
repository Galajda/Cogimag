/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cogimag.java.keyboard.junk;

import cogimag.java.keyboard.CharConstruction;
import cogimag.java.keyboard.development.MapGenerator;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @deprecated 
 * @author MichalG HP Envy
 */
public class old_map_generator {
    

    public static void main(String[] args) {
        //open output window
        //put the cursor in the output window before starting. press Shift + F6 to run
//        System.out.println("printing ascii codes and chars");
//        System.out.println("ascii #\t\tchar\tVK_\tShifted?");
//        printCharConstructions();
//        System.out.println("finish list\n");
        ArrayList<String> vkStringArray = makeVkStringArray();
        ArrayList<Integer> vkValArray = makeVkValArray();
        for (int i : vkValArray) {
//            if (KeyEvent.g)
        }
        
    }
    
    private static ArrayList<String> makeVkStringArray() {
        ArrayList<String> array = new ArrayList<>();
        Field[] classFields = KeyEvent.class.getDeclaredFields();
        System.out.println("printing vk codes");
        for (Field f : classFields) {            
            if (f.getName().startsWith("VK_")) {                
                array.add("KeyEvent." + f.getName());
//                    System.out.println("\tKeyEvent." + f.getName() +"=" + f.getInt(f));                               
            }
        }
        return array;        
    }
    
    private static ArrayList<Integer> makeVkValArray() {
        ArrayList<Integer> array = new ArrayList<>();
        Field[] classFields = KeyEvent.class.getDeclaredFields();
        System.out.println("printing vk codes");
        for (Field f : classFields) {            
            if (f.getName().startsWith("VK_")) {
                try {
                    array.add(f.getInt(f));
//                    System.out.println("\tKeyEvent." + f.getName() +"=" + f.getInt(f));    
                }
                catch (Exception ex) {}                
            }
        }
        return array;        
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
    private static void printCharConstructions() {
//        for (int i=32;i<=126;i++) {
        for (int i=32;i<=35;i++) {
            //i runs through the range of ASCII printable characters. omit delete (127)
            char c = (char)i;
            CharConstruction charCon = getCharConstruction(Character.toString(c));
            if (charCon == null) {
                System.out.println(i + "\t\t:" +c + ":\terr\terr");
            }
            else {
                System.out.println(i + "\t\t:" +c + ":\t" + charCon.keyEventConstant + "\t" + charCon.isShifted);
            }
            
        }
        //key text matches char for digits, ucase letters
        //for punctuation and lcase letters, keytext may be unknown or wrong
        
        
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
                String keyText = KeyEvent.getKeyText(vkValue);
//                System.out.print("\tfound " + f.getName() + " = " + vkValue + " key text :" 
//                        + keyText + ":" + " matches test value " + testVal);
//                System.out.println("char from code point :" + Character.toString((char)s.codePointAt(0)));
                    //same as s
                
            }
        }
        return returnVal;
    }

    
    /**
     * @deprecated 
     * @param s
     * @return 
     */
    public static CharConstruction getCharConstruction(String s) {
        System.out.println("begin get char constr. char val " + s);
        CharConstruction kc = null;
        
        //SO 15313469
        System.out.println("iterating through key event VK_ fields");
        Field[] classFields = KeyEvent.class.getDeclaredFields();
        for (Field f : classFields) {
            if (f.getName().startsWith("VK_")) {
                int vkValue = -1;
                try {
                    vkValue = f.getInt(f);
                    if (testMatch(s, vkValue, true)) {
                        kc = new CharConstruction(s,vkValue,true);
                    }
                    if (testMatch(s, vkValue, false)) {
                        kc = new CharConstruction(s,vkValue,false);
                    }
                }
                catch (Exception ex) {
                    //all of the VK_ in Java SE7 java.awt.event.KeyEvent are public static final int
                }
                String keyText = KeyEvent.getKeyText(vkValue);
                System.out.print("\tfound " + f.getName() + " value " + vkValue + " key text :" + keyText + ":");
//                System.out.println("char from code point :" + Character.toString((char)s.codePointAt(0)));
                    //same as s                
            }            
        }
        return kc;    
    }
    /**
     * @deprecated 
     * @param s
     * @param vk_number
     * @param shift
     * @return 
     */
    private static boolean testMatch(String s, int vk_number, boolean shift) {
        boolean success = false;
        String roboInput;
        Robot r;
        try {
            r = new Robot();
            if (shift) { r.keyPress(KeyEvent.VK_SHIFT); }
//            r.keyPress(vk_number);
//            r.keyRelease(vk_number);    
//            r.keyPress(KeyEvent.VK_SPACE);
//            r.keyRelease(KeyEvent.VK_SPACE);
            if (shift) { r.keyRelease(KeyEvent.VK_SHIFT);}
//            r.keyPress(KeyEvent.VK_ENTER); //0x0d
//            r.keyRelease(KeyEvent.VK_ENTER); //this works
            roboInput = ConsoleReader.readln();
            System.out.println("robot typed " + roboInput);
            success = (s.equals(roboInput));
        }        
        catch (AWTException | SecurityException ex)
        {
            System.out.println("cannot initialize robot" + ex.getMessage());            
        }
        return success;
    }
    /**
     * @deprecated 
     * @param c 
     */
    public static void printMapAssignment(char c) {
        
    }    
    /**
     * @deprecated 
     * @param s
     * @return 
     */
    public static javafx.scene.input.KeyCombination getFxKeyComb(String s) {
        return null;
    }

    /**
     * @deprecated 
     * @param s
     * @return 
     */
    public static int getMapKey(String s) {       
        //throw error if s.length() > 1?
        return s.codePointAt(0); //the ascii value
    }
}
