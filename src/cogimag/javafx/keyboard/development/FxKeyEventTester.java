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
package cogimag.javafx.keyboard.development;


import javafx.application.Application;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import cogimag.javafx.keyboard.FxKeyEventSteno;
import cogimag.javafx.keyboard.FxKeyMap_EN_US;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
/**
 * Tests the FxKeyMap_EN_US by looking up in the HashMap a character that the 
 * user types into the input box and repeating this character to the output box
 * through javafx.event.Event. The text field displays the result of comparing
 * input and output. In order to adapt the tester to other key maps, the user may
 * alter the call to FxKeyEventSteno.fireEvent in txtInputBox.setOnKeyReleased
 * and in btnSubmitString.setOnAction to take a custom key map as an argument.<br>
 * Known issue: Result is incorrect if user submits more than one character
 * (excepting escape sequences). There is no plan to fix this bug. The class is
 * intended for development use only.
 * @author MichalG HP Envy
 */
public class FxKeyEventTester extends Application {
    
    Label lblDescription;
    TextField txtInputBox;
    TextField txtOutputBox;
    HBox ioContainer;
    TextArea txtTestResultsBox;
    Button btnSubmitString;
    Button btnClearFields;
    HBox buttonContainer;
    VBox mainContainer;
    
    
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 600, 250);
        mainContainer = new VBox();
        mainContainer.setMinWidth(500);
        lblDescription = new Label("Type one character, then press Enter or click Submit.");
        txtInputBox = new TextField();
        txtInputBox.setOnKeyReleased(new EventHandler<KeyEvent>() {            
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
//                    System.out.println("submit string via enter key");
    //                txtInputBox.clear();
                    txtOutputBox.requestFocus();
//                    System.out.println("get focus owner " + scene.getFocusOwner());
                    FxKeyEventSteno.fireEvent(scene.getFocusOwner() ,new FxKeyMap_EN_US(), txtInputBox.getText());
                }
            }
        });        
        txtOutputBox = new TextField();
        txtOutputBox.setOnKeyTyped(new EventHandler<KeyEvent>() {            
            @Override
            public void handle(KeyEvent e) {
//                System.out.println("key typed in output box");
                int inputCharAsciiCode =  FxKeyMap_EN_US.getAsciiNumber(txtInputBox.getText());
                System.out.println("key typed event from output box. input as int = " + inputCharAsciiCode);
                int outputCharAsciiCode = (e.getCharacter().codePointAt(0));
//                System.out.println("key typed event from output box. output as int = " + outputCharAsciiCode);
                String testResult = "you typed " + txtInputBox.getText() + ". input ascii number = " + inputCharAsciiCode +
                        ". output ascii number = " + outputCharAsciiCode + ". match ? " + (inputCharAsciiCode == outputCharAsciiCode);
                txtTestResultsBox.appendText(testResult + "\n");
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {                          
                        txtInputBox.clear();
                        txtOutputBox.clear();
                        txtInputBox.requestFocus();
                    }
                });                                
            }
        });
        
        ioContainer = new HBox();
        ioContainer.getChildren().addAll(txtInputBox, txtOutputBox);
        ioContainer.setAlignment(Pos.CENTER);
        txtTestResultsBox = new TextArea("Test results\n");
        
        btnSubmitString = new Button("Submit string");
        btnSubmitString.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {
//                System.out.println("submit string via button");
//                txtInputBox.clear();
                txtOutputBox.requestFocus();
//                System.out.println("get focus owner " + scene.getFocusOwner());
                FxKeyEventSteno.fireEvent(scene.getFocusOwner() ,new FxKeyMap_EN_US(), txtInputBox.getText());
            }
        });        
        btnClearFields = new Button("Clear fields");
        btnClearFields.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {
//                System.out.println("clear fields");
                txtInputBox.clear();
                txtOutputBox.clear();
                txtTestResultsBox.setText("Test results\n");
                txtInputBox.requestFocus();
            }
        });
        buttonContainer = new HBox();
        buttonContainer.getChildren().addAll(btnSubmitString, btnClearFields);
        buttonContainer.setAlignment(Pos.CENTER);
        
        mainContainer.getChildren().addAll(lblDescription, ioContainer, txtTestResultsBox, buttonContainer);
        
        root.getChildren().add(mainContainer);
        
        primaryStage.setTitle("Fx key event tester");
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
