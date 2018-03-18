package ballHardWay;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.concurrent.ThreadLocalRandom;

public class BallHardWay extends Application {
    private int ROOM_X = 600;
    private int ROOM_Y = 600;
    private int speedX = 2;
    private int speedY = 5;
    private int BALL_SIZE = 20;

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
        Circle ball = new Circle(BALL_SIZE, g);
        ball.setCenterX(BALL_SIZE);
        ball.setCenterY(BALL_SIZE);

        Group root = new Group();
        root.getChildren().addAll(ball);
        Scene scene = new Scene(root, ROOM_X, ROOM_Y);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bouncing Ball");
        primaryStage.show();

        KeyFrame keyFrame = new KeyFrame(Duration.millis(10), event -> {
            ball.setCenterX(ball.getCenterX() + speedX);
            ball.setCenterY(ball.getCenterY() + speedY);
            if (ball.getCenterX() >= (ROOM_X - BALL_SIZE) || ball.getCenterX() <= BALL_SIZE) {
                speedX = -(speedX + ThreadLocalRandom.current().nextInt(1, 2));
            }
            if (ball.getCenterY() >= (ROOM_Y - BALL_SIZE) || ball.getCenterY() <= BALL_SIZE) {
                speedY = -(speedY + ThreadLocalRandom.current().nextInt(1, 2));
            }
        });
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

}
