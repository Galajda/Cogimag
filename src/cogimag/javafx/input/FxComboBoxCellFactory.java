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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
/**
 *
 * @author MichalG HP Envy
 */
public class FxComboBoxCellFactory implements Callback<ListView<String>, ListCell<String>>{
    public String searchString; //too lazy to make accessors
    public FxComboBoxCellFactory() {
        super();
        searchString = "";
        System.out.println("cell factory constructor");
    }
    @Override
    public ListCell<String> call(ListView<String> param) {
        System.out.println("call fcn param " + param);
//        final ListCell<String> cell = new ListCell<String>() {          
//            {
//                super.setPrefWidth(100);
//                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//                System.out.println("new anonymous list cell ");
//            }    
//            @Override public void updateItem(String item, boolean empty) {                
//                super.updateItem(item, empty);
//                System.out.println("update item. item = " + item + " empty? " + empty);
//                if (item != null) {
//                    setText(item);    
//                    if (item.startsWith(searchString)) {
////                        setTextFill(Color.RED);
//                        ComboBoxListCell listCell = new ComboBoxListCell(searchString, item);
//                        this.setGraphic(listCell.getItem());
//                    }
//                }
//                else {
////                    setText(null);
//                    this.setGraphic(null);
//                    
//                }
//            }
//        };
        final ListCell<String> cell = new MyListCell(searchString);
        return cell;        
    }
    
    
}



// ComboBox<Color> cmb = new ComboBox<Color>();
// cmb.getItems().addAll(
//     Color.RED,
//     Color.GREEN,
//     Color.BLUE);
//
// cmb.setCellFactory(new Callback<ListView<Color>, ListCell<Color>>() {
//     @Override public ListCell<Color> call(ListView<Color> p) {
//         return new ListCell<Color>() {
//             private final Rectangle rectangle;
//             { 
//                 setContentDisplay(ContentDisplay.GRAPHIC_ONLY); 
//                 rectangle = new Rectangle(10, 10);
//             }
//             
//             @Override protected void updateItem(Color item, boolean empty) {
//                 super.updateItem(item, empty);
//                 
//                 if (item == null || empty) {
//                     setGraphic(null);
//                 } else {
//                     rectangle.setFill(item);
//                     setGraphic(rectangle);
//                 }
//            }
//       };
//   }
//});





















