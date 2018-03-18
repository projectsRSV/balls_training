package ball;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
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

public class Ball extends Application {
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
        Circle ball1 = new Circle(0, 0, 20);
        ball1.setFill(g);
        Circle ball2 = new Circle(0, 0, 20);
        ball2.setFill(g);

        Group root = new Group();
        root.getChildren().addAll(ball1, ball2);
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bouncing Ball");
        primaryStage.show();

        TranslateTransition t1 = new TranslateTransition(Duration.millis(2000), ball1);
        t1.setFromX(ball1.getRadius());
        t1.setToX(scene.getWidth() - ball1.getRadius());
        t1.setFromY(scene.getHeight() / 2);
        t1.setToY(scene.getHeight() / 2);
        t1.setByX(100);
        t1.setCycleCount(Transition.INDEFINITE);
        t1.setAutoReverse(true);
        t1.setInterpolator(Interpolator.LINEAR);

        TranslateTransition t2 = new TranslateTransition(Duration.millis(2000), ball2);
        t2.setFromX(scene.getWidth() / 2);
        t2.setToX(scene.getWidth() / 2);
        t2.setFromY(ball2.getRadius());
        t2.setToY(scene.getHeight() - ball2.getRadius());
        t2.setByY(10);
        t2.setCycleCount(Transition.INDEFINITE);
        t2.setAutoReverse(true);
        t2.setInterpolator(Interpolator.LINEAR);

        ParallelTransition s = new ParallelTransition(t1, t2);
        s.play();
    }
}