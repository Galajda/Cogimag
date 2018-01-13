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

import javafx.scene.control.ComboBox;
import java.util.Collection;
import javafx.scene.input.KeyEvent;
/**
 * A subclass of the ComboBox. All normal features of the ComboBox are supported.
 * The extra feature matches the user's keypress with the selection.
 * Credit to Stack Overflow 13362607
 * @author Michal G. 
 */
public class FxComboBox extends ComboBox<String> {
    /**
     * Null-argument constructor sets key release handler
     */
    public FxComboBox() {
        super();
        super.setOnKeyReleased(e -> comboBoxKeyboardShortcut(e));
    }
    /**
     * The combo box may be initialized with the values of the options.
     * @param items the String Collection of options in the combo box
     */
    public FxComboBox(Collection<String> items) {
        super.getItems().addAll(items);
        super.setOnKeyReleased(e -> comboBoxKeyboardShortcut(e));
    }
    /**
     * The combo box may be initialized with an id and the Collection of option values.
     * @param control_id the id of this instance of the combo box
     * @param items the String Collection of options in the combo box
     */
    public FxComboBox(String control_id, Collection<String> items) {
        super();
        super.getItems().addAll(items);
        super.setId(control_id);
        super.setOnKeyReleased(e -> comboBoxKeyboardShortcut(e));
    }
    
    
    private void comboBoxKeyboardShortcut(KeyEvent e) {
//        System.out.println("handling a key press event in the private handler");
        //replace this search with startsWith()
        int firstMatchLocation = (this.getSelectionModel().getSelectedIndex() < 0) ? 
                0 : this.getSelectionModel().getSelectedIndex();
        Boolean foundSecondMatch = false;
        for (int i=firstMatchLocation+1; i<this.getItems().size(); i++) {
            if (e.getText().equalsIgnoreCase(this.getItems().get(i).substring(0,1))) {
                this.getSelectionModel().select(i);
                foundSecondMatch = true;
                e.consume();
//                System.out.println("matched " + e.getText() + " with " + this.getItems().get(i));
                break;
            }
        }
        //if no second match is found, start from the 0 index
        if (!foundSecondMatch) {
            for (String item : this.getItems() ) {
//                System.out.println("\tcycling through options. item " + item);            
                if (e.getText().equalsIgnoreCase(item.substring(0, 1))) {
                    this.getSelectionModel().select(item);
                    e.consume();
                    break;
                }
            }
        }
//        e.consume();
        
        
        
    }
    
    
    
}
