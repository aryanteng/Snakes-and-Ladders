package com.example.snakesandladders;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class Player {
    private String name;
    @FXML
    private final ImageView icon;
    private final Label scoreLabel;
    private int position;
    private boolean isOutOfHouse = false;
    private boolean isAboutToFinish = false;
    private boolean turn ;

    Player(ImageView icon, Label score, String name, boolean turn){
        this.name = name;
        this.icon = icon;
        this.scoreLabel = score;
        this.turn = turn;
    }
    public void climbLadder(int finalPosition) {
        position = finalPosition - 1;
    }

    public void snakeBite(int finalPosition) {
        position = finalPosition - 1;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isOutOfHouse() {
        return isOutOfHouse;
    }

    public void setOutOfHouse(boolean outOfHouse) {
        isOutOfHouse = outOfHouse;
    }

    public boolean isAboutToFinish() {
        return isAboutToFinish;
    }

    public void setAboutToFinish(boolean aboutToFinish) {
        isAboutToFinish = aboutToFinish;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public String getName() {
        return name;
    }

}


