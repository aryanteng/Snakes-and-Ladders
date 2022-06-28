package com.example.snakesandladders;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MainSceneController {
    private final Media ladderClimbMedia = new Media(new File("src/main/resources/audios/ladder.wav").toURI().toString());
    private final MediaPlayer ladderMediaPlayer = new MediaPlayer(ladderClimbMedia);
    private final Media moveMedia = new Media(new File("src/main/resources/audios/move.wav").toURI().toString());
    private final MediaPlayer moveMediaPlayer = new MediaPlayer(moveMedia);
    private final Media diceMedia = new Media(new File("src/main/resources/audios/dice.wav").toURI().toString());
    private final MediaPlayer diceMediaPlayer = new MediaPlayer(diceMedia);
    private final Media snakeMedia = new Media(new File("src/main/resources/audios/snake.mp3").toURI().toString());
    private final MediaPlayer snakeMediaPlayer = new MediaPlayer(snakeMedia);
    private final Media houseMedia = new Media(new File("src/main/resources/audios/house.wav").toURI().toString());
    private final MediaPlayer houseMediaPlayer = new MediaPlayer(houseMedia);
    private final Media buttonMedia = new Media(new File("src/main/resources/audios/button.wav").toURI().toString());
    private final MediaPlayer buttonMediaPlayer = new MediaPlayer(buttonMedia);
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ImageView player1TurnImageView;
    @FXML
    private ImageView player2TurnImageView;
    @FXML
    private ImageView diceImageView;
    @FXML
    private Button rollDiceButton;
    @FXML
    private ImageView first;
    @FXML
    private ImageView second;
    @FXML
    private Label player1score;
    @FXML
    private Label player2score;
    @FXML
    private ImageView Box;
    @FXML
    private ImageView HomeIcon1;
    @FXML
    private ImageView HomeIcon2;
    @FXML
    private Label alertLabel;
    @FXML
    private ImageView DiceRollIcon;

    private int diceNumber;
    private boolean gameEnded = false;
    private Player playerOne = new Player(first, player1score, "first", true);
    private Player playerTwo = new Player(second, player2score, "second", false);
    private Player mainPlayer;
    private boolean flagForEndGif = true;
    private final Dice dice = new Dice();
    private final int[][] positionList = {
            {9, 0},
            {9, 1},
            {9, 2},
            {9, 3},
            {9, 4},
            {9, 5},
            {9, 6},
            {9, 7},
            {9, 8},
            {9, 9},

            {8, 9},
            {8, 8},
            {8, 7},
            {8, 6},
            {8, 5},
            {8, 4},
            {8, 3},
            {8, 2},
            {8, 1},
            {8, 0},

            {7, 0},
            {7, 1},
            {7, 2},
            {7, 3},
            {7, 4},
            {7, 5},
            {7, 6},
            {7, 7},
            {7, 8},
            {7, 9},

            {6, 9},
            {6, 8},
            {6, 7},
            {6, 6},
            {6, 5},
            {6, 4},
            {6, 3},
            {6, 2},
            {6, 1},
            {6, 0},

            {5, 0},
            {5, 1},
            {5, 2},
            {5, 3},
            {5, 4},
            {5, 5},
            {5, 6},
            {5, 7},
            {5, 8},
            {5, 9},

            {4, 9},
            {4, 8},
            {4, 7},
            {4, 6},
            {4, 5},
            {4, 4},
            {4, 3},
            {4, 2},
            {4, 1},
            {4, 0},

            {3, 0},
            {3, 1},
            {3, 2},
            {3, 3},
            {3, 4},
            {3, 5},
            {3, 6},
            {3, 7},
            {3, 8},
            {3, 9},

            {2, 9},
            {2, 8},
            {2, 7},
            {2, 6},
            {2, 5},
            {2, 4},
            {2, 3},
            {2, 2},
            {2, 1},
            {2, 0},

            {1, 0},
            {1, 1},
            {1, 2},
            {1, 3},
            {1, 4},
            {1, 5},
            {1, 6},
            {1, 7},
            {1, 8},
            {1, 9},

            {0, 9},
            {0, 8},
            {0, 7},
            {0, 6},
            {0, 5},
            {0, 4},
            {0, 3},
            {0, 2},
            {0, 1},
            {0, 0},
    };
    private int[][] ladderList;
    private int[][] snakeList;

    public void SetBox(int image) {
        Box.setImage(new Image("file:src/main/resources/Box" + image + ".png"));
    }

    public void SetTurnGIF() {
        player1TurnImageView.setImage(new Image("file:src/main/resources/Turn.gif"));
    }

    public void SetList(int[][] snake_arr, int[][] ladder_arr) {
        snakeList = snake_arr;
        ladderList = ladder_arr;
    }

    public void EndPage(ActionEvent event) throws IOException {
        Media winningMedia = new Media(new File("src/main/resources/audios/winning.wav").toURI().toString());
        MediaPlayer winningMediaPlayer = new MediaPlayer(winningMedia);
        winningMediaPlayer.setAutoPlay(true);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EndingScene.fxml"));
        root = loader.load();
        EndSceneController scene2Controller = loader.getController();
        if (Objects.equals(mainPlayer.getName(), "first")) {
            scene2Controller.displayName("PLAYER 1");
        } else {
            scene2Controller.displayName("PLAYER 2");
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void HomePage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartingScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void Leave(ActionEvent event) throws RuntimeException{
        buttonMediaPlayer.play();
        buttonMediaPlayer.setOnEndOfMedia(new Runnable()
        {
            public void run()
            {
                buttonMediaPlayer.stop();
            }
        });
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Leave Game");
        Stage dialog = (Stage) alert.getDialogPane().getScene().getWindow();
        dialog.getIcons().add(new Image("file:src/main/resources/ICON.png"));
        alert.setHeaderText("You are in the middle of your game!");
        alert.setContentText("Are you sure you want to leave?");
        ImageView icon = new ImageView("file:src/main/resources/ICON.png");
        icon.setFitHeight(48);
        icon.setFitWidth(48);
        alert.getDialogPane().setGraphic(icon);
        if (alert.showAndWait().get() == ButtonType.OK) {
            try {
                HomePage(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void RollDice(ActionEvent event) {
        snakeMediaPlayer.setOnEndOfMedia(new Runnable()
        {
            public void run()
            {
                snakeMediaPlayer.stop();
            }
        });
        houseMediaPlayer.setOnEndOfMedia(new Runnable()
        {
            public void run()
            {
                houseMediaPlayer.stop();
            }
        });
        diceMediaPlayer.play();
        Timeline timeline1 = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    diceImageView.setImage(new Image("file:src/main/resources/whitedice.gif"));
                    rollDiceButton.setDisable(true);
                    DiceRollIcon.setImage(null);
                }),
                new KeyFrame(Duration.seconds(0.5), e -> {
                    mainPlayer.setPosition(mainPlayer.getPosition() + 1);
                    moveMediaPlayer.play();
                    moveMediaPlayer.setOnEndOfMedia(new Runnable()
                    {
                        public void run()
                        {
                            moveMediaPlayer.stop();
                        }
                    });
                    diceImageView.setImage(new Image("file:src/main/resources/" + diceNumber + ".png"));

                    if (Objects.equals(mainPlayer.getName(), "first")) {
                        player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                    } else {
                        player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                    }

                }),
                new KeyFrame(Duration.seconds(1.0), e -> {
                    rollDiceButton.setDisable(false);
                    DiceRollIcon.setImage(new Image("file:src/main/resources/Turn.gif"));
                    for (int[] value : ladderList) {
                        if (mainPlayer.getPosition() == value[0] - 1) {
                            mainPlayer.climbLadder(value[1]);
                            ladderMediaPlayer.play();
                            if (Objects.equals(mainPlayer.getName(), "first")) {
                                alertLabel.setText("Player 1 climbed a ladder!");
                            } else {
                                alertLabel.setText("Player 2 climbed a ladder!");
                            }
                        }
                    }
                    for (int[] ints : snakeList) {
                        if (mainPlayer.getPosition() == ints[0] - 1) {
                            mainPlayer.snakeBite(ints[1]);
                            snakeMediaPlayer.play();
                            if (Objects.equals(mainPlayer.getName(), "first")) {
                                alertLabel.setText("Player 1 bitten by a snake!");
                            } else {
                                alertLabel.setText("Player 2 bitten by a snake!");
                            }

                        }
                    }
                    if (Objects.equals(mainPlayer.getName(), "first")) {
                        player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                    } else {
                        player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                    }
                })
        );
        Timeline timeline2 = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    diceImageView.setImage(new Image("file:src/main/resources/whitedice.gif"));
                    rollDiceButton.setDisable(true);
                    DiceRollIcon.setImage(null);
                }),
                new KeyFrame(Duration.seconds(0.5), e -> {
                    mainPlayer.setPosition(mainPlayer.getPosition() + 1);
                    moveMediaPlayer.play();
                    moveMediaPlayer.setOnEndOfMedia(new Runnable()
                    {
                        public void run()
                        {
                            moveMediaPlayer.stop();
                        }
                    });
                    diceImageView.setImage(new Image("file:src/main/resources/" + diceNumber + ".png"));

                    if (Objects.equals(mainPlayer.getName(), "first")) {
                        player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                    } else {
                        player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                    }
                }),
                new KeyFrame(Duration.seconds(0.82), e -> {
                    mainPlayer.setPosition(mainPlayer.getPosition() + 1);
                    moveMediaPlayer.play();
                    moveMediaPlayer.setOnEndOfMedia(new Runnable()
                    {
                        public void run()
                        {
                            moveMediaPlayer.stop();
                        }
                    });
                    diceImageView.setImage(new Image("file:src/main/resources/" + diceNumber + ".png"));
                    if (Objects.equals(mainPlayer.getName(), "first")) {
                        player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                    } else {
                        player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                    }

                }),
                new KeyFrame(Duration.seconds(1.32), e -> {
                    rollDiceButton.setDisable(false);
                    DiceRollIcon.setImage(new Image("file:src/main/resources/Turn.gif"));
                    for (int[] value : ladderList) {
                        if (mainPlayer.getPosition() == value[0] - 1) {
                            mainPlayer.climbLadder(value[1]);
                            ladderMediaPlayer.play();
                            if (Objects.equals(mainPlayer.getName(), "first")) {
                                alertLabel.setText("Player 1 climbed a ladder!");
                            } else {
                                alertLabel.setText("Player 2 climbed a ladder!");
                            }
                        }
                    }
                    for (int[] ints : snakeList) {
                        if (mainPlayer.getPosition() == ints[0] - 1) {
                            mainPlayer.snakeBite(ints[1]);
                            snakeMediaPlayer.play();
                            if (Objects.equals(mainPlayer.getName(), "first")) {
                                alertLabel.setText("Player 1 bitten by a snake!");
                            } else {
                                alertLabel.setText("Player 2 bitten by a snake!");
                            }

                        }
                    }
                    if (Objects.equals(mainPlayer.getName(), "first")) {
                        player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                    } else {
                        player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                    }
                })

        );
        Timeline timeline3 = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    diceImageView.setImage(new Image("file:src/main/resources/whitedice.gif"));
                    rollDiceButton.setDisable(true);
                    DiceRollIcon.setImage(null);
                }),
                new KeyFrame(Duration.seconds(0.5), e -> {
                    mainPlayer.setPosition(mainPlayer.getPosition() + 1);
                    diceImageView.setImage(new Image("file:src/main/resources/" + diceNumber + ".png"));
                    moveMediaPlayer.play();
                    moveMediaPlayer.setOnEndOfMedia(new Runnable()
                    {
                        public void run()
                        {
                            moveMediaPlayer.stop();
                        }
                    });
                    if (Objects.equals(mainPlayer.getName(), "first")) {
                        player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                    } else {
                        player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                    }
                }),
                new KeyFrame(Duration.seconds(0.82), e -> {
                    mainPlayer.setPosition(mainPlayer.getPosition() + 1);
                    moveMediaPlayer.play();
                    moveMediaPlayer.setOnEndOfMedia(new Runnable()
                    {
                        public void run()
                        {
                            moveMediaPlayer.stop();
                        }
                    });
                    diceImageView.setImage(new Image("file:src/main/resources/" + diceNumber + ".png"));
                    if (Objects.equals(mainPlayer.getName(), "first")) {
                        mainPlayer.setPosition(mainPlayer.getPosition() + 1);
                        player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                    } else {
                        player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                    }
                }),
                new KeyFrame(Duration.seconds(1.14), e -> {
                    mainPlayer.setPosition(mainPlayer.getPosition() + 1);
                    moveMediaPlayer.play();
                    moveMediaPlayer.setOnEndOfMedia(new Runnable()
                    {
                        public void run()
                        {
                            moveMediaPlayer.stop();
                        }
                    });
                    diceImageView.setImage(new Image("file:src/main/resources/" + diceNumber + ".png"));
                    if (Objects.equals(mainPlayer.getName(), "first")) {
                        player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                    } else {
                        player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                    }
                }),
                new KeyFrame(Duration.seconds(1.64), e -> {
                    rollDiceButton.setDisable(false);
                    DiceRollIcon.setImage(new Image("file:src/main/resources/Turn.gif"));
                    for (int[] value : ladderList) {
                        if (mainPlayer.getPosition() == value[0] - 1) {
                            mainPlayer.climbLadder(value[1]);
                            ladderMediaPlayer.play();
                            if (Objects.equals(mainPlayer.getName(), "first")) {
                                alertLabel.setText("Player 1 climbed a ladder!");
                            } else {
                                alertLabel.setText("Player 2 climbed a ladder!");
                            }
                        }
                    }
                    for (int[] ints : snakeList) {
                        if (mainPlayer.getPosition() == ints[0] - 1) {
                            mainPlayer.snakeBite(ints[1]);
                            snakeMediaPlayer.play();
                            if (Objects.equals(mainPlayer.getName(), "first")) {
                                alertLabel.setText("Player 1 bitten by a snake!");
                            } else {
                                alertLabel.setText("Player 2 bitten by a snake!");
                            }

                        }
                    }
                    if (Objects.equals(mainPlayer.getName(), "first")) {
                        player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                    } else {
                        player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                    }
                })
        );
        Timeline timeline4 = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    diceImageView.setImage(new Image("file:src/main/resources/whitedice.gif"));
                    rollDiceButton.setDisable(true);
                    DiceRollIcon.setImage(null);
                }),
                new KeyFrame(Duration.seconds(0.5), e -> {
                    moveMediaPlayer.play();
                    moveMediaPlayer.setOnEndOfMedia(new Runnable()
                    {
                        public void run()
                        {
                            moveMediaPlayer.stop();
                        }
                    });
                    mainPlayer.setPosition(mainPlayer.getPosition() + 1);
                    diceImageView.setImage(new Image("file:src/main/resources/" + diceNumber + ".png"));

                    if (Objects.equals(mainPlayer.getName(), "first")) {

                        player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                    } else {
                        player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                    }
                }),
                new KeyFrame(Duration.seconds(0.82), e -> {
                    moveMediaPlayer.play();
                    moveMediaPlayer.setOnEndOfMedia(new Runnable()
                    {
                        public void run()
                        {
                            moveMediaPlayer.stop();
                        }
                    });
                    mainPlayer.setPosition(mainPlayer.getPosition() + 1);
                    diceImageView.setImage(new Image("file:src/main/resources/" + diceNumber + ".png"));

                    if (Objects.equals(mainPlayer.getName(), "first")) {
                        player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                    } else {
                        player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                    }
                }),
                new KeyFrame(Duration.seconds(1.14), e -> {
                    moveMediaPlayer.play();
                    moveMediaPlayer.setOnEndOfMedia(new Runnable()
                    {
                        public void run()
                        {
                            moveMediaPlayer.stop();
                        }
                    });
                    mainPlayer.setPosition(mainPlayer.getPosition() + 1);
                    diceImageView.setImage(new Image("file:src/main/resources/" + diceNumber + ".png"));

                    if (Objects.equals(mainPlayer.getName(), "first")) {
                        player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                    } else {
                        player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                    }
                }),
                new KeyFrame(Duration.seconds(1.46), e -> {
                    moveMediaPlayer.play();
                    moveMediaPlayer.setOnEndOfMedia(new Runnable()
                    {
                        public void run()
                        {
                            moveMediaPlayer.stop();
                        }
                    });
                    mainPlayer.setPosition(mainPlayer.getPosition() + 1);
                    diceImageView.setImage(new Image("file:src/main/resources/" + diceNumber + ".png"));
                    if (Objects.equals(mainPlayer.getName(), "first")) {
                        player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                    } else {
                        player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                    }

                }),
                new KeyFrame(Duration.seconds(1.96), e -> {
                    rollDiceButton.setDisable(false);
                    DiceRollIcon.setImage(new Image("file:src/main/resources/Turn.gif"));
                    for (int[] value : ladderList) {
                        if (mainPlayer.getPosition() == value[0] - 1) {
                            mainPlayer.climbLadder(value[1]);
                            ladderMediaPlayer.play();
                            if (Objects.equals(mainPlayer.getName(), "first")) {
                                alertLabel.setText("Player 1 climbed a ladder!");
                            } else {
                                alertLabel.setText("Player 2 climbed a ladder!");
                            }
                        }
                    }
                    for (int[] ints : snakeList) {
                        if (mainPlayer.getPosition() == ints[0] - 1) {
                            mainPlayer.snakeBite(ints[1]);
                            snakeMediaPlayer.play();
                            if (Objects.equals(mainPlayer.getName(), "first")) {
                                alertLabel.setText("Player 1 bitten by a snake!");
                            } else {
                                alertLabel.setText("Player 2 bitten by a snake!");
                            }

                        }
                    }
                    if (Objects.equals(mainPlayer.getName(), "first")) {
                        player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                    } else {
                        player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                    }
                })
        );
        Timeline timeline5 = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    diceImageView.setImage(new Image("file:src/main/resources/whitedice.gif"));
                    rollDiceButton.setDisable(true);
                    DiceRollIcon.setImage(null);
                }),
                new KeyFrame(Duration.seconds(0.5), e -> {
                    moveMediaPlayer.play();
                    moveMediaPlayer.setOnEndOfMedia(new Runnable()
                    {
                        public void run()
                        {
                            moveMediaPlayer.stop();
                        }
                    });
                    mainPlayer.setPosition(mainPlayer.getPosition() + 1);
                    diceImageView.setImage(new Image("file:src/main/resources/" + diceNumber + ".png"));
                    if (Objects.equals(mainPlayer.getName(), "first")) {
                        player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                    } else {
                        player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                    }
                }),
                new KeyFrame(Duration.seconds(0.82), e -> {
                    moveMediaPlayer.play();
                    moveMediaPlayer.setOnEndOfMedia(new Runnable()
                    {
                        public void run()
                        {
                            moveMediaPlayer.stop();
                        }
                    });
                    mainPlayer.setPosition(mainPlayer.getPosition() + 1);
                    diceImageView.setImage(new Image("file:src/main/resources/" + diceNumber + ".png"));
                    if (Objects.equals(mainPlayer.getName(), "first")) {
                        player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                    } else {
                        player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                    }
                }),
                new KeyFrame(Duration.seconds(1.14), e -> {
                    moveMediaPlayer.play();
                    moveMediaPlayer.setOnEndOfMedia(new Runnable()
                    {
                        public void run()
                        {
                            moveMediaPlayer.stop();
                        }
                    });
                    mainPlayer.setPosition(mainPlayer.getPosition() + 1);
                    diceImageView.setImage(new Image("file:src/main/resources/" + diceNumber + ".png"));

                    if (Objects.equals(mainPlayer.getName(), "first")) {
                        player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                    } else {
                        player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                    }
                }),
                new KeyFrame(Duration.seconds(1.46), e -> {
                    moveMediaPlayer.play();
                    moveMediaPlayer.setOnEndOfMedia(new Runnable()
                    {
                        public void run()
                        {
                            moveMediaPlayer.stop();
                        }
                    });
                    mainPlayer.setPosition(mainPlayer.getPosition() + 1);
                    diceImageView.setImage(new Image("file:src/main/resources/" + diceNumber + ".png"));

                    if (Objects.equals(mainPlayer.getName(), "first")) {
                        player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                    } else {
                        player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                    }
                }),
                new KeyFrame(Duration.seconds(1.78), e -> {
                    moveMediaPlayer.play();
                    moveMediaPlayer.setOnEndOfMedia(new Runnable()
                    {
                        public void run()
                        {
                            moveMediaPlayer.stop();
                        }
                    });
                    mainPlayer.setPosition(mainPlayer.getPosition() + 1);
                    diceImageView.setImage(new Image("file:src/main/resources/" + diceNumber + ".png"));
                    if (Objects.equals(mainPlayer.getName(), "first")) {
                        player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                    } else {
                        player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                    }

                }),
                new KeyFrame(Duration.seconds(2.28), e -> {
                    rollDiceButton.setDisable(false);
                    DiceRollIcon.setImage(new Image("file:src/main/resources/Turn.gif"));
                    for (int[] value : ladderList) {
                        if (mainPlayer.getPosition() == value[0] - 1) {
                            mainPlayer.climbLadder(value[1]);
                            ladderMediaPlayer.play();
                            if (Objects.equals(mainPlayer.getName(), "first")) {
                                alertLabel.setText("Player 1 climbed a ladder!");
                            } else {
                                alertLabel.setText("Player 2 climbed a ladder!");
                            }
                        }
                    }
                    for (int[] ints : snakeList) {
                        if (mainPlayer.getPosition() == ints[0] - 1) {
                            mainPlayer.snakeBite(ints[1]);
                            snakeMediaPlayer.play();
                            if (Objects.equals(mainPlayer.getName(), "first")) {
                                alertLabel.setText("Player 1 bitten by a snake!");
                            } else {
                                alertLabel.setText("Player 2 bitten by a snake!");
                            }

                        }
                    }
                    if (Objects.equals(mainPlayer.getName(), "first")) {
                        player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                    } else {
                        player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                    }
                })
        );
        Timeline timeline6 = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    diceImageView.setImage(new Image("file:src/main/resources/whitedice.gif"));
                    rollDiceButton.setDisable(true);
                    DiceRollIcon.setImage(null);
                }),
                new KeyFrame(Duration.seconds(0.5), e -> {
                    moveMediaPlayer.play();
                    moveMediaPlayer.setOnEndOfMedia(new Runnable()
                    {
                        public void run()
                        {
                            moveMediaPlayer.stop();
                        }
                    });
                    mainPlayer.setPosition(mainPlayer.getPosition() + 1);
                    diceImageView.setImage(new Image("file:src/main/resources/" + diceNumber + ".png"));
                    if (Objects.equals(mainPlayer.getName(), "first")) {
                        player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                    } else {
                        player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                    }
                }),
                new KeyFrame(Duration.seconds(0.82), e -> {
                    moveMediaPlayer.play();
                    moveMediaPlayer.setOnEndOfMedia(new Runnable()
                    {
                        public void run()
                        {
                            moveMediaPlayer.stop();
                        }
                    });
                    mainPlayer.setPosition(mainPlayer.getPosition() + 1);
                    diceImageView.setImage(new Image("file:src/main/resources/" + diceNumber + ".png"));

                    if (Objects.equals(mainPlayer.getName(), "first")) {
                        player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                    } else {
                        player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                    }
                }),
                new KeyFrame(Duration.seconds(1.14), e -> {
                    moveMediaPlayer.play();
                    moveMediaPlayer.setOnEndOfMedia(new Runnable()
                    {
                        public void run()
                        {
                            moveMediaPlayer.stop();
                        }
                    });
                    mainPlayer.setPosition(mainPlayer.getPosition() + 1);
                    diceImageView.setImage(new Image("file:src/main/resources/" + diceNumber + ".png"));

                    if (Objects.equals(mainPlayer.getName(), "first")) {
                        player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                    } else {
                        player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                    }
                }),
                new KeyFrame(Duration.seconds(1.46), e -> {
                    moveMediaPlayer.play();
                    moveMediaPlayer.setOnEndOfMedia(new Runnable()
                    {
                        public void run()
                        {
                            moveMediaPlayer.stop();
                        }
                    });
                    mainPlayer.setPosition(mainPlayer.getPosition() + 1);
                    diceImageView.setImage(new Image("file:src/main/resources/" + diceNumber + ".png"));
                    if (Objects.equals(mainPlayer.getName(), "first")) {
                        player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                    } else {
                        player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                    }
                }),
                new KeyFrame(Duration.seconds(1.78), e -> {
                    moveMediaPlayer.play();
                    moveMediaPlayer.setOnEndOfMedia(new Runnable()
                    {
                        public void run()
                        {
                            moveMediaPlayer.stop();
                        }
                    });
                    mainPlayer.setPosition(mainPlayer.getPosition() + 1);
                    diceImageView.setImage(new Image("file:src/main/resources/" + diceNumber + ".png"));

                    if (Objects.equals(mainPlayer.getName(), "first")) {
                        player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                    } else {
                        player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                    }
                }),
                new KeyFrame(Duration.seconds(2.10), e -> {
                    moveMediaPlayer.play();
                    moveMediaPlayer.setOnEndOfMedia(new Runnable()
                    {
                        public void run()
                        {
                            moveMediaPlayer.stop();
                        }
                    });

                    mainPlayer.setPosition(mainPlayer.getPosition() + 1);
                    diceImageView.setImage(new Image("file:src/main/resources/" + diceNumber + ".png"));
                    if (Objects.equals(mainPlayer.getName(), "first")) {
                        player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                    } else {
                        player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                    }

                }),
                new KeyFrame(Duration.seconds(2.6), e -> {
                    rollDiceButton.setDisable(false);
                    DiceRollIcon.setImage(new Image("file:src/main/resources/Turn.gif"));
                    for (int[] value : ladderList) {
                        if (mainPlayer.getPosition() == value[0] - 1) {
                            mainPlayer.climbLadder(value[1]);
                            ladderMediaPlayer.play();
                            if (Objects.equals(mainPlayer.getName(), "first")) {
                                alertLabel.setText("Player 1 climbed a ladder!");
                            } else {
                                alertLabel.setText("Player 2 climbed a ladder!");
                            }
                        }
                    }
                    for (int[] ints : snakeList) {
                        if (mainPlayer.getPosition() == ints[0] - 1) {
                            mainPlayer.snakeBite(ints[1]);
                            snakeMediaPlayer.play();
                            if (Objects.equals(mainPlayer.getName(), "first")) {
                                alertLabel.setText("Player 1 bitten by a snake!");
                            } else {
                                alertLabel.setText("Player 2 bitten by a snake!");
                            }

                        }
                    }
                    if (Objects.equals(mainPlayer.getName(), "first")) {
                        player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                    } else {
                        player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                        GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                        GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                    }
                    if (mainPlayer.getPosition() == 99) {
                        gameEnded = true;
                    }
                })
        );
        alertLabel.setText(null);
        if (playerOne.isTurn()) {
            mainPlayer = playerOne;
        } else {
            if (playerTwo.isTurn()) {
                mainPlayer = playerTwo;
            }
        }
        diceNumber = dice.RollDice();
        if (mainPlayer.isOutOfHouse()) {
            if (mainPlayer.getPosition() >= 93) {
                mainPlayer.setAboutToFinish(true);
            }
            if (mainPlayer.isAboutToFinish()) {
                if (diceNumber <= 99 - mainPlayer.getPosition()) {
                    if (mainPlayer.getPosition() + diceNumber == 99) {
                        flagForEndGif = false;
                        mainPlayer.setPosition((mainPlayer.getPosition() + diceNumber));
                        Timeline endgame = new Timeline(
                            new KeyFrame(Duration.ZERO, e -> {
                                diceImageView.setImage(new Image("file:src/main/resources/whitedice.gif"));
                                rollDiceButton.setDisable(true);
                                DiceRollIcon.setImage(null);
                            }),
                            new KeyFrame(Duration.seconds(0.5), e -> {
                                moveMediaPlayer.play();
                                moveMediaPlayer.setOnEndOfMedia(new Runnable()
                                {
                                    public void run()
                                    {
                                        moveMediaPlayer.stop();
                                    }
                                });
                                diceImageView.setImage(new Image("file:src/main/resources/" + diceNumber + ".png"));
                                if (Objects.equals(mainPlayer.getName(), "first")) {
                                    player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                                    GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                                    GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);
                                } else {
                                    player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                                    GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                                    GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                                }
                            }),
                            new KeyFrame(Duration.seconds(1.5), e -> {
                                try {
                                    EndPage(event);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            })
                        );
                        endgame.play();
                    }
                    else if (diceNumber == 1) {
                        timeline1.play();
                    } else if (diceNumber == 2) {
                        timeline2.play();
                    } else if (diceNumber == 3) {
                        timeline3.play();
                    } else if (diceNumber == 4) {
                        timeline4.play();
                    } else if (diceNumber == 5) {
                        timeline5.play();
                    } else if (diceNumber == 6) {
                        timeline6.play();
                    }
                } else {
                    Timeline timeline = new Timeline(
                            new KeyFrame(Duration.ZERO, e -> {
                                diceImageView.setImage(new Image("file:src/main/resources/whitedice.gif"));
                                rollDiceButton.setDisable(true);
                                DiceRollIcon.setImage(null);
                            }),
                            new KeyFrame(Duration.seconds(0.5), e -> {
                                diceImageView.setImage(new Image("file:src/main/resources/" + diceNumber + ".png"));
                                rollDiceButton.setDisable(false);
                                DiceRollIcon.setImage(new Image("file:src/main/resources/Turn.gif"));
                            })
                    );
                    timeline.play();
                }
            } else {
                if (diceNumber == 1) {
                    timeline1.play();
                } else if (diceNumber == 2) {
                    timeline2.play();
                } else if (diceNumber == 3) {
                    timeline3.play();
                } else if (diceNumber == 4) {
                    timeline4.play();
                } else if (diceNumber == 5) {
                    timeline5.play();
                } else if (diceNumber == 6) {
                    timeline6.play();
                }
            }
        }
        Timeline timeline_gif = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                }),
                new KeyFrame(Duration.seconds(1), e -> {
                    if (playerOne.isTurn()) {
                        player1TurnImageView.setImage(new Image("file:src/main/resources/Turn.gif"));
                        player2TurnImageView.setImage(new Image("file:src/main/resources/white.png"));
                    } else {
                        if (playerTwo.isTurn()) {
                            player2TurnImageView.setImage(new Image("file:src/main/resources/Turn.gif"));
                            player1TurnImageView.setImage(new Image("file:src/main/resources/white.png"));
                        }
                    }
                })
        );

        if(flagForEndGif)
            timeline_gif.play();
        if (!mainPlayer.isOutOfHouse()) {
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, e -> {
                        diceImageView.setImage(new Image("file:src/main/resources/whitedice.gif"));
                        rollDiceButton.setDisable(true);
                        DiceRollIcon.setImage(null);
                    }),
                    new KeyFrame(Duration.seconds(0.5), e -> {
                        diceImageView.setImage(new Image("file:src/main/resources/" + diceNumber + ".png"));
                        rollDiceButton.setDisable(false);
                        DiceRollIcon.setImage(new Image("file:src/main/resources/Turn.gif"));
                        if (diceNumber == 1) {
                            mainPlayer.setOutOfHouse(true);
                            houseMediaPlayer.play();
                            houseMediaPlayer.setOnEndOfMedia(new Runnable()
                            {
                                public void run()
                                {
                                    houseMediaPlayer.stop();
                                }
                            });
                            if (Objects.equals(mainPlayer.getName(), "first")) {
                                HomeIcon1.setImage(null);
                                player1score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                                GridPane.setRowIndex(first, positionList[mainPlayer.getPosition()][0]);
                                GridPane.setColumnIndex(first, positionList[mainPlayer.getPosition()][1]);

                            } else {
                                HomeIcon2.setImage(null);
                                player2score.setText(String.valueOf(mainPlayer.getPosition() + 1));
                                GridPane.setRowIndex(second, positionList[mainPlayer.getPosition()][0]);
                                GridPane.setColumnIndex(second, positionList[mainPlayer.getPosition()][1]);
                            }
                            mainPlayer.setPosition(0);
                        }
                    })
            );
            timeline.play();
        }
        if (playerOne.isTurn()) {
            playerOne.setTurn(false);
            playerTwo.setTurn(true);
            playerOne = mainPlayer;
        } else {
            playerOne.setTurn(true);
            playerTwo.setTurn(false);
            playerTwo = mainPlayer;
        }
        diceMediaPlayer.setOnEndOfMedia(new Runnable()
        {
            public void run()
            {
                diceMediaPlayer.stop();
            }
        });

        ladderMediaPlayer.setOnEndOfMedia(new Runnable()
        {
            public void run()
            {
                ladderMediaPlayer.stop();
            }
        });


        houseMediaPlayer.setOnEndOfMedia(new Runnable()
        {
            public void run()
            {
                houseMediaPlayer.stop();
            }
        });
    }}
