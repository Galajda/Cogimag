
/*
 * Copyright (C) 2018 Michal G.
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

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 * Adaptation of {@link Robot} for use with the AwtKeyMap in this package
 * @author MichalG HP Envy
 */
public class AwtRoboSteno {
    
    private static Robot r;
    private static boolean isRobotOnline;    
    private static AwtKeyMap map;
    
    /**
     * The robot must be linked to a AwtKeyMap in order to look up the correct
     * AwtCharConstruction object. In contrast to the {@link AwtKeyEventSteno},
     * the map is passed to the constructor, rather than to a static method.
     * Since the constructor is needed for other reasons, the map may as well
     * be passed here. Instantiating a new {@code Robot} for every typing event
     * is deemed extravagant. The application may keep one instance of the 
     * {@code RoboSteno} for the duration. A 10ms delay between keystrokes is
     * built in.
     * @param m the language-specific AwtKeyMap to which characters will be 
     * matched
     */
    public AwtRoboSteno(AwtKeyMap m) {
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
     * Fundamental typing method. Overloaded methods call this to print 
     * characters on the screen. Method checks for availability of the Robot. 
     * If offline, no chars are typed, no Exception is thrown.
     * @param charCon the AwtCharConstruction object representing the character
     * to be typed
     */
    public void type(AwtCharConstruction charCon) {
        if (isRobotOnline) {            
//            System.out.println("robot is typing key " + charCon.rendering);
//            System.out.println(" on thread " + Thread.currentThread());
            if (charCon.isShifted) { r.keyPress(KeyEvent.VK_SHIFT); }
            r.keyPress(charCon.keyEventConstant);
            r.keyRelease(charCon.keyEventConstant);
            if (charCon.isShifted) { r.keyRelease(KeyEvent.VK_SHIFT); }            
            r.delay(10);
        }        
    }
    /**
     * Alternate argument type, depending on the designer's preference
     * @param c a single {@code char} that will be repeated by the Robot
     */
    public void type(char c) {
        type(map.getCharCon(c));
    }
    /**
     * Alternate argument type, depending on the designer's preference
     * @param ascii_number the decimal number of the character, taken from
     * the ASCII table
     */
    public void type(int ascii_number) {
        type(map.getCharCon(ascii_number));
    }
    
    /**
     * Useful for typing longer texts.
     * @param s a String of unspecified length that will be broken up and typed
     * one letter at at time.
     */
    public void type(String s) {
//        System.out.println("robo steno type string got string:" + s + ":");
        char[] charArray = s.toCharArray();
        //cannot use enhanced for loop because I want to increment i within
        //the loop when it encounters a \. I do not know how to increment
        //an enhanced loop counter.
        for (int i=0;i<s.length();i++) {
            //when checking for \, must ensure that there is a char following the \
            if ((i<(s.length()-1)) && (s.codePointAt(i)==Character.toString('\\').codePointAt(0))) {
//                System.out.println("found an escape char");
                //examine the letter following the \
                switch (s.codePointAt(++i)) {
                    case '\\':
//                        type(((AwtKeyMap)map).getAsciiNumber("\\"));
                        type('\\');
                        break;
                    case 't':
//                        type(((AwtKeyMap)map).getAsciiNumber("\t"));
                        type('\t');
                        break;
                    case 'n':
//                        type(((AwtKeyMap)map).getAsciiNumber("\n"));
                        type('\n');
                        break;
                    case '"':
//                        type(((AwtKeyMap)map).getAsciiNumber("\""));
                        type('\"');
                        break;
                    default:
                        //throw error, or just plod on like JavaScript?
                        //currently, the \ and the following char are ignored
                        //if the following char is not one of the above
                }
            }
            else {
//                System.out.println("code point at " + i + " = " + s.codePointAt(i) );                
                try {
                    type(map.getCharCon(s.codePointAt(i)));
                }
                catch (NullPointerException ex) {
                    //skip the char
                }
            }
        }
    }
    
    /**
     * failed experiment to type é
     */
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
}
