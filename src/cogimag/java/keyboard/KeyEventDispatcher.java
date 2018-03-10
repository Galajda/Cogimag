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
package cogimag.java.keyboard;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 * Adapted from MadProgrammer's example in 
 * <a href='https://stackoverflow.com/questions/14572270/how-can-i-perfectly-simulate-keyevents'>
 * Stack Overflow 14572270</a>
 * @author MichalG HP Envy
 */
public class KeyEventDispatcher {
    public static void fireEvent(KeyMap map, int ascii_number) {
//        System.out.println("firing key event");
        CharConstruction charCon = map.getCharCon(ascii_number);
        if (EventQueue.isDispatchThread()) {
//            System.out.println("on dispatch thread");    
            List<KeyEvent> events = new ArrayList<>();
            events.clear();
            int modifier = (charCon.isShifted) ? (KeyEvent.VK_SHIFT) : 0;            
            KeyEvent ke;
            ke = new KeyEvent(new javax.swing.JPanel(),KeyEvent.KEY_PRESSED,System.currentTimeMillis(),modifier,charCon.keyEventConstant,charCon.rendering.charAt(0));
            events.add(ke);
            ke = new KeyEvent(new javax.swing.JPanel(),KeyEvent.KEY_RELEASED,System.currentTimeMillis(),modifier,charCon.keyEventConstant,charCon.rendering.charAt(0));
            events.add(ke);
            ke = new KeyEvent(new javax.swing.JPanel(),KeyEvent.KEY_TYPED,System.currentTimeMillis(),modifier,KeyEvent.VK_UNDEFINED,charCon.rendering.charAt(0));
            events.add(ke);
            
            for (KeyEvent event : events) {
                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(event);
            }
        }
        else {
            System.out.println("waiting to post event");
            try {
                SwingUtilities.invokeAndWait(
                        new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("invoke and wait");
                                fireEvent(map, ascii_number);
                            }               
                        }
                );
            }
            catch (Exception ex) {
                System.out.println("could not invoke and wait");
            }
        }
    }
    public static void fireEvent(KeyMap map, String s) {
        for (int i=0;i<s.length();i++) {
            //when checking for \, ensure that there is a char following the \
            if ((i<(s.length()-1)) && (s.codePointAt(i)==Character.toString('\\').codePointAt(0))) {
//                System.out.println("found an escape char");
                //examine the letter following the \
                switch (s.codePointAt(++i)) {
                    //little is to be gained from exploiting fallthrough. 
                    //leave the cases separate for clarity.
                    case '\\':
//                        fireEvent(map, ((KeyMap)map).getAsciiNumber("\\"));
                        //what is the value of casting map to its parent?
//                        fireEvent(map, map.getAsciiNumber("\\")); //this works  
                        fireEvent(map, '\\');
                        break;
                    case 't':
//                        fireEvent(map, ((KeyMap)map).getAsciiNumber("\t"));
                        fireEvent(map, '\t');
                        break;                    
                    case '"':
//                        fireEvent(map, ((KeyMap)map).getAsciiNumber("\""));
                        fireEvent(map, '\"');
                        break;                       
                   case 'n':
//                        fireEvent(map, ((KeyMap)map).getAsciiNumber("\n"));
                        fireEvent(map, '\n');
                        break;
                    default:
                        //throw error, or just plod on like JavaScript?
                        //currently, the \ and the following char are ignored
                        //if the following char is not one of the above
                }
            }
            else {
                fireEvent(map, s.codePointAt(i));
            }
        }
    }
}
