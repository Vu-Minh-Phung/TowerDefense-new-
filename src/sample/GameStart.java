package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;


public class GameStart extends Application {
    public static int WIDTH = 1100;
    public static int HEIGHT = 700;
    static File sound = new File(".\\Sound\\alert.mp3");

    void playSound(File sound)
    {
        AudioClip audioClip = new AudioClip(sound.toURI().toString());
        audioClip.setCycleCount(1);
        audioClip.play();
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
    //playSound(sound);

        primaryStage.setTitle("Tower Defense");

        Group group = new Group();
        Scene theScene = new Scene(group);
        //Tao bieu do canh
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        group.getChildren().add(canvas);

        // Ve bieu do canh
        graphicsContext.setFill(Color.grayRgb(120));
        graphicsContext.fillRect(0, 0, WIDTH, HEIGHT);
        //stop();
        GameLaunch gameLaunch = new GameLaunch();

        gameLaunch.start(group,canvas, graphicsContext);

        // Display the stage
        primaryStage.setScene(theScene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
