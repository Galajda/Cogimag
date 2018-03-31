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
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 * Adapted from MadProgrammer's example in 
 * <a href='https://stackoverflow.com/questions/14572270/how-can-i-perfectly-simulate-keyevents'>
 * Stack Overflow 14572270</a>
 * @author MichalG HP Envy
 */
public class AwtKeyEventSteno {
    public static void fireEvent(AwtKeyMap map, int ascii_number) {
        Thread thisThread = Thread.currentThread();
//        System.out.println("firing key event on thread " + thisThread);
//        System.out.println("is event queue the dispatch thread? " + EventQueue.isDispatchThread());
//        System.out.println("current event in event queue = " + EventQueue.getCurrentEvent());
        //SO 1323408
//        java.util.Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
//        Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);      
//        Thread eventQueue = threadArray[0];
//        for (int i=0;i<threadSet.size();i++) {
//            System.out.println("thread #" + i + " to string " + threadArray[i].toString() + 
//                    " name " +  " state = " + threadArray[i].getState());
            //threadArray[i].getName()
//            if (threadArray[i].getName().startsWith("AWT-EventQueue")) {
//                eventQueue = threadArray[i];                
//            }
//        }
        
        //the StringTester in this package fires on thread name Thread[AWT-EventQueue-0,6,main]. the current
            //event is the Enter key release in the text field input. thread state runnable
        //the rfid app fires on thread Thread[JavaFX Application Thread,5,main]. awt event queue is listed.
            //there is no current event in the event queue, state is waiting
        AwtCharConstruction charCon = map.getCharCon(ascii_number);
        if (EventQueue.isDispatchThread()) {
//        if (true) {
            System.out.println("on dispatch thread");    
            List<KeyEvent> events = new ArrayList<>();
            events.clear();
            int modifier = (charCon.isShifted) ? (KeyEvent.VK_SHIFT) : 0;            
            KeyEvent ke;
            //SO 720208
            System.out.println("is focus owner null? " + (KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner() == null));
//            System.out.println("focus owner " + KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner().toString());
//            ke = new KeyEvent(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner(),
//                    KeyEvent.KEY_PRESSED,System.currentTimeMillis(),modifier,charCon.keyEventConstant,charCon.rendering.charAt(0));
            ke = new KeyEvent(new javax.swing.JPanel(),KeyEvent.KEY_PRESSED,System.currentTimeMillis(),modifier,charCon.keyEventConstant,charCon.rendering.charAt(0));
            events.add(ke);
//            ke = new KeyEvent(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner(),
//                    KeyEvent.KEY_RELEASED,System.currentTimeMillis(),modifier,charCon.keyEventConstant,charCon.rendering.charAt(0));
            ke = new KeyEvent(new javax.swing.JPanel(),KeyEvent.KEY_RELEASED,System.currentTimeMillis(),modifier,charCon.keyEventConstant,charCon.rendering.charAt(0));
            events.add(ke);
//            ke = new KeyEvent(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner(),
//                    KeyEvent.KEY_TYPED,System.currentTimeMillis(),modifier,KeyEvent.VK_UNDEFINED,charCon.rendering.charAt(0));
            ke = new KeyEvent(new javax.swing.JPanel(),KeyEvent.KEY_TYPED,System.currentTimeMillis(),modifier,KeyEvent.VK_UNDEFINED,charCon.rendering.charAt(0));
            events.add(ke);            
            for (KeyEvent event : events) {
                System.out.println("posting event for key code " + event.getKeyCode());
                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(event);                
            }
            
        }
        else {
            System.out.println("waiting to post event");
            try {
//                SwingUtilities.invokeAndWait(
                    SwingUtilities.invokeLater(
                        new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("invoke and wait");
                                fireEvent(map, ascii_number);
                            }               
                        }
                );
            }
            catch (Exception ex) {
                System.out.println("could not invoke and wait");
            }
        }
    }
    public static void fireEvent(AwtKeyMap map, String s) {
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
                        fireEvent(map, '\\');
                        break;
                    case 't':
//                        fireEvent(map, ((AwtKeyMap)map).getAsciiNumber("\t"));
                        fireEvent(map, '\t');
                        break;                    
                    case '"':
//                        fireEvent(map, ((AwtKeyMap)map).getAsciiNumber("\""));
                        fireEvent(map, '\"');
                        break;                       
                   case 'n':
//                        fireEvent(map, ((AwtKeyMap)map).getAsciiNumber("\n"));
                        fireEvent(map, '\n');
                        break;
                    default:
                        //throw error, or just plod on like JavaScript?
                        //currently, the \ and the following char are ignored
                        //if the following char is not one of the above
                }
            }
            else {
                fireEvent(map, s.codePointAt(i));
            }
        }
    }
}
