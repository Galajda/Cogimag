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
package cogimag.javafx.keyboard;

import javafx.event.Event;
import javafx.event.EventTarget;
//import javafx.scene.control.FocusModel;
import javafx.scene.input.KeyEvent;
/**
 *
 * @author MichalG HP Envy
 */
public class FxKeyEventSteno {
    
    public static void fireEvent(EventTarget event_target, FxKeyMap map, int ascii_number) {
        FxCharConstruction charCon = map.getCharCon(ascii_number);
        
        KeyEvent keyEvent;
        keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, charCon.displayedCharacter, charCon.strKeyCode, charCon.intKeyCode, charCon.isShifted,
            false, false, false);
        Event.fireEvent(event_target, keyEvent);
        
        keyEvent = new KeyEvent(KeyEvent.KEY_RELEASED, charCon.displayedCharacter, charCon.strKeyCode, charCon.intKeyCode, charCon.isShifted,
            false, false, false);
        Event.fireEvent(event_target, keyEvent);
        
        keyEvent = new KeyEvent(KeyEvent.KEY_TYPED, charCon.displayedCharacter, charCon.strKeyCode, charCon.intKeyCode, charCon.isShifted,
            false, false, false);
        Event.fireEvent(event_target, keyEvent);
        
    }
    
    public static void fireEvent(EventTarget event_target, FxKeyMap map, String s) {
        for (int i=0;i<s.length();i++) {
            //when checking for \, ensure that there is a char following the \
            if ((i<(s.length()-1)) && (s.codePointAt(i)==Character.toString('\\').codePointAt(0))) {
//                System.out.println("found an escape char");
                //examine the letter following the \
                switch (s.codePointAt(++i)) {
                    //little is to be gained from exploiting fallthrough. 
                    //leave the cases separate for clarity.
                    case '\\':
//                        fireEvent(map, ((AwtKeyMap)map).getAsciiNumber("\\"));
                        //what is the value of casting map to its parent?
//                        fireEvent(map, map.getAsciiNumber("\\")); //this works  
                        fireEvent(event_target, map, '\\');
                        break;
                    case 't':
//                        fireEvent(map, ((AwtKeyMap)map).getAsciiNumber("\t"));
                        fireEvent(event_target, map, '\t');
                        break;                    
                    case '"':
//                        fireEvent(map, ((AwtKeyMap)map).getAsciiNumber("\""));
                        fireEvent(event_target, map, '\"');
                        break;                       
                   case 'n':
//                        fireEvent(map, ((AwtKeyMap)map).getAsciiNumber("\n"));
                        fireEvent(event_target, map, '\n');
                        break;
                    default:
                        //throw error, or just plod on like JavaScript?
                        //currently, the \ and the following char are ignored
                        //if the following char is not one of the above
                }
            }
            else {
                fireEvent(event_target, map, s.codePointAt(i));
            }
        }
        
    }
}
