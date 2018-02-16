/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cogimag.java.keyboard;

import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * The parent's static method runs first. The child's static method overwrites the
 * values put by the parent.
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
