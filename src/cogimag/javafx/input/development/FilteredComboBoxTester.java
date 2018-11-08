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

import cogimag.javafx.input.FilteredFxComboBox;
import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.scene.Scene;
//import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//import cogimag.javafx.input.development.SplitStringComboBox;

/**
 *
 * @author MichalG HP Envy
 */
public class FilteredComboBoxTester extends Application {
    private Label lblFilterCboBox;
    private FilteredFxComboBox filteredCboBox;
    private Label lblSplitStringCboBox;
    private SplitStringComboBox cboSplitString;
    private TextField textField;
    @Override
    public void start(Stage primaryStage) {
        lblFilterCboBox = new Label("filtered combo box");
//        lblFilterCboBox.labelForProperty().bind(filteredCboBox.getProperties());
        filteredCboBox = new FilteredFxComboBox(new String[] {"one", "two", "three"});
        filteredCboBox.setPromptText("Choose a number...");
        lblSplitStringCboBox = new Label("\nsplit string combo box");
        
        cboSplitString = new SplitStringComboBox(new String[] {"one", "two", "three", "four", "five", "sick", "sicks", "seven"});
        cboSplitString.setPromptText("Start typing to search");
        textField = new TextField();
        VBox container = new VBox();
        container.getChildren().addAll(lblFilterCboBox, filteredCboBox, lblSplitStringCboBox, cboSplitString, textField);
        StackPane root = new StackPane();
        root.getChildren().add(container);
        
        Scene scene = new Scene(root, 400, 250);
        
        primaryStage.setTitle("Filtered combo box tester");
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
