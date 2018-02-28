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
 * Object contains three primitives that identify printable characters. Supports
 * automated typing utilities {@link RoboSteno} and {@link KeyEventDispatcher}.
 * @author Michal G.
 */
public class CharConstruction {
    
    /**
     * the appearance of the character on the screen, such as a, A, 1, !
     */
    public final String rendering;
    /**
     * the  java.awt.event.KeyEvent.VK_ constant of the base (unshifted) key on 
     * the keyboard that must be pressed in order to type the character
     */
    public final int keyEventConstant;
    /**
     * true if shift must be pressed in combination with the base key to get 
     * the rendering 
     */
    public final boolean isShifted;
    
    public CharConstruction(String displayed_char, int vk_constant, boolean is_shifted) {
        rendering = displayed_char;
        keyEventConstant = vk_constant;
        isShifted = is_shifted;
    }    
}
