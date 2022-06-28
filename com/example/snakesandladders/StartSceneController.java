package com.example.snakesandladders;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class StartSceneController {
    private final int[][] snakeList_1 = {
        {99,78},
        {96,47},
        {94,73},
        {63,59},
        {64,37},
        {70,49},
        {42,22},
        {25,4},
        {33,27},
        {29,10},
    };
    private final int[][] snakeList_2 = {
        {99,78},
        {96,65},
        {94,48},
        {90,49},
        {81,63},
        {66,47},
        {57,19},
        {43,22},
        {36,14},
        {11,9},
    };
    private final int[][] snakeList_3 = {
        {99,80},
        {95,75},
        {92,88},
        {74,53},
        {64,60},
        {62,19},
        {46,25},
        {49,11},
        {16,6},
    };
    private final int[][] snakeList_4 = {
        {98,58},
        {96,84},
        {94,73},
        {90,72},
        {68,13},
        {86,54},
        {61,59},
        {56,25},
        {43,38},
        {23,5},
    };
    private final int[][] snakeList_5 = {
        {97,78},
        {95,56},
        {88,24},
        {62,18},
        {70,49},
        {32,10},
        {25,6},
    };
    private final int[][] ladderList_1 = {
        {2,23},
        {8,14},
        {26,35},
        {31,86},
        {38,44},
        {41,60},
        {56,77},
        {69,90},
        {79,81},
        {88,92}
    };
    private final int[][] ladderList_2 = {
        {4,25},
        {8,51},
        {28,46},
        {39,60},
        {44,80},
        {52,68},
        {69,93},
        {64,85},
        {71,92},
        {84,98}
    };
    private final int[][] ladderList_3 = {
        {2,38},
        {7,14},
        {8,31},
        {15,26},
        {21,42},
        {28,84},
        {36,44},
        {51,67},
        {71,91},
        {78,98},
        {87,94}
    };
    private final int[][] ladderList_4 = {
        {3,21},
        {16,26},
        {8,55},
        {37,76},
        {50,70},
        {32,48},
        {64,83},
        {77,95},
        {89,91},
        {80,99}
    };
    private final int[][] ladderList_5 = {
        {2,38},
        {4,14},
        {8,30},
        {21,42},
        {28,76},
        {50,67},
        {71,92},
        {80,99},
    };

    public void MainPage(ActionEvent event) throws IOException {
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

        Random random = new Random();
        int boxNumber = 1 + random.nextInt(5);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
        Parent root = loader.load();
        MainSceneController scene2Controller = loader.getController();
        scene2Controller.SetBox(boxNumber);
        if (boxNumber == 1){
            scene2Controller.SetList(snakeList_1, ladderList_1);
        }
        else if (boxNumber == 2){
            scene2Controller.SetList(snakeList_2, ladderList_2);
        }
        else if (boxNumber == 3){
            scene2Controller.SetList(snakeList_3, ladderList_3);
        }
        else if (boxNumber == 4){
            scene2Controller.SetList(snakeList_4, ladderList_4);
        }
        else {
            scene2Controller.SetList(snakeList_5, ladderList_5);
        }
        scene2Controller.SetTurnGIF();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
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
        Stage dialog = (Stage) alert.getDialogPane().getScene().getWindow();
        dialog.getIcons().add(new Image("file:src/main/resources/ICON.png"));
        alert.setHeaderText("Thanks for using Snakes and Ladders!");
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
