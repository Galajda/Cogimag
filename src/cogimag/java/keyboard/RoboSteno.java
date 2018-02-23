
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cogimag.java.keyboard;

//import com.sun.jna.Native;
import java.awt.Robot;
import java.awt.AWTException;
//import java.lang.SecurityException;
import java.awt.event.KeyEvent;
//import static java.lang.System.out;
//import javafx.scene.input.KeyCode;

/**
 *
 * @author MichalG HP Envy
 */
public class RoboSteno {
    
    private static Robot r;
    private static boolean isRobotOnline;    
    private static KeyMap map;
    
    public RoboSteno(KeyMap m) {
        map = m;
        isRobotOnline = true;
        try {
            r = new Robot();
        }        
        catch (AWTException | SecurityException ex)
        {
            System.out.println("cannot initialize robot" + ex.getMessage());
            isRobotOnline = false;
        }
    }
    /**
     * Fundamental typing method. Overloaded methods call this to print characters on the screen.
     * Method checks for availability of the Robot. If offline, no chars are typed, no error
     * is thrown.
     * @param charCon 
     */
    public void type(CharConstruction charCon) {
        if (isRobotOnline) {            
            if (charCon.isShifted) { r.keyPress(KeyEvent.VK_SHIFT); }
            r.keyPress(charCon.keyEventConstant);
            r.keyRelease(charCon.keyEventConstant);
            if (charCon.isShifted) { r.keyRelease(KeyEvent.VK_SHIFT); }            
            r.delay(10);
        }        
    }
    
    public void type(char c) {
        type(map.getCharCon(c));
//        return true;
    }
//    public static void type(int ascii) {
////        return true;
//    }
    public void type(String s) {
        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            type(map.getCharCon(c));
        }
//        return true;
    }
    
    public void testAccent() {
        if (isRobotOnline) {            
            
            r.keyPress(KeyEvent.VK_ALT);
            r.keyPress(KeyEvent.VK_0);
            r.keyRelease(KeyEvent.VK_0);
            r.keyPress(KeyEvent.VK_2);
            r.keyRelease(KeyEvent.VK_2);
            r.keyPress(KeyEvent.VK_3);
            r.keyRelease(KeyEvent.VK_3);
            r.keyPress(KeyEvent.VK_3);
            r.keyRelease(KeyEvent.VK_3);
            r.keyRelease(KeyEvent.VK_ALT);
            
            r.delay(1000);
        }        
    }
//    private static int getVkCode(char c) {
////        map.KEY_MAP.get(c);
//        CharConstruction charCon = map.KEY_MAP.get(Character.toString(c).codePointAt(0));
//        return charCon.keyEventConstant;
//    }
    
//    private static int getVkCode(int ascii_code) {
//        CharConstruction charCon = map.KEY_MAP.get(ascii_code);
//        return charCon.keyEventConstant;
//    }
    
//    public void type(int vk_code) {
//        r.keyPress(vk_code);
//        r.keyRelease(vk_code);
//        r.delay(10);
////        return true;
//    }
}
