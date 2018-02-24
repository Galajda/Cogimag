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
 *
 * @author MichalG HP Envy
 */
public abstract class KeyMap {
 
    public static HashMap<Integer, CharConstruction> KEY_MAP = makeMap();
    
    public static HashMap makeMap() {
        System.out.println("parent key map static method");    
        HashMap<Integer, CharConstruction> map = new HashMap<>();
        
        return map;
    }
    
    
    public CharConstruction getCharCon(char c) {
//        return KEY_MAP.get(Character.toString(c).codePointAt(0));
        throw new java.lang.NullPointerException();

    }
    
    public CharConstruction getCharCon(int ascii_code) {
//        return KEY_MAP.get(ascii_code);
        throw new java.lang.NullPointerException();
    }
    
}
