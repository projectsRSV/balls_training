package balls;


import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Ball extends Circle {
    private double radius;
    private double width;
    private double height;
    private double speedX;
    private double speedY;
    private List<Ball> balls;

    public Ball(double width, double height, double radius, List<Ball> balls) {
        super();
        this.radius = radius;
        this.width = width;
        this.height = height;
        this.balls = balls;

        RadialGradient g = new RadialGradient(
                0, 0,
                0.35, 0.35,
                0.5,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0.0, Color.WHITE),
                new Stop(1.0, Color.RED));

        super.setFill(g);
        super.setRadius(radius);
        super.setCenterX(ThreadLocalRandom.current().nextDouble(radius, width - radius));
        super.setCenterY(ThreadLocalRandom.current().nextDouble(radius, height - radius));
        while (checkIntersect(balls)) {
            super.setCenterY(ThreadLocalRandom.current().nextDouble(radius, height - radius));
        }
        this.speedX = ThreadLocalRandom.current().nextInt(1, 5);
        this.speedY = ThreadLocalRandom.current().nextInt(1, 5);
    }

    private boolean checkIntersect(List<Ball> balls) {
        for (Ball b : balls) {
            if (b != this && b.intersects(super.getLayoutBounds())) {
                return true;
            }
        }
        return false;
    }

    public void move() {
        this.setCenterX(this.getCenterX() + speedX);
        this.setCenterY(this.getCenterY() + speedY);

        if (this.getCenterX() >= width - radius) {
            this.setCenterX(width - radius);
            speedX = -speedX;
        }
        if (this.getCenterX() <= radius) {
            this.setCenterX(radius);
            speedX = -speedX;
        }
        if (this.getCenterY() >= height - radius) {
            this.setCenterY(height - radius);
            speedY = -speedY;
        }
        if (this.getCenterY() <= radius) {
            this.setCenterY(radius);
            speedY = -speedY;
        }

        if (checkIntersect(balls)) {
//                double tempX = b.speedX;
//                double tempY = b.speedY;
//                b.speedX = this.speedX;
//                b.speedY = this.speedY;
//                this.speedY = tempX;
//                this.speedY = tempY;
            speedY = -speedY;
            speedX = -speedX;
        }
    }
}
