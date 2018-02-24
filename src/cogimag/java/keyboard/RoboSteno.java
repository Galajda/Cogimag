
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
 * Adaptation of {@link Robot} for use with the KeyMap in this package
 * @author MichalG HP Envy
 */
public class RoboSteno {
    
    private static Robot r;
    private static boolean isRobotOnline;    
    private static KeyMap map;
    
    /**
     * The robot must be linked to a KeyMap in order to look up the correct
     * CharConstruction object 
     * @param m 
     */
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
     * Fundamental typing method. Overloaded methods call this to print characters
     * on the screen. Method checks for availability of the Robot. If offline, 
     * no chars are typed, no Exception is thrown.
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
    }
    
    public void type(String s) {
        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            type(map.getCharCon(c));
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
