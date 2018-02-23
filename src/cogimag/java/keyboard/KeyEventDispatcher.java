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
 * Adapted from SO 14572270
 * @author MichalG HP Envy
 */
public class KeyEventDispatcher {
    public static void fireEvent(int ascii_number) {
        System.out.println("firing key event");
        CharConstruction charCon = KeyMap.getCharCon(ascii_number);
        if (EventQueue.isDispatchThread()) {
            System.out.println("on dispatch thread");    
            List<KeyEvent> events = new ArrayList<>();
//            List<Integer> modifiers = new ArrayList<>();
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
                        fireEvent(ascii_number);
                    }
                            
                        }
                );
            }
            catch (Exception ex) {
                System.out.println("could not invoke and wait");
            }
        }
    }
}
