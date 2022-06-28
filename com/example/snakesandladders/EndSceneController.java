package com.example.snakesandladders;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class EndSceneController {
    @FXML
    private Label WinningPlayer;
    private Stage stage;
    private Scene scene;
    private Stage dialog;

    public void displayName(String winner) {
        WinningPlayer.setText(winner);
    }

    public void HomePage(ActionEvent event) throws IOException {
        Media buttonMedia = new Media(new File("src/main/resources/audios/button.wav").toURI().toString());
        MediaPlayer buttonMediaPlayer = new MediaPlayer(buttonMedia);
        buttonMediaPlayer.play();
        buttonMediaPlayer.setOnEndOfMedia(new Runnable()
        {
            public void run()
            {
                buttonMediaPlayer.stop();
            }
        });
        Parent root = FXMLLoader.load(getClass().getResource("StartingScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void Exit(ActionEvent event) {
        Media buttonMedia = new Media(new File("src/main/resources/audios/button.wav").toURI().toString());
        MediaPlayer buttonMediaPlayer = new MediaPlayer(buttonMedia);
        buttonMediaPlayer.play();
        buttonMediaPlayer.setOnEndOfMedia(new Runnable()
        {
            public void run()
            {
                buttonMediaPlayer.stop();
            }
        });
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exit");
        dialog = (Stage) alert.getDialogPane().getScene().getWindow();
        dialog.getIcons().add(new Image("file:src/main/resources/ICON.png"));
        alert.setHeaderText("Thanks for using Snakes & Ladders!");
        alert.setContentText("Are you sure you want to exit?");
        ImageView icon = new ImageView("file:src/main/resources/ICON.png");
        icon.setFitHeight(48);
        icon.setFitWidth(48);
        alert.getDialogPane().setGraphic(icon);
        if (alert.showAndWait().get() == ButtonType.OK) {
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }
}
