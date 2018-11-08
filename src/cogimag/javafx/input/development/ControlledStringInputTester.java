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

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import cogimag.javafx.input.ControlledStringInputComponent;

//import cogimag.javafx.input.development.SplitStringComboBox;


/**
 * Simple JavaFX window to demonstrate use of the {@link 
 * ControlledStringInputComponent}. The component appears at the top, and an
 * output is below, demonstrating the retrieval of the component's String.
 * 
 * @author MichalG HP Envy
 */
public class ControlledStringInputTester extends Application {
    
    private ControlledStringInputComponent inputComponent;
    private Button btnShowString;
    private Label lblOutput;
//    private Label lblTestCboBox;
//    private SplitStringComboBox testCboBox;
    private VBox mainContainer;
    @Override
    public void start(Stage primaryStage) {
        
        String[] choices = {"one", "two", "three", "\\n", "\\t"};        
        inputComponent = new ControlledStringInputComponent("These are your choices.\nLetters match first place only.", choices);
        
        btnShowString = new Button("Get the string");        
        btnShowString.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {
                lblOutput.setText(inputComponent.getString());
            }
        });
        lblOutput = new Label("your output here");
        
//        lblTestCboBox = new Label("\ntest combo box with split display");
//        testCboBox = new SplitStringComboBox(new String[] {"one", "two", "three", "four", "five", "sick", "sicks", "seven"});
        
        
        mainContainer = new VBox();
        mainContainer.getChildren().addAll(inputComponent.getComponent(), btnShowString, lblOutput);
        
        StackPane root = new StackPane();
//        root.getChildren().add(btn);
        root.getChildren().add(mainContainer);
        Scene scene = new Scene(root, 500, 250);
        
        primaryStage.setTitle("Controlled string input tester");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
