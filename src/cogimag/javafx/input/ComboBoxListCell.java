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
package cogimag.javafx.input;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
/**
 *
 * @author MichalG HP Envy
 */
class ComboBoxListCell {
    private final Label matchedPart;
    private final Label unmatchedPart;
    private final String value; //might need this for button value
//    private final String searchFragment;
    private HBox container;
    
    public ComboBoxListCell(String search_string, String option_value) {
        value = option_value;
        matchedPart = new Label();        
        matchedPart.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #0000ff;");
        unmatchedPart = new Label();
        unmatchedPart.setStyle("-fx-text-fill: #000000;");
        if (value.startsWith(search_string)) {
            //an empty search string passes this test
            matchedPart.setText(search_string);
            unmatchedPart.setText(value.substring(search_string.length()));
            container = new HBox(matchedPart, unmatchedPart);
        }
        else {
            container = null;
        }
        
    }
    
    public HBox getItem() {
        return container;
    }
}
