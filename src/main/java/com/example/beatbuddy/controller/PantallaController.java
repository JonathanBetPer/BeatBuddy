package com.example.beatbuddy.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;


public class PantallaController implements Initializable {

    @FXML
    public ImageView myImageView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image im = new Image("/com/example/beatbuddy/Icons/goku.png", false);
        myImageView.setImage(im);

        Rectangle clip = new Rectangle();
        clip.setWidth(30);
        clip.setHeight(30);

        clip.setArcHeight(30);
        clip.setArcWidth(30);
        clip.setStroke(Color.BLACK);
        myImageView.setClip(clip);

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = myImageView.snapshot(parameters, null);

        myImageView.setClip(null);

        myImageView.setEffect(new DropShadow(20, Color.BLACK));
        myImageView.setImage(image);
    }
}