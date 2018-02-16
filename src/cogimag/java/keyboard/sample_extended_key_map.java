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

import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Demonstration of extending the built-in KeyMap with a map for a different keyboard.
 * The parent's static method runs first. The child's static method erases and overwrites 
 * the values put by the parent. The HashMap of this class will have the key-value pairs
 * generated by the developer's keyboard.
 * @author MichalG HP Envy
 */
public class sample_extended_key_map extends KeyMap {
    public static HashMap MakeMap() {        
//        System.out.println("child static method");
        HashMap<Integer, CharConstruction> map = new HashMap<>();

        map.put(33, new CharConstruction("1", KeyEvent.VK_1, false));
//        map.put(34, new CharConstruction("\"", KeyEvent.VK_QUOTE, true));

//        }
        return map;
    }
}
