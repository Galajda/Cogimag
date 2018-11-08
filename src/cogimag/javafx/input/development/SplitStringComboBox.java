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
package cogimag.javafx.input.development;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import cogimag.javafx.input.FxComboBoxCellFactory;

/**
 *
 * @author MichalG HP Envy 
 */
public class SplitStringComboBox extends ComboBox<String>{
    FxComboBoxCellFactory myCellFactory;
    public SplitStringComboBox(String[] values) {
        super.setOnKeyPressed(e -> keyPress(e));
        super.setOnAction(e -> selectionChange(e));
        super.getItems().addAll(values);
        myCellFactory = new FxComboBoxCellFactory(); //its search string is initialized as ""
        super.setCellFactory(myCellFactory);
        super.setButtonCell(myCellFactory.call(null));
    }
    
    private void keyPress(KeyEvent e) {
        
        System.out.println("handling a key press event in the private handler");
        System.out.println("\tkey press event text = " + e.getText());
        System.out.println("\tstring to match = " + myCellFactory.searchString);
        
        if (!e.getCode().equals(KeyCode.TAB)) {
            if ((e.getCode().equals(KeyCode.BACK_SPACE)) && (myCellFactory.searchString.length() > 0)) {
                myCellFactory.searchString = myCellFactory.searchString.substring(0, myCellFactory.searchString.length()-1);
            }
            else {
                //not tab, not backspace, so add the new char to the string
                System.out.println("\tadded char " + e.getText() + " to the string");
                myCellFactory.searchString += e.getText();
            }
            if (myCellFactory.searchString.length() == 0) {
                this.getSelectionModel().selectFirst();
            }
            else {
                System.out.println("\tlooking for match with " + myCellFactory.searchString);
                boolean foundMatch = false;
                for (String item: super.getItems()) {
                    if (item.startsWith(myCellFactory.searchString)) {
                        System.out.println("\tfound match with item " + item);
                        foundMatch = true;
                        this.getSelectionModel().select(item);
                        break;
                    }
                }
                if (!foundMatch) {
                    System.out.println("\tdid not find a match. resetting the search string");
//                    myCellFactory.searchString = myCellFactory.searchString.substring(0, myCellFactory.searchString.length()-1);
                    myCellFactory.searchString = "";
                    this.getSelectionModel().clearSelection();
                }
            }
            
            
        }
        //else let tab traverse UI fields of the scene
    
        
    }
    private void selectionChange(ActionEvent e) {
        System.out.println("selection change event new value = " + this.getValue());
    }
}
