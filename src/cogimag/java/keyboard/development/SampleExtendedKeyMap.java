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
package cogimag.java.keyboard.development;

import cogimag.java.keyboard.CharConstruction;
import cogimag.java.keyboard.KeyMap;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Demonstration of extending the built-in KeyMap with a map for a different keyboard.
 * The parent's static method runs first. The child's static method overwrites (does not append)
 * the values put by the parent. The HashMap of this class will have the key-value pairs
 * generated by the developer's keyboard by running {@link MapGenerator}.
 * @author MichalG HP Envy
 */
public class SampleExtendedKeyMap extends KeyMap {

    /**
     * Intentionally hides the HashMap of the parent class
     */
    public static final HashMap<Integer, CharConstruction> KEY_MAP = makeMap();
    
    public static HashMap makeMap() {        
        System.out.println("sample key map static method");
        HashMap<Integer, CharConstruction> map = new HashMap<>();
        //example
        map.put(33, new CharConstruction("1", KeyEvent.VK_1, false));
        //paste MapGenerator output below

        return map;
    }
    /**
     * The parent method must be overridden in order to return the value from
     * the extended class, instead of the value from the base class.
     * @param c 
     * @return   
     */
    @Override
    public CharConstruction getCharCon(char c) {
        return SampleExtendedKeyMap.KEY_MAP.get(Character.toString(c).codePointAt(0));        
    }
    
    @Override
    public CharConstruction getCharCon(int ascii_code) {
        return SampleExtendedKeyMap.KEY_MAP.get(ascii_code);        
    }
}
