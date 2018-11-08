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

import javafx.scene.control.ComboBox;
import java.util.Collection;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
/**
 * A subclass of the ComboBox. All normal features of the ComboBox are supported.
 * The extra feature matches the user's keypress with the selection.
 * bug: cannot see the letters you have typed
 * bug: the whole word is highlighted
 * bug: string is not cleared when focus is lost
 * Credit to 
 * <a href="https://stackoverflow.com/questions/13362607/combobox-jump-to-typed-char/13372302">
 * Stack Overflow 13362607</a>
 * @author Michal G. 
 */

public class FilteredFxComboBox extends ComboBox<String>{
    private String inputChars;
    /**
     * Zero-argument constructor sets key release handler. Combo box contents
     * must be set separately through {@code super.getItems().addAll()}
     */
    public FilteredFxComboBox() {
        super();
        inputChars = "";
        super.setOnKeyPressed(e -> matchKey(e));
        //https://stackoverflow.com/questions/16549296/how-perform-task-on-javafx-textfield-at-onfocus-and-outfocus
        super.focusedProperty().addListener((javafx.beans.Observable hasFocus) -> focusListener(hasFocus));
       
    }
    /**
     * The combo box may be initialized with the values of the options, sent as
     * a {@code Collection<>}.
     * @param items the String Collection of options in the combo box
     */
    public FilteredFxComboBox(Collection<String> items) {
        this();                
        super.getItems().addAll(items);
//        super.setOnKeyPressed(e -> matchKey(e));
    }
    /**
     * More accessible constructor using String array for the combo box options.
     * @param items a String array, which may be more convenient than a Collection.
     */
    public FilteredFxComboBox(String[] items) {
        this();
        super.getItems().addAll(items);
//        super.setOnKeyPressed(e -> matchKey(e));
    }
    
    /**
     * The combo box may be initialized with an id and the Collection of option
     * values.
     * @param control_id the id of this instance of the combo box control
     * @param items the String Collection of options in the combo box
     */
    public FilteredFxComboBox(String control_id, Collection<String> items) {
        this();
//        inputChars = "";
        super.getItems().addAll(items);
        super.setId(control_id);
//        super.setOnKeyPressed(e -> matchKey(e));
    }
        /**
     * The combo box may be initialized with an id and the String array of option
     * values.
     * @param control_id the id of this instance of the combo box control
     * @param items the String array of options in the combo box
     */
    public FilteredFxComboBox(String control_id, String[] items) {
        this();
//        inputChars = "";
        super.getItems().addAll(items);
        super.setId(control_id);
//        super.setOnKeyPressed(e -> matchKey(e));
    }
    /**
     * Handles keystrokes in the combo box, matching the letter typed with the 
     * combo box values. Subsequent presses refine the selection.
     * @param e key pressed event in the combo box
     */
    private void matchKey(KeyEvent e) {
        System.out.println("handling a key press event in the private handler");
        System.out.println("\tstring to match = " + inputChars);
        //replace this search with startsWith()
        if (!e.getCode().equals(KeyCode.TAB)) {
            if ((e.getCode().equals(KeyCode.BACK_SPACE)) && (inputChars.length() > 0)) {
                inputChars = inputChars.substring(0, inputChars.length()-1);
            }
            else {
                //not tab, not backspace, so add the new char to the string
                System.out.println("\tadded char " + e.getText() + " to the string");
                inputChars += e.getText();
            }
            if (inputChars.length() == 0) {
                this.getSelectionModel().selectFirst();
            }
            else {
                System.out.println("\tlooking for match with " + inputChars);
                boolean foundMatch = false;
                for (String item: super.getItems()) {
                    if (item.startsWith(inputChars)) {
                        System.out.println("\tfound match with item " + item);
                        foundMatch = true;
                        this.getSelectionModel().select(item);
                        break;
                    }
                }
                if (!foundMatch) {
                    System.out.println("\tdid not find a match. trimming the search string");
                    inputChars = inputChars.substring(0, inputChars.length()-1);
                }
            }
            
            
        }
        //else let tab traverse UI fields of the scene
        
//        e.consume();        
    }
    private void focusListener(javafx.beans.Observable hasFocus) {
        System.out.println("combo box focus listener is running");
        
        if (!this.focusedProperty().get()) {
            //get returns boolean true if component has focus
            System.out.println("combo box lost focus. clearing search string.");
            inputChars = "";
        }
    }
}
