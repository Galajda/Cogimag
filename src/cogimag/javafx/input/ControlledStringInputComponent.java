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

//import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.control.TextArea;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
/**
 * A JavaFX node combining an informational title, a combo box that the 
 * developer may populate, add and clear buttons, and a non-editable display
 * of the user's selections. The component is intended to limit the user's string
 * input to pre-defined choices of characters, improving reliability and security.
 * @author MichalG HP Envy
 */
public class ControlledStringInputComponent extends VBox{
    private static Label lblInstructions;
    private static HBox buttonContainer;
    private static FxComboBox comboBox;
    private static Button btnAdd;
    private static Label lblResult;
    private static Button btnClear;
    
    private static VBox mainContainer;
    /**
     * Constructs a component with a custom heading and the array of combo box
     * values.
     * @param instructions the instructions to the user for choosing values
     * @param values the array of Strings from which the user may choose
     */
    public ControlledStringInputComponent(String instructions, String[]values) {
        lblInstructions = new Label(instructions);
        
        comboBox = new FxComboBox(values);
        comboBox.setPromptText("Choose a value");
        
        btnAdd = new Button("Add");
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (comboBox.getValue() != null) {                
                    lblResult.setText(lblResult.getText() + comboBox.getValue());
                }
            }
        });
        btnClear = new Button("Clear");
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lblResult.setText("");
            }
        });
        buttonContainer = new HBox();
        buttonContainer.getChildren().addAll(comboBox, btnAdd, btnClear);
        
        lblResult = new Label();
//        lblResult.setBorder(new Border());
        lblResult.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-min-width: 130;" + 
                " -fx-min-height: 30; -fx-font-size: 16px; -fx-label-padding: 2,50,2,50");
        //does -fx-label-padding do anything?
        
        mainContainer = new VBox();
        mainContainer.getChildren().addAll(lblInstructions, buttonContainer, lblResult);        
    }
    /**
     * Constructs a component with a default title and the array of combo box
     * values.
     * @param values the array of Strings from which the user may choose
     */
    public ControlledStringInputComponent(String[]values) {
        this("Choose a value and add it to the string", values);
    }
    /**
     * Retrieves the component for placement in the application window.
     * @return the component (a VBox) which may be added to an FX node.
     */
    public VBox getComponent() {
        return mainContainer;
    }
    /**
     * Retrieves the user's input
     * @return the string which the user has built by adding selections from
     * the combo box
     */    
    public String getString() {
        return lblResult.getText();
    }
    
}
