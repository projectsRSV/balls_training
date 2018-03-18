package balls;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private final double ROOM_X = 600;
    private final double ROOM_Y = 600;
    private final double RADIUS = 20;
    private final double BALL_NUM = 25;

    private List<Ball> balls = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        RadialGradient g = new RadialGradient(
                0, 0,
                0.35, 0.35,
                0.5,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0.0, Color.WHITE),
                new Stop(1.0, Color.RED));

        for (int i = 0; i < BALL_NUM; i++) {
            balls.add(new Ball(ROOM_X, ROOM_Y, RADIUS, balls));
        }

        Group root = new Group();
        root.getChildren().addAll(balls);
        Scene scene = new Scene(root, ROOM_X, ROOM_Y);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bouncing Balls");
        primaryStage.show();

        KeyFrame keyFrame = new KeyFrame(Duration.millis(10), event -> {
            for (Ball b : balls) {
                b.move();
            }
        });
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

}
