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

import javafx.scene.input.KeyCode;
/**
 * 
 * Object contains four primitives that identify printable characters. Supports
 * automated typing utility {@link FxKeyEventSteno}. Because that class uses
 * {@link javafx.scene.input.KeyEvent}, this object collects the parameters
 * needed to construct a {@code KeyEvent} and fire it via {@link javafx.event.Event}.
 * The field variables are final, being set once in the constructor and accessed
 * (read-only) directly thereafter.
 * @author MichalG HP Envy
 */
public class FxCharConstruction {
    /**
     * the appearance of the character on the screen, such as a, A, 1, !
     */
    public final String displayedCharacter;
    /**
     * The String representation of the FX KeyCode, such as a or 1.
     * @see javafx.scene.input.KeyCode
     */
    public final String strKeyCode;
    /**
     * The KeyCode enum of the key, such as KeyCode.A or KeyCode.DIGIT1
     * @see javafx.scene.input.KeyCode
     */
    public final KeyCode intKeyCode;
    /**
     * true if shift must be pressed in combination with the base key to get 
     * the rendering 
     */
    public final boolean isShifted;
    
    public FxCharConstruction(String char_representation, String key_code_string, KeyCode key_code_int, boolean is_shifted) {
        displayedCharacter = char_representation;
        strKeyCode = key_code_string;
        intKeyCode = key_code_int;
        isShifted = is_shifted;
    }
            
}
