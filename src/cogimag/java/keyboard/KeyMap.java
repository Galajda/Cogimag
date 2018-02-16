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

import java.util.HashMap;
import java.awt.event.KeyEvent;

/**
 *Credit to SO 6802483 and 26999663. 
 * @author Michal G. <Michal.G at cogitatummagnumtelae.com>
 */
public class KeyMap {
    
    public static final HashMap<Integer, CharConstruction> charMap = MakeMap();
    
    
    public static HashMap MakeMap() {
        System.out.println("parent static method");
        
        HashMap<Integer, CharConstruction> map = new HashMap<>();

        map.put(33, new CharConstruction("!", KeyEvent.VK_1, true));
        map.put(34, new CharConstruction("\"", KeyEvent.VK_QUOTE, true));
        map.put(35, new CharConstruction("#", KeyEvent.VK_3, true));

//        }
        return map;
    }
    public void inheritMe() {
        
    }
}
