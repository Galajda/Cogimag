/*
 * Copyright (C) 2018 Michal G. <Michal.G at cogitatummagnumtelae.com>
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

/**
 * 
 * @author Michal G. <Michal.G at cogitatummagnumtelae.com>
 */
public class CharConstruction {
    
//        int asciiCode;
    /**
     * the appearance of the character on the screen, e.g. a, A, 1, !
     */
    public final String rendering;
    /**
     * the  java.awt.event.KeyEvent.VK_ constant of the base (unshifted) key on the keyboard
     *
     */
    public final int keyEventConstant;
    /**
     * true if shift must be pressed in combination with the base key to get the rendering 
     */
    public final boolean isShifted;
        
//constructor
//        CharConstruction(int ascii_code, int vk_constant, boolean is_shifted) {
    public CharConstruction(String displayed_char, int vk_constant, boolean is_shifted) {
//            asciiCode = ascii_code;
        rendering = displayed_char;
        keyEventConstant = vk_constant;
        isShifted = is_shifted;

    }
    
}
