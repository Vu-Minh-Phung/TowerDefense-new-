package sample.Tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sample.Bullet.Bullet;
import sample.GameLaunch;
import sample.Room;

public class NormalTower extends  Tower{
    public NormalTower(double positionX, double positionY, double width, double height, int airId) {
        super(positionX, positionY, width, height, airId);
        damageTower = 500;
        areaTower = 150;
        speedTower = 40;
        displayAreaTower = new Circle(getX() + getWidth()/2, getY() + getHeight()/2, areaTower);
    }
    public void drawArea(GraphicsContext graphicsContext){
        graphicsContext.setFill(Color.rgb(200, 200, 200, 0.1));
        graphicsContext.fillOval(displayAreaTower.getCenterX() - displayAreaTower.getRadius(), displayAreaTower.getCenterY() - displayAreaTower.getRadius(), displayAreaTower.getRadius() * 2, displayAreaTower.getRadius() * 2);
        graphicsContext.setStroke(Color.rgb(150, 200, 200, 0.5));
        graphicsContext.strokeOval(displayAreaTower.getCenterX() - displayAreaTower.getRadius(), displayAreaTower.getCenterY() - displayAreaTower.getRadius(), displayAreaTower.getRadius() * 2, displayAreaTower.getRadius() * 2);

        if(shoting && shotMob != -1) {

            if (GameLaunch.enemies.get(shotMob).isDead()) {
                shoting = false;
                shotMob = -1;
            }
        }
    }
}
