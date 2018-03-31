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
 * @author MichalG HP Envy
 */
public class FxCharConstruction {
    public final String displayedCharacter;
    public final String strKeyCode;
    public final KeyCode intKeyCode;
    public final boolean isShifted;
    
    public FxCharConstruction(String char_representation, String key_code_string, KeyCode key_code_int, boolean is_shifted) {
        displayedCharacter = char_representation;
        strKeyCode = key_code_string;
        intKeyCode = key_code_int;
        isShifted = is_shifted;
    }
            
}
