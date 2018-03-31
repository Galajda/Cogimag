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


import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import cogimag.javafx.keyboard.FxKeyEventSteno;
import cogimag.javafx.keyboard.FxKeyMap_EN_US;

/**
 * Used to gather information on the behavior of key events when application
 * focus changes. It seems that the event queue acts only when the native 
 * application is the active window.
 * @author MichalG HP Envy
 */
public class FxFocusTester extends Application {
    
    Label lblDescription;
    TextField txtOutputBox;
    Label lblQueueStatus;
    Button btnFireOnce;
    Button btnStartTimer;
    Button btnStopTimer;
    VBox controlContainer;
    long startTime;
    Timer tmr;
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 600, 250);
        controlContainer = new VBox();
        controlContainer.setMinWidth(500);
        lblDescription = new Label("");
        txtOutputBox = new TextField();
        btnFireOnce = new Button("Fire one event");
        btnFireOnce.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("fire one event");
//                txtOutputBox.clear();
                txtOutputBox.requestFocus();
                System.out.println("get focus owner " + scene.getFocusOwner());
                FxKeyEventSteno.fireEvent(scene.getFocusOwner() ,new FxKeyMap_EN_US(), "1234");
            }
        });
        
        btnStartTimer = new Button("Start timer");
        btnStartTimer.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("stop timer");
                startTimer();
            }
        });
        
        
        btnStopTimer = new Button("Stop timer");
        btnStopTimer.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("stop timer");
                tmr.cancel();
            }
        });
        lblQueueStatus = new Label();
        
        controlContainer.getChildren().addAll(lblDescription, lblQueueStatus, txtOutputBox, btnFireOnce, btnStartTimer, btnStopTimer);
        
//        root.getChildren().add(btn);
        root.getChildren().add(controlContainer);
        
        
        primaryStage.setTitle("Event queue tester");
        primaryStage.setScene(scene);
        primaryStage.show();        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    private void startTimer() {
        System.out.println("start timer");
        startTime = System.currentTimeMillis();
        txtOutputBox.requestFocus();
        tmr = new Timer();
        tmr.schedule(new KeyEventMonitor(txtOutputBox), 1000, 3000);
    }
    
    private class KeyEventMonitor extends TimerTask {
        javafx.event.EventTarget target;
        KeyEventMonitor(javafx.event.EventTarget event_target) {
            super();
            target = event_target;
        }
        @Override
        public void run() {
//            AwtKeyEventSteno.fireEvent(new AwtKeyMap_EN_US(), "Secs elapsed " + ((System.currentTimeMillis()- startTime)/1000) );
            FxKeyEventSteno.fireEvent(target, new FxKeyMap_EN_US(), "1234");
            //SO 1323408
//        java.util.Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
//        Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);      
//        Thread eventQueue = threadArray[0];
//        for (int i=0;i<threadSet.size();i++) {
//            System.out.println("thread #" + i + " to string " + threadArray[i].toString() + 
//                    " name " +  " state = " + threadArray[i].getState());
            //threadArray[i].getName()
//            if (threadArray[i].getName().startsWith("AWT-EventQueue")) {
//                eventQueue = threadArray[i];
//                String labelText = "Secs elapsed " + ((System.currentTimeMillis()- startTime)/1000) + 
//                        ". Event queue status" + threadArray[i].getState();
//                lblQueueStatus.setText(labelText);
//                System.out.println(labelText);
//            }
//        }
        
            
           
        }
        
    }
}
