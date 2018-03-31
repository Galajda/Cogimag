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
package cogimag.javafx.keyboard;

import java.util.HashMap;
import javafx.scene.input.KeyCode;
/**
 *
 * @author MichalG HP Envy
 */
public class FxKeyMap_EN_US extends FxKeyMap {
/**    
     * Intentionally hides the HashMap of the parent class
     */    
    public static final HashMap<Integer, FxCharConstruction> KEY_MAP = makeMap();
    /**
     * Put statements must be pasted manually using the MapGenerator tool.
     * Credit for static initializer technique to SO 6802483 and 26999663. 
     * @return KEY_MAP     
     */

    public static HashMap<Integer, FxCharConstruction> makeMap() {
//        System.out.println("en-us static method");        
        
        HashMap<Integer, FxCharConstruction> map = new HashMap<>();
        
        //manually added                
        map.put(10, new FxCharConstruction("\n", "\n", KeyCode.ENTER, false));
        map.put(9, new FxCharConstruction("\t", "\t", KeyCode.TAB, false));
        
        //generated by MapGenerator
        //row 1, not shifted
        map.put(49, new FxCharConstruction("1", "1", KeyCode.DIGIT1, false));
        map.put(50, new FxCharConstruction("2", "2", KeyCode.DIGIT2, false));
        map.put(51, new FxCharConstruction("3", "3", KeyCode.DIGIT3, false));
        map.put(52, new FxCharConstruction("4", "4", KeyCode.DIGIT4, false));
        map.put(53, new FxCharConstruction("5", "5", KeyCode.DIGIT5, false));
        map.put(54, new FxCharConstruction("6", "6", KeyCode.DIGIT6, false));
        map.put(55, new FxCharConstruction("7", "7", KeyCode.DIGIT7, false));
        map.put(56, new FxCharConstruction("8", "8", KeyCode.DIGIT8, false));
        map.put(57, new FxCharConstruction("9", "9", KeyCode.DIGIT9, false));
        map.put(48, new FxCharConstruction("0", "0", KeyCode.DIGIT0, false));
        map.put(45, new FxCharConstruction("-", "-", KeyCode.MINUS, false));
        map.put(61, new FxCharConstruction("=", "=", KeyCode.EQUALS, false));
        //row 1, shifted
        map.put(33, new FxCharConstruction("!", "1", KeyCode.DIGIT1, true));
	map.put(64, new FxCharConstruction("@", "2", KeyCode.DIGIT2, true));
	map.put(35, new FxCharConstruction("#", "3", KeyCode.DIGIT3, true));
	map.put(36, new FxCharConstruction("$", "4", KeyCode.DIGIT4, true));
	map.put(37, new FxCharConstruction("%", "5", KeyCode.DIGIT5, true));
	map.put(94, new FxCharConstruction("^", "6", KeyCode.DIGIT6, true));
	map.put(38, new FxCharConstruction("&", "7", KeyCode.DIGIT7, true));
	map.put(42, new FxCharConstruction("*", "8", KeyCode.DIGIT8, true));
	map.put(40, new FxCharConstruction("(", "9", KeyCode.DIGIT9, true));
	map.put(41, new FxCharConstruction(")", "0", KeyCode.DIGIT0, true));
	map.put(95, new FxCharConstruction("_", "-", KeyCode.MINUS, true));
	map.put(43, new FxCharConstruction("+", "=", KeyCode.EQUALS, true));
        //row 2, not shifted
        map.put(113, new FxCharConstruction("q", "q", KeyCode.Q, false));
	map.put(119, new FxCharConstruction("w", "w", KeyCode.W, false));
	map.put(101, new FxCharConstruction("e", "e", KeyCode.E, false));
	map.put(114, new FxCharConstruction("r", "r", KeyCode.R, false));
	map.put(116, new FxCharConstruction("t", "t", KeyCode.T, false));
	map.put(121, new FxCharConstruction("y", "y", KeyCode.Y, false));
	map.put(117, new FxCharConstruction("u", "u", KeyCode.U, false));
	map.put(105, new FxCharConstruction("i", "i", KeyCode.I, false));
	map.put(111, new FxCharConstruction("o", "o", KeyCode.O, false));
	map.put(112, new FxCharConstruction("p", "p", KeyCode.P, false));
	map.put(91, new FxCharConstruction("[", "[", KeyCode.OPEN_BRACKET, false));
	map.put(93, new FxCharConstruction("]", "]", KeyCode.CLOSE_BRACKET, false));
        map.put(92, new FxCharConstruction("\\", "\\", KeyCode.BACK_SLASH, false)); //manually corrected
        //row 2, shifted
	map.put(81, new FxCharConstruction("Q", "q", KeyCode.Q, true));
	map.put(87, new FxCharConstruction("W", "w", KeyCode.W, true));
	map.put(69, new FxCharConstruction("E", "e", KeyCode.E, true));
	map.put(82, new FxCharConstruction("R", "r", KeyCode.R, true));
	map.put(84, new FxCharConstruction("T", "t", KeyCode.T, true));
	map.put(89, new FxCharConstruction("Y", "y", KeyCode.Y, true));
	map.put(85, new FxCharConstruction("U", "u", KeyCode.U, true));
	map.put(73, new FxCharConstruction("I", "i", KeyCode.I, true));
	map.put(79, new FxCharConstruction("O", "o", KeyCode.O, true));
	map.put(80, new FxCharConstruction("P", "p", KeyCode.P, true));
	map.put(123, new FxCharConstruction("{", "[", KeyCode.OPEN_BRACKET, true));
	map.put(125, new FxCharConstruction("}", "]", KeyCode.CLOSE_BRACKET, true));
	map.put(124, new FxCharConstruction("|", "\\", KeyCode.BACK_SLASH, true)); //manually corrected    
        //row 3, not shifted
        map.put(97, new FxCharConstruction("a", "a", KeyCode.A, false));
	map.put(115, new FxCharConstruction("s", "s", KeyCode.S, false));
	map.put(100, new FxCharConstruction("d", "d", KeyCode.D, false));
	map.put(102, new FxCharConstruction("f", "f", KeyCode.F, false));
	map.put(103, new FxCharConstruction("g", "g", KeyCode.G, false));
	map.put(104, new FxCharConstruction("h", "h", KeyCode.H, false));
	map.put(106, new FxCharConstruction("j", "j", KeyCode.J, false));
	map.put(107, new FxCharConstruction("k", "k", KeyCode.K, false));
	map.put(108, new FxCharConstruction("l", "l", KeyCode.L, false));
	map.put(59, new FxCharConstruction(";", ";", KeyCode.SEMICOLON, false));
	map.put(39, new FxCharConstruction("'", "'", KeyCode.QUOTE, false));
        //row 3, shifted
	map.put(65, new FxCharConstruction("A", "a", KeyCode.A, true));
	map.put(83, new FxCharConstruction("S", "s", KeyCode.S, true));
	map.put(68, new FxCharConstruction("D", "d", KeyCode.D, true));
	map.put(70, new FxCharConstruction("F", "f", KeyCode.F, true));
	map.put(71, new FxCharConstruction("G", "g", KeyCode.G, true));
	map.put(72, new FxCharConstruction("H", "h", KeyCode.H, true));
	map.put(74, new FxCharConstruction("J", "j", KeyCode.J, true));
	map.put(75, new FxCharConstruction("K", "k", KeyCode.K, true));
	map.put(76, new FxCharConstruction("L", "l", KeyCode.L, true));
	map.put(58, new FxCharConstruction(":", ";", KeyCode.SEMICOLON, true));
	map.put(34, new FxCharConstruction("\"", "'", KeyCode.QUOTE, true)); //manually corrected        
        //row 4, not shifted
	map.put(122, new FxCharConstruction("z", "z", KeyCode.Z, false));
	map.put(120, new FxCharConstruction("x", "x", KeyCode.X, false));
	map.put(99, new FxCharConstruction("c", "c", KeyCode.C, false));
	map.put(118, new FxCharConstruction("v", "v", KeyCode.V, false));
	map.put(98, new FxCharConstruction("b", "b", KeyCode.B, false));
	map.put(110, new FxCharConstruction("n", "n", KeyCode.N, false));
	map.put(109, new FxCharConstruction("m", "m", KeyCode.M, false));
	map.put(44, new FxCharConstruction(",", ",", KeyCode.COMMA, false));
	map.put(46, new FxCharConstruction(".", ".", KeyCode.PERIOD, false));
	map.put(47, new FxCharConstruction("/", "/", KeyCode.SLASH, false));        
        //row 4, shifted
	map.put(90, new FxCharConstruction("Z", "z", KeyCode.Z, true));
	map.put(88, new FxCharConstruction("X", "x", KeyCode.X, true));
	map.put(67, new FxCharConstruction("C", "c", KeyCode.C, true));
	map.put(86, new FxCharConstruction("V", "v", KeyCode.V, true));
	map.put(66, new FxCharConstruction("B", "b", KeyCode.B, true));
	map.put(78, new FxCharConstruction("N", "n", KeyCode.N, true));
	map.put(77, new FxCharConstruction("M", "m", KeyCode.M, true));
	map.put(60, new FxCharConstruction("<", ",", KeyCode.COMMA, true));
	map.put(62, new FxCharConstruction(">", ".", KeyCode.PERIOD, true));	
	map.put(63, new FxCharConstruction("?", "/", KeyCode.SLASH, true));        
        //row 5
	map.put(32, new FxCharConstruction(" ", " ", KeyCode.SPACE, false));
        
        

        return map;
    }
    
    
    @Override
    public FxCharConstruction getCharCon(char c) {
        return FxKeyMap_EN_US.KEY_MAP.get(Character.toString(c).codePointAt(0));
    }
    
    @Override
    public FxCharConstruction getCharCon(int ascii_code) {
        return FxKeyMap_EN_US.KEY_MAP.get(ascii_code);
    }    

}
