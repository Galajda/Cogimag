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
 * Contains a single object, a HashMap that simplifies programmed typing of letters 
 * in any editable text input field on the client's screen.
 * Credit for static initializer technique to SO 6802483 and 26999663. 
 * @author Michal G. <Michal.G at cogitatummagnumtelae.com>
 */
public class KeyMap_EN_US {    
    /**
     * A map of all the ASCII printable characters. Key is the ASCII code for the character.
     * Value is a CharConstruction object containing the details of that character's 
     * construction--its text representation, the base key used to type it and a flag
     * if Shift must be pressed in conjunction.
     */
    public static final HashMap<Integer, CharConstruction> KEY_MAP = makeMap();
    
    /**
     * Loaded manually using the MapGenerator tool.
     * @return KEY_MAP
     * @see #KEY_MAP
     */
    public static HashMap makeMap() {
//        System.out.println("parent static method");        
        HashMap<Integer, CharConstruction> map = new HashMap<>();
        
        map.put(49, new CharConstruction("1", KeyEvent.VK_1, false));
        map.put(50, new CharConstruction("2", KeyEvent.VK_2, false));
        map.put(51, new CharConstruction("3", KeyEvent.VK_3, false));
        map.put(52, new CharConstruction("4", KeyEvent.VK_4, false));
        map.put(53, new CharConstruction("5", KeyEvent.VK_5, false));
        map.put(54, new CharConstruction("6", KeyEvent.VK_6, false));
        map.put(55, new CharConstruction("7", KeyEvent.VK_7, false));
        map.put(56, new CharConstruction("8", KeyEvent.VK_8, false));
        map.put(57, new CharConstruction("9", KeyEvent.VK_9, false));
        map.put(48, new CharConstruction("0", KeyEvent.VK_0, false));
        map.put(45, new CharConstruction("-", KeyEvent.VK_MINUS, false));
        map.put(61, new CharConstruction("=", KeyEvent.VK_EQUALS, false));
        map.put(33, new CharConstruction("!", KeyEvent.VK_1, true));
        map.put(64, new CharConstruction("@", KeyEvent.VK_2, true));
        map.put(35, new CharConstruction("#", KeyEvent.VK_3, true));
        map.put(36, new CharConstruction("$", KeyEvent.VK_4, true));
        map.put(37, new CharConstruction("%", KeyEvent.VK_5, true));
        map.put(94, new CharConstruction("^", KeyEvent.VK_6, true));
        map.put(38, new CharConstruction("&", KeyEvent.VK_7, true));
        map.put(42, new CharConstruction("*", KeyEvent.VK_8, true));
        map.put(40, new CharConstruction("(", KeyEvent.VK_9, true));
        map.put(41, new CharConstruction(")", KeyEvent.VK_0, true));
        map.put(95, new CharConstruction("_", KeyEvent.VK_MINUS, true));
        map.put(43, new CharConstruction("+", KeyEvent.VK_EQUALS, true));
        map.put(113, new CharConstruction("q", KeyEvent.VK_Q, false));
        map.put(119, new CharConstruction("w", KeyEvent.VK_W, false));
        map.put(101, new CharConstruction("e", KeyEvent.VK_E, false));
        map.put(114, new CharConstruction("r", KeyEvent.VK_R, false));
        map.put(116, new CharConstruction("t", KeyEvent.VK_T, false));
        map.put(121, new CharConstruction("y", KeyEvent.VK_Y, false));
        map.put(117, new CharConstruction("u", KeyEvent.VK_U, false));
        map.put(105, new CharConstruction("i", KeyEvent.VK_I, false));
        map.put(111, new CharConstruction("o", KeyEvent.VK_O, false));
        map.put(112, new CharConstruction("p", KeyEvent.VK_P, false));
        map.put(91, new CharConstruction("[", KeyEvent.VK_OPEN_BRACKET, false));
        map.put(93, new CharConstruction("]", KeyEvent.VK_CLOSE_BRACKET, false));
        map.put(92, new CharConstruction("\\", KeyEvent.VK_BACK_SLASH, false));
        map.put(81, new CharConstruction("Q", KeyEvent.VK_Q, true));
        map.put(87, new CharConstruction("W", KeyEvent.VK_W, true));
        map.put(69, new CharConstruction("E", KeyEvent.VK_E, true));
        map.put(82, new CharConstruction("R", KeyEvent.VK_R, true));
        map.put(84, new CharConstruction("T", KeyEvent.VK_T, true));
        map.put(89, new CharConstruction("Y", KeyEvent.VK_Y, true));
        map.put(85, new CharConstruction("U", KeyEvent.VK_U, true));
        map.put(73, new CharConstruction("I", KeyEvent.VK_I, true));
        map.put(79, new CharConstruction("O", KeyEvent.VK_O, true));
        map.put(80, new CharConstruction("P", KeyEvent.VK_P, true));
        map.put(123, new CharConstruction("{", KeyEvent.VK_OPEN_BRACKET, true));
        map.put(125, new CharConstruction("}", KeyEvent.VK_CLOSE_BRACKET, true));
        map.put(124, new CharConstruction("|", KeyEvent.VK_BACK_SLASH, true));
        map.put(97, new CharConstruction("a", KeyEvent.VK_A, false));
        map.put(115, new CharConstruction("s", KeyEvent.VK_S, false));
        map.put(100, new CharConstruction("d", KeyEvent.VK_D, false));
        map.put(102, new CharConstruction("f", KeyEvent.VK_F, false));
        map.put(103, new CharConstruction("g", KeyEvent.VK_G, false));
        map.put(104, new CharConstruction("h", KeyEvent.VK_H, false));
        map.put(106, new CharConstruction("j", KeyEvent.VK_J, false));
        map.put(107, new CharConstruction("k", KeyEvent.VK_K, false));
        map.put(108, new CharConstruction("l", KeyEvent.VK_L, false));
        map.put(59, new CharConstruction(";", KeyEvent.VK_SEMICOLON, false));
        map.put(39, new CharConstruction("'", KeyEvent.VK_QUOTE, false));
        map.put(65, new CharConstruction("A", KeyEvent.VK_A, true));
        map.put(83, new CharConstruction("S", KeyEvent.VK_S, true));
        map.put(68, new CharConstruction("D", KeyEvent.VK_D, true));
        map.put(70, new CharConstruction("F", KeyEvent.VK_F, true));
        map.put(71, new CharConstruction("G", KeyEvent.VK_G, true));
        map.put(72, new CharConstruction("H", KeyEvent.VK_H, true));
        map.put(74, new CharConstruction("J", KeyEvent.VK_J, true));
        map.put(75, new CharConstruction("K", KeyEvent.VK_K, true));
        map.put(76, new CharConstruction("L", KeyEvent.VK_L, true));
        map.put(58, new CharConstruction(":", KeyEvent.VK_SEMICOLON, true));
        map.put(34, new CharConstruction("\"", KeyEvent.VK_QUOTE, true));
        map.put(122, new CharConstruction("z", KeyEvent.VK_Z, false));
        map.put(120, new CharConstruction("x", KeyEvent.VK_X, false));
        map.put(99, new CharConstruction("c", KeyEvent.VK_C, false));
        map.put(118, new CharConstruction("v", KeyEvent.VK_V, false));
        map.put(98, new CharConstruction("b", KeyEvent.VK_B, false));
        map.put(110, new CharConstruction("n", KeyEvent.VK_N, false));
        map.put(109, new CharConstruction("m", KeyEvent.VK_M, false));
        map.put(44, new CharConstruction(",", KeyEvent.VK_COMMA, false));
        map.put(46, new CharConstruction(".", KeyEvent.VK_PERIOD, false));
        map.put(47, new CharConstruction("/", KeyEvent.VK_SLASH, false));
        map.put(90, new CharConstruction("Z", KeyEvent.VK_Z, true));
        map.put(88, new CharConstruction("X", KeyEvent.VK_X, true));
        map.put(67, new CharConstruction("C", KeyEvent.VK_C, true));
        map.put(86, new CharConstruction("V", KeyEvent.VK_V, true));
        map.put(66, new CharConstruction("B", KeyEvent.VK_B, true));
        map.put(78, new CharConstruction("N", KeyEvent.VK_N, true));
        map.put(77, new CharConstruction("M", KeyEvent.VK_M, true));
        map.put(60, new CharConstruction("<", KeyEvent.VK_COMMA, true));
        map.put(62, new CharConstruction(">", KeyEvent.VK_PERIOD, true));
        map.put(63, new CharConstruction("?", KeyEvent.VK_SLASH, true));
        
        //map generator produces this for alt-0233. map tester says it fails
//        map.put(233, new CharConstruction("é", KeyEvent.VK_NUMPAD3, false));

        
        return map;
    }
    
    
    public static CharConstruction getCharCon(char c) {
        return KeyMap_EN_US.KEY_MAP.get(Character.toString(c).codePointAt(0));
    }
    
    public static CharConstruction getCharCon(int ascii_code) {
        return KeyMap_EN_US.KEY_MAP.get(ascii_code);
    }
}
