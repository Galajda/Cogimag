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

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
/**
 *
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
    
    public ControlledStringInputComponent(String instructions, String[]values) {
        lblInstructions = new Label(instructions);
        
        comboBox = new FxComboBox(values);
        comboBox.setPromptText("Choose a value");
        
        btnAdd = new Button("Add");
        btnAdd.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (comboBox.getValue() != null) {                
                    lblResult.setText(lblResult.getText() + comboBox.getValue());
                }
            }
        });
        btnClear = new Button("Clear");
        btnClear.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
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
    
    public ControlledStringInputComponent(String[]values) {
        this("Choose a value and add it to the string", values);
    }
    
    public VBox getComponent() {
        return mainContainer;
    }
    
    
    public String getString() {
        return lblResult.getText();
    }
    
}
