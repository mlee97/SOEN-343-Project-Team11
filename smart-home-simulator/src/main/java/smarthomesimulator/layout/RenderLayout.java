package smarthomesimulator.layout;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import smarthomesimulator.model.Simulator;
public class RenderLayout extends Application {
    
   

    public static void main(String[] args) {
        launch(args);
       
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("House Layout");
        FlowPane layout = new FlowPane();
        for (int i = 0; i < Simulator.roomsOfHouse.size(); i++) {
        Button button=new Button();	
        button.setPrefWidth(100);
        button.setPrefHeight(50);
        button.setText(Simulator.roomsOfHouse.get(i).getRoomName());
        layout.getChildren().add(button);
        }
        
       
        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
  

}