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

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import javafx.scene.layout.HBox;

/**
 *
 * @author MichalG HP Envy
 */
public class MyListCell extends ListCell<String> {
    private final String searchString;
    public MyListCell(String search_string) {
        searchString = search_string;
        super.setPrefWidth(100);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        System.out.println("new anonymous list cell for search string " + search_string);
    }
    @Override 
    public void updateItem(String item, boolean empty) {                
        super.updateItem(item, empty);
        System.out.println("update item. item = " + item + " empty? " + empty);
        if (empty || (item == null)) {
            setText(null);
            this.setGraphic(null);            
        }
        else {
            setText(item);    
            if (item.startsWith(searchString)) {
//                ComboBoxListCell listCell = new ComboBoxListCell(searchString, item);
//                this.setGraphic(listCell.getItem());
                this.setGraphic(this.getHbox(item));
                
            }
        }
    }
    private HBox getHbox(String item) {
        Label matchedPart;
        Label unmatchedPart;
        HBox container;        
        matchedPart = new Label();        
        matchedPart.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #0000ff;");
        unmatchedPart = new Label();
        unmatchedPart.setStyle("-fx-text-fill: #000000;");
        System.out.println("getting hbox. matching item = " + searchString);
        if (item.startsWith(searchString)) {
            //an empty search string passes this test
            matchedPart.setText(searchString);
            unmatchedPart.setText(searchString.substring(searchString.length()));
            container = new HBox(matchedPart, unmatchedPart);
        }
        else {
            container = null;
        }
        return container;
    }
    
    
    
}
