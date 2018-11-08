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
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 *
 * @author MichalG HP Envy
 */
public class FxMapGenerator extends Application {
    
    Label lblInstructions;
    TextField txtInputBox;
    TextArea txtOutputBox;
    Button btnClearFields;
    Button btnCopyOutput;
    VBox mainContainer;
    HBox buttonContainer;

    private int asciiNumber;
    private String characterRepresentation;
    private String keyCodeString;
    private KeyCode keyCodeEnumValue;
    private boolean isShifted;
    
    @Override
    public void start(Stage primaryStage) {
        mainContainer = new VBox();
        mainContainer.setMinWidth(500);
        lblInstructions = new Label("Type one key on the box below. Press Enter or click Submit keystroke.");
        txtInputBox = new  TextField();
        txtInputBox.setOnKeyPressed(e -> txtInputBox_KeyPressed(e));
        txtInputBox.setOnKeyReleased(e -> txtInputBox_KeyReleased(e));        
        txtInputBox.setOnKeyTyped(e -> txtInputBox_KeyTyped(e));

        txtOutputBox = new TextArea(); //scrolls automatically 
        txtOutputBox.setMinSize(500, 300);
        
        btnClearFields = new Button("Clear fields");
        btnClearFields.setOnAction(e-> btnClearFields_Click(e));
        
        btnCopyOutput = new Button("Copy to clipboard");
        btnCopyOutput.setOnAction(e -> btnCopyOutput_Click(e));
        buttonContainer = new HBox();
        buttonContainer.getChildren().addAll(btnClearFields, btnCopyOutput);
        
        mainContainer.getChildren().addAll(lblInstructions, txtInputBox, txtOutputBox, buttonContainer);
        StackPane root = new StackPane();
        root.getChildren().add(mainContainer);
        Scene scene = new Scene(root, 600, 400);
        
        primaryStage.setTitle("JavaFX key map generator");
        primaryStage.setScene(scene);
        primaryStage.show();        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    private void txtInputBox_KeyPressed(Event e) {
        System.out.println("key press in input field");
        
    }
    private void txtInputBox_KeyTyped(Event e) {
        EventType eType = e.getEventType();
        System.out.println("key typed in input field. ");
//        System.out.println("\tevent type " + eType);
//        System.out.println("\tdoes it match key typed? " + eType.equals(KeyEvent.KEY_TYPED));
        KeyEvent keyEvent = (KeyEvent)e;
        System.out.println("\tthe char = " + keyEvent.getCharacter());
        
        System.out.println("\tshift? " + keyEvent.isShiftDown());
        
        if (keyEvent.getCharacter().codePointAt(0) != 13) {            
            asciiNumber = keyEvent.getCharacter().codePointAt(0);
            System.out.println("\tproposed ascii number = " + asciiNumber);
            characterRepresentation = keyEvent.getCharacter();
            this.isShifted = keyEvent.isShiftDown();
            
        }
    }
    
    private void txtInputBox_KeyReleased(Event e) {
        KeyEvent keyEvent = (KeyEvent)e;
        System.out.println("key release in input field");
        String keyReleaseText = keyEvent.getText();
        KeyCode kc = keyEvent.getCode();
        
        System.out.println("\tget code = " + kc + " as text = " + keyReleaseText);        
        System.out.println("\tkey get code:" + kc + " get text:" + keyReleaseText + ":");
        if (!((kc == KeyCode.ENTER) || (kc == KeyCode.SHIFT))) {            
//            System.out.println("\tget code = " + kc + " as text = " + keyReleaseText);        
            keyCodeString = keyReleaseText;            
            this.keyCodeEnumValue = kc;
        }
        
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            
            this.txtOutputBox.appendText(this.formatMapPut() + "\n");
            this.txtInputBox.clear();
        }
    }
    
    private void btnClearFields_Click(Event e) {
        System.out.println("btn clear click");
        this.txtInputBox.clear();
        this.txtOutputBox.clear();
        this.txtInputBox.requestFocus();
    }
    
    private void btnCopyOutput_Click(Event e) {
        System.out.println("btn copy output");
        txtOutputBox.selectAll();
        txtOutputBox.copy();
        txtOutputBox.deselect();
        this.txtInputBox.requestFocus();
    }
    private String formatMapPut() {
        System.out.println("formatting map put statement");
        StringBuilder sb = new StringBuilder();
        sb.append("\tmap.put(");
        sb.append(this.asciiNumber);
        sb.append(", new FxCharConstruction(\"");
        sb.append(this.characterRepresentation); 
        sb.append("\", \"");        
        sb.append(keyCodeString);
        sb.append("\", ");       
//        sb.append(char_con.keyEventConstant); //should I write this as KeyEvent.VK_ ?
        sb.append("KeyCode.");
        sb.append(this.keyCodeEnumValue);        
        sb.append(", ");
        sb.append(this.isShifted);
        sb.append("));");        
        
        return sb.toString();
    }

    
}
