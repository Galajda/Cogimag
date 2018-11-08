/*
 * Copyright (C) 2017 Michal G.
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
package cogimag.javafx;

/**
 * Constant strings used in the package
 * @author Michal G. 
 */
class Constants {
    //button labels    
    protected static final String BTN_OK_TXT = "OK";
    protected static final String BTN_CANCEL_TXT = "Cancel";
    
    //text field styles    
    protected static final String STYLE_TEXT_FLD_OK = "-fx-border-color: black; -fx-border-width: 2;"
            + "-fx-border-radius:5; -fx-background-color: #adff2f;"; //green
    protected static final String STYLE_TEXT_FLD_FAIL = "-fx-border-color: black; -fx-border-width: 2;"
            + "-fx-border-radius:5; -fx-background-color: #ff1493;"; //red
    protected static final String STYLE_TEXT_FLD_NEUTRAL = "-fx-border-color: black; -fx-border-width: 2;"
            + "-fx-border-radius:5; -fx-background-color: #ffffff;"; //white
}
