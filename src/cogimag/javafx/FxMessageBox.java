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
import javafx.scene.control.PasswordField;
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
    
    private static final String BTN_OK_TXT = "OK";
    private static final String BTN_CANCEL_TXT = "Cancel";
    private Boolean confirmDialogResult;
    
    public FxMessageBox() { 
       confirmDialogResult = false;        
    }
    
    /**
     * Displays a message box using FX tools
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
        Button btnOk = new Button(FxMessageBox.BTN_OK_TXT);
        btnOk.setOnAction(e->stage.close());
        VBox pane = new VBox(40);
        pane.getChildren().addAll(msgArea, btnOk);
        pane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.showAndWait();
    }
    /**
     * Displays a modal dialog box with a message, OK and Cancel buttons.
     * @param title title for the dialog box
     * @param message details of the message, such as a yes/no question     
     * @return true if the user clicks "OK"
     */
    public Boolean confirm(String title, String message) {
        Stage stage = new Stage();
//        Boolean response = false;
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(300);
        stage.setMaxWidth(900);
        //set width according to content. 
        stage.getIcons().add(new Image("cogimag/javafx/images/question.jpg"));
        
        Label msgArea = new Label(message); //how to wrap message?
        Button btnOk = new Button(FxMessageBox.BTN_OK_TXT);
//        btnOk.setOnAction(e -> getAnswer(e, stage));
//        MyClickHandler ch = new MyClickHandler();
        btnOk.setOnAction(e -> getAnswer(e, stage));
        Button btnCancel = new Button(FxMessageBox.BTN_CANCEL_TXT);
        btnCancel.setOnAction(e -> getAnswer(e, stage));
        VBox pane = new VBox(20);
        HBox btnPane = new HBox(10);
        btnPane.setAlignment(Pos.CENTER);
        btnPane.getChildren().addAll(btnOk, btnCancel);
        pane.getChildren().addAll(msgArea, btnPane);
        pane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        System.out.println("about to wait");
        stage.showAndWait(); //thread waits here until button is clicked
        System.out.println("done waiting");
//        stage.close();
        return confirmDialogResult;
        
    }
    private void getAnswer(ActionEvent e, Stage s) {
//        System.out.println("ok/cancel clicked " + e.getSource());
        
        String name = "";
        if (e.getSource() instanceof Button) {
            Button b = (Button)e.getSource();
            name = b.getText();
//            System.out.println("button name " + name);
        }
        confirmDialogResult = name.equals(FxMessageBox.BTN_OK_TXT);
        s.close();
//        return (name.equals(FxMessageBox.BTN_OK_TXT));
    }
    
    //anonymous inner class event handler
    //new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent event) {
//                        response = true; var must be final for reference from here
//                        
//                        
//                    }
    //if the class implements EventHandler, setOnAction must specify this. However, non-static
    //reference is not allowed in a static method.
//    @Override
//    public void handle(Event event) {
//        System.out.println("handling the click with fxmsgbox implementation");
//    }
    
    //the basic problem is that a click handler has a void return type. you cannot return a value
//    private static class MyClickHandler implements EventHandler {
//        @Override
//        public void handle(Event event) {
//            System.out.println("handling the click in inner class");
//        }        
//    }
    
}
