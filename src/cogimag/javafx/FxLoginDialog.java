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
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Please do not use this.
 * @author Michal G. 
 */
public class FxLoginDialog {
    
    private static final String LOGIN_TITLE = "Login";
    private static final String LOGIN_LABEL = "Enter the password";
    private static final String BTN_OK_TXT = "OK";
    private Boolean loginSuccess;
    
    public FxLoginDialog() {    
        loginSuccess = false;
    }
    
    
    /**
     * EXPERIMENTAL. passing vars from event handler to the dialog box is difficult.
     * the handler can, however change the style of the text field. 
     * use the style of the pwd field to indicate success
     * @param realPassword where should the app store this? hash in xml?
     * @return true if login succeeds, false if login fails
     */
    
    public Boolean login(String realPassword) {
        
        final Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(FxLoginDialog.LOGIN_TITLE);
        stage.setMinWidth(300);
        stage.setMaxWidth(600);
        //set width according to content. 
        stage.getIcons().add(new Image("cogimag/javafx/images/attention.jpg"));
//        Boolean loginSuccess2 = false;
        final Label lblPassword = new Label(FxLoginDialog.LOGIN_LABEL); //how to wrap message?
        final PasswordField pwdField = new PasswordField();
        Button btnOk = new Button(FxLoginDialog.BTN_OK_TXT);
//        btnOk.setOnAction(e->validatePassword(e, pwdField.getText(), realPassword, stage));
        btnOk.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.print("validating password " + pwdField.getText() + " against " + realPassword);
                    Boolean valid = (pwdField.getText() != null && 
                            realPassword != null  && pwdField.getText().equals(realPassword));
                    if (valid) {
                        pwdField.setStyle(Constants.STYLE_TEXT_FLD_OK);
                        //let user x out?
                            stage.close();
                    }
                    else {
                        pwdField.setStyle(Constants.STYLE_TEXT_FLD_FAIL);
                    }
                    System.out.println(" result " + valid);
                }
            }
        );
        VBox pane = new VBox(30);
        pane.getChildren().addAll(lblPassword, pwdField, btnOk);
        pane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.showAndWait();
        
        return pwdField.getStyle().equals(Constants.STYLE_TEXT_FLD_OK);
        //flip because app constant style is guaranteed to be non-null?
        //if stage is already closed, what is the style?
    }
    private void validatePassword(ActionEvent e, String testPwd, String realPwd, Stage s) {
        System.out.print("validating password " + testPwd + " against " + realPwd);
        loginSuccess = (testPwd != null && realPwd != null  && testPwd.equals(realPwd));
        System.out.println(" result " + loginSuccess);
        if (loginSuccess) {
            s.close();
        }
        else {
            
        }
        
        
    }
    
    
}
