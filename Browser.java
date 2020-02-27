//package org.o7planning.javafx.webview;
 
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
 
public class Browser extends Application {
 
   @Override
   public void start(final Stage stage) {
 
       TextField addressBar = new TextField();
       addressBar.setText("https://google.com");
       String url = addressBar.getText();
       Button goButton = new Button("Go!");
       //Label stateLabel = new Label();
 
       //stateLabel.setTextFill(Color.RED);
       //ProgressBar progressBar = new ProgressBar();
 
       final WebView browser = new WebView();
       final WebEngine webEngine = browser.getEngine();
       browser.setPrefSize(5000, 5000);
 
       // A Worker load the page
       Worker<Void> worker = webEngine.getLoadWorker();
       webEngine.setUserAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
       webEngine.load(url);
 
        // Listening to the status of worker
       worker.stateProperty().addListener(new ChangeListener<State>() {
 
           @Override
           public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) {
               //stateLabel.setText("Loading state: " + newValue.toString());
               if (newValue == Worker.State.SUCCEEDED) {
                   stage.setTitle(webEngine.getLocation());
                   //stateLabel.setText("Finish!");
               }
           }
       });
 
       // Bind the progress property of ProgressBar
       // with progress property of Worker
       //progressBar.progressProperty().bind(worker.progressProperty());
 
       goButton.setOnAction(new EventHandler<ActionEvent>() {
 
           @Override
           public void handle(ActionEvent event) {
               String url = addressBar.getText();
               // Load the page.
               webEngine.load(url);
           }
       });
 
       VBox root = new VBox();
       //root.getChildren().addAll(addressBar, goButton, stateLabel, progressBar, browser);
       root.getChildren().addAll(addressBar, goButton, browser);
 
       Scene scene = new Scene(root);
 
       stage.setTitle("DimBrowser");
       stage.setScene(scene);
       //stage.setWidth(1920);
       //stage.setHeight(1080);
       stage.setMaximized(true);
 
       stage.show();
   }
 
   public static void main(String[] args) {
       launch(args);
   }
 
}