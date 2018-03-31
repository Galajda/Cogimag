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
import javafx.scene.input.KeyCode;
/**
 *
 * @author MichalG HP Envy
 */
public class FxStringTester extends Application {
    
    Label lblDescription;
    TextField txtInputBox;
    TextArea txtOutputBox;
    Button btnSubmitString;
    Button btnClearFields;
    VBox mainContainer;
    HBox buttonContainer;
    
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 600, 250);
        mainContainer = new VBox();
        mainContainer.setMinWidth(500);
        lblDescription = new Label("");
        txtInputBox = new TextField();
        txtInputBox.setOnKeyReleased(new EventHandler<KeyEvent>() {            
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    System.out.println("submit string via enter key");
    //                txtInputBox.clear();
                    txtOutputBox.requestFocus();
//                    System.out.println("get focus owner " + scene.getFocusOwner());
                    FxKeyEventSteno.fireEvent(scene.getFocusOwner() ,new FxKeyMap_EN_US(), txtInputBox.getText());
                }
            }
        });
        
        txtOutputBox = new TextArea();
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
                txtInputBox.requestFocus();
            }
        });
        buttonContainer = new HBox();
        buttonContainer.getChildren().addAll(btnSubmitString, btnClearFields);
        
        mainContainer.getChildren().addAll(lblDescription, txtInputBox, txtOutputBox, buttonContainer);
        
        root.getChildren().add(mainContainer);
        
        
        primaryStage.setTitle("Fx string tester");
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
