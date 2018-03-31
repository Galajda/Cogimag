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

import java.util.HashMap;
import javafx.scene.input.KeyCode;
//import cogimag.javafx.keyboard.FxCharConstruction;



/**
 * Base class for language-specific subclasses of the keyboard mapping. Do not
 * use this class directly. Contains a single object, a {@link HashMap} that 
 * simplifies programmed typing of letters in any editable text input field on 
 * the client's screen. 
 * @author MichalG HP Envy
 */
public abstract class FxKeyMap {
    /**
 * A map of all the ASCII printable characters. Key is the ASCII code for the 
 character. Value is a FxCharConstruction object containing the details of that 
 character's construction--its text representation, the base key used to type 
 it and a flag if Shift must be pressed in conjunction.
 */
    public static final HashMap<Integer, FxCharConstruction> KEY_MAP = makeMap();
    
    /**
     * Base method returns empty map.
     * @return the map of ASCII codes and their FxCharConstruction objects
     */
    private static HashMap<Integer, FxCharConstruction> makeMap() {
//        System.out.println("parent key map static method");    
        HashMap<Integer, FxCharConstruction> map = new HashMap<>();
        
        //add these to the statements from MapGenerator
//        map.put(10, new FxCharConstruction("\n", KeyEvent.VK_ENTER, false));
//        map.put(9, new FxCharConstruction("\t", KeyEvent.VK_TAB, false));
//        map.put(92, new FxCharConstruction("\\", KeyEvent.VK_BACK_SLASH, false));
//        map.put(34, new FxCharConstruction("\"", KeyEvent.VK_QUOTE, true));
        
        
        return map;
    }
    /**
     * Constructor does nothing. KeyMap instance is used to transfer map
 identity between classes. In one imagined case, an application may 
 record the map identity in its settings, and pass this to the RoboSteno,
 which will retrieve FxCharConstruction information from the correct map.
     */
    public FxKeyMap() {
        
    }
    /**
     * Base method throws {@code NullPointerException} because map is empty
     * @param c a single {@code char} from the range of printable characters
     * @return in subclasses, the FxCharConstruction corresponding to the input 
 character, null or other as the designer wishes in the case of no match
     * @throws java.lang.NullPointerException always because the base map is 
     * empty
     */
    public FxCharConstruction getCharCon(char c) throws java.lang.NullPointerException {
        //subclass should return KEY_MAP.get(Character.toString(c).codePointAt(0));
        throw new java.lang.NullPointerException();
    }
    /**
     * Base method throws {@code NullPointerException} because map is empty.
     * Subclasses may decide how to handle value not found case.
     * @param ascii_code an {@code int} from the range of printable characters
     * @return the FxCharConstruction corresponding to the input number, null or 
 other as the designer wishes in the case of no match
     * @throws java.lang.NullPointerException always because the base map is
     * empty
     */
    public FxCharConstruction getCharCon(int ascii_code) throws java.lang.NullPointerException {
        //sublclass should return KEY_MAP.get(ascii_code);
        throw new java.lang.NullPointerException();
    }
    
    /**
     * Converts a string of one typed element into an integer from the ASCII table.
     * @param one_char String for one typed char, which may literally be one
     * unit long, in the case of letters, numbers and punctuation, or may be
     * two units long, in the case of escaped chars \n, \t, \\, \"
     * @return an ASCII decimal number for the character if the character
     * meets the criteria, 0 otherwise
     */
    public static int getAsciiNumber(String one_char) {
        switch (one_char.length()) {
//            case 0:
//                //assume enter key. cr or lf?
//                return 13;

            case 1:
                //TODO: ensure normal char
                
//                System.out.println("firing event for single char");
                return one_char.codePointAt(0);
            case 2:
                //escaped char
                switch (one_char.charAt(1)) {
                    case 't':
                        System.out.println("esc t");
                        return 9;
                    case 'n':
                        System.out.println("esc n");
                        return 10;
                    case '\\':
                        System.out.println("esc backslash");
                        return 92;
                    case '\"':
                        System.out.println("esc dbl quote");
                        return 34;
                    default:
                        return 0;
                }
            default:
                return 0;
        }
    }
}
