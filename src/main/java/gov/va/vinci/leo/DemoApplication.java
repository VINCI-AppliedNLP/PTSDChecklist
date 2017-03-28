package gov.va.vinci.leo;
/*
 * #%L
 * NLP Leo demonstation
 * %%
 * Copyright (C) 2010 - 2016 Department of Veterans Affairs
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import gov.va.vinci.leo.pcl.PclClient;
import gov.va.vinci.leo.pcl.PclService;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.apache.log4j.Logger;
import org.apache.uima.util.Level;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static javafx.scene.text.Font.*;

public class DemoApplication extends Application {
  
  private Desktop desktop = Desktop.getDesktop();
  
  @Override
  public void start(Stage primaryStage) throws Exception {
    // Grid Pane has a variable number of columns and rows
    
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setGridLinesVisible(false);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(25, 25, 25, 25));
    grid.getColumnConstraints().add(new ColumnConstraints(400)); // column 1 is 100 wide
    grid.getColumnConstraints().add(new ColumnConstraints(500));
    grid.getColumnConstraints().add(new ColumnConstraints(200));
    
    //  Section 1 Title
    Text section1Title = new Text("Select Service configurations");
    section1Title.setFont(font("Tahoma", FontWeight.NORMAL, 20));
    grid.add(section1Title, 0, 0, 4, 1);  // add(Node child, int columnIndex, int rowIndex, int colspan, int rowspan)
    
    //  Broker URL
    Label brokerUrlLabel = new Label("Broker URL ( ex. tcp://localhost:61616 ):");
    grid.add(brokerUrlLabel, 0, 1);
    
    TextField brokerUrlText = new TextField();
    brokerUrlText.setText("tcp://localhost:61616");
    grid.add(brokerUrlText, 1, 1);
    
    // Scaleout type
    Label pw = new Label("Scaleout type:");
    grid.add(pw, 0, 2);
    
    ChoiceBox<String> choiceBox = new ChoiceBox(FXCollections.observableArrayList(
        "Synchronous", "Asynchronous"));
    choiceBox.setValue("Synchronous");
    grid.add(choiceBox, 1, 2);
  
  
  
    ChoiceBox<String> choiceBoxPipeline = new ChoiceBox(FXCollections.observableArrayList(
        "Example Pipeline", "Optimization Pipeline", "Scalability Pipeline"));
    choiceBoxPipeline.setValue("Example Pipeline");
    grid.add(choiceBoxPipeline, 1, 3);
    
    //  Service Name
    Label endPointNameLabel = new Label("Service name (use alphanumeric characters or underscore only.):");
    grid.add(endPointNameLabel, 0, 3);
    
    TextField endPointNameText = new TextField();
    endPointNameText.setText("DemoSystem");
    grid.add(endPointNameText, 1, 4);
    
    /////  Section 2 title
    Text section2Title = new Text("Select Client configurations");
    section2Title.setFont(font("Tahoma", FontWeight.NORMAL, 20));
    grid.add(section2Title, 0, 5, 4, 1);  // add(Node child, int columnIndex, int rowIndex, int colspan, int rowspan)
    
    //  Button to select reader
    Label readerLabel = new Label("Select reader configuration file:");
    grid.add(readerLabel, 1, 6);
    
     FileChooser fileChooser = new FileChooser();
     File[] selectedReader = new File[]{new File("")};
    Button chooseReader = createReaderButton(fileChooser, primaryStage, readerLabel, selectedReader);
    grid.add(chooseReader, 0, 6);
    
    ////  Button to select listeners
    Label listenerLabel = new Label("Listener list:");
    grid.add(listenerLabel, 1, 7);
    
     List<File> listenerList = new ArrayList<File>();
     String[] listeners = new String[]{""};
     Button openMultipleButton = createListenersButton(fileChooser, primaryStage, listenerList, listeners, listenerLabel);
    grid.add(openMultipleButton, 0, 7);
    ////////////////////////
    
    TextField aggregateDescPath = new TextField();
    grid.add(aggregateDescPath, 1, 4);
    
    PclService exService = new PclService();
     String[] aggPath = new String[]{""};
    
    Button btn = createServiceButton( brokerUrlText,  choiceBox , choiceBoxPipeline, endPointNameText , selectedReader,  exService ,  aggregateDescPath);
    grid.add(btn, 2, 3);
    
    
    Button btnStop = createStopPipelineButton(exService);
    grid.add(btnStop, 0, 10);
    ////////////////////////
    
    Button btnStartClient = createStartClientButton(selectedReader, listenerList);
    grid.add(btnStartClient, 2, 8);
    ///////////////////
    
    
    Scene scene = new Scene(grid, 1200, 500);
    primaryStage.setTitle("System configurations");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  private Button createReaderButton( FileChooser fileChooser,  Stage primaryStage,  Label readerLabel,  File [] selectedReader) {
    Button chooseReader = new Button();
    
    chooseReader.setText("Select reader configuration file");
    chooseReader.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        File file = fileChooser.showOpenDialog(primaryStage);
        
        if (file != null) {
          openFile(file);
          try {
            selectedReader[0] = file;
            readerLabel.setText(selectedReader[0].getCanonicalPath());
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    });
    return chooseReader;
  }
  
  
  private Button createListenersButton( FileChooser fileChooser,  Stage primaryStage,  List<File> listenerList,  String[] listeners,  Label listenerLabel) {
    Button openMultipleButton = new Button("Select listener configuration files (hold Ctrl to select multiple)");
    openMultipleButton.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle( ActionEvent e) {
            try {
              List<File> list =
                  fileChooser.showOpenMultipleDialog(primaryStage);
              if (list != null) {
                for (File file : list) {
                  
                  listenerList.add(file);
                  listeners[0] = listeners[0] + "\r\n" + file.getCanonicalPath();
                  openFile(file);
                }
              }
              listenerLabel.setText(listeners[0]);
            } catch (IOException e1) {
              e1.printStackTrace();
            }
          }
        });
    return openMultipleButton;
  }
  
  private Button createServiceButton(TextField brokerUrlText, ChoiceBox<String> choiceBox , ChoiceBox<String> choiceBoxPipeline , TextField endPointNameText , File[] selectedReader, PclService exService , TextField aggregateDescPath) {
     Button btn = new Button();
    btn.setText("Run Pipeline");
    btn.setOnAction(new EventHandler<ActionEvent>() {
      
      @Override
      public void handle(ActionEvent event) {
        System.out.println("set variables");
        System.out.println(brokerUrlText.getText());
        System.out.println(choiceBox.getValue());
        System.out.println(endPointNameText.getText());
        System.out.println(selectedReader[0]);
        /*  Create config/ServiceConfig.groovy */
        
        FileWriter fw = null;
        try {
          fw = new FileWriter("ServiceConfig_run.groovy");
          fw.write("brokerURL=\"" + brokerUrlText.getText() + "\"\r\n");
          fw.write("endpoint=\"" + endPointNameText.getText() + "\"\r\n");
          fw.write("isAsync=\""
              + (((Boolean) (choiceBox.getValue()).contains("Synchronous")) ? "false" : "true")
              + "\"\r\n");
          
          fw.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
        
        Thread saveIt = new Thread(new Runnable() {
          @Override
          public void run() {
  
            exService.serviceConfigFile = new File[]{new File("ServiceConfig_run.groovy")};
            if(choiceBoxPipeline.getValue().equals("Example Pipeline") ){
              exService.pipeline = new String[]{"gov.va.vinci.example.pipeline.Pipeline"};
            } else if(choiceBoxPipeline.getValue().equals("Optimization Pipeline")){
              exService.pipeline = new String[]{"gov.va.vinci.optimization.pipeline.Pipeline"};
            } else if(choiceBoxPipeline.getValue().equals( "Scalability Pipeline")){
              exService.pipeline = new String[]{"gov.va.vinci.scalability.pipeline.Pipeline"};
            }else {
              exService.pipeline = new String[]{"gov.va.vinci.example.pipeline.Pipeline"};
            }
  
            try {
              exService.run();
              aggregateDescPath.setText(exService.getAggregatePath());
            } catch (Exception e) {
              e.printStackTrace();
            }
            
          }
        });
        saveIt.setDaemon(true);
        saveIt.start();
        btn.setDisable(true);
        
      }
    });
    return btn;
  }
  
  private Button createStopPipelineButton(PclService exService ) {
    Button btnStop = new Button();
    btnStop.setText("Stop Pipeline");
    btnStop.setOnAction(new EventHandler<ActionEvent>() {
      
      @Override
      public void handle(ActionEvent event) {
        
        Thread saveIt = new Thread(new Runnable() {
          @Override
          public void run() {
            try {
              exService.stopService();
            } catch (Exception e) {
              e.printStackTrace();
            }
            
          }
        });
        saveIt.setDaemon(true);
        saveIt.start();
        
      }
    });
    return btnStop;
  }
  
  
  private Button createStartClientButton(File[] selectedReader, List<File> listenerList ) {
    Button btnStartClient = new Button();
    btnStartClient.setText("Run Client");
    btnStartClient.setOnAction(new EventHandler<ActionEvent>() {
      
      @Override
      public void handle(ActionEvent event) {
        
        Thread saveIt = new Thread(new Runnable() {
          @Override
          public void run() {
            try {
              
              new PclClient(new File("ServiceConfig_run.groovy"), selectedReader[0],
                  (File[]) listenerList.toArray(new File[]{})
              ).runClient();
              
            } catch (Exception e) {
              e.printStackTrace();
            }
            
          }
        });
        saveIt.setDaemon(true);
        saveIt.start();
        
        
      }
    });
    return btnStartClient;
  }
  
  private void openFile(File file) {
    try {
      desktop.open(file);
    } catch (IOException ex) {
      Logger.getLogger(
          DemoApplication.class.getName()).log(
          null, Level.SEVERE, ex
      );
    }
  }
  
  
  void addTitle() {
    
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}

