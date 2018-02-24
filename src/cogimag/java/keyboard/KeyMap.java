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

import java.util.HashMap;

/**
 * Base class for language-specific subclasses of the keyboard mapping. Do not
 * use this class directly. Contains a single object, a {@link HashMap} that 
 * simplifies programmed typing of letters in any editable text input field on 
 * the client's screen. 
 * @author MichalG HP Envy
 */
public abstract class KeyMap {
 /**
 * A map of all the ASCII printable characters. Key is the ASCII code for the character.
 * Value is a CharConstruction object containing the details of that character's 
 * construction--its text representation, the base key used to type it and a flag
 * if Shift must be pressed in conjunction.
 */
    public static final HashMap<Integer, CharConstruction> KEY_MAP = makeMap();
    
    public static HashMap makeMap() {
        System.out.println("parent key map static method");    
        HashMap<Integer, CharConstruction> map = new HashMap<>();
        
        return map;
    }
    
    public CharConstruction getCharCon(char c) {
        //subclass should return KEY_MAP.get(Character.toString(c).codePointAt(0));
        throw new java.lang.NullPointerException();
    }
    
    public CharConstruction getCharCon(int ascii_code) {
        //sublclass should return KEY_MAP.get(ascii_code);
        throw new java.lang.NullPointerException();
    }    
}
