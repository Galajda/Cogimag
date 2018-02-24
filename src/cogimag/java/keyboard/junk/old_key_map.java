/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cogimag.java.keyboard.junk;

import cogimag.java.keyboard.CharConstruction;
import java.awt.event.KeyEvent;

/**
 *
 * @author MichalG HP Envy
 */
public class old_key_map {
    
    
//    public static final String KEY_EXCL = "!";
    private static final int[] mapKeyArray = makeMapKeyArray();
    private static final CharConstruction[] mapValueArray = makeMapValueArray() ;
    
    private static int[] makeMapKeyArray() {
        return new int[]{32, 33, 34, 35};
    }
    private static CharConstruction[] makeMapValueArray() {
        CharConstruction[] array = null ;
        array[0] = new CharConstruction(" ", KeyEvent.VK_SPACE, false);
        array[1] = new CharConstruction("!", KeyEvent.VK_1, true);
        array[2] = new CharConstruction("\"", KeyEvent.VK_3, true);
        array[3] = new CharConstruction("#", KeyEvent.VK_3, true);

        return array;
    }
    
}
