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


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * A modal dialog box that displays an informational message
 * with an OK button.
 * @author Michal G. 
 */
public class FxMessageBox {
    /**
     * The constructor is never needed. The single method of this class is static. 
     */
    public FxMessageBox() { }
    
    /**
     * Displays a message box using FX tools. User clicks "OK" when done.
     * @param title The title of the window
     * @param message The body of the message     
     */
    public static void show(String title, String message)
    {        
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(300);
        stage.setMaxWidth(600);
        //set width according to content. 
        stage.getIcons().add(new Image("cogimag/javafx/images/attention.jpg"));
        
        Label msgArea = new Label(message); //how to wrap message?
        Button btnOk = new Button(Constants.BTN_OK_TXT);
        btnOk.setOnAction(e->stage.close());
        VBox pane = new VBox(40);
        pane.getChildren().addAll(msgArea, btnOk);
        pane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
