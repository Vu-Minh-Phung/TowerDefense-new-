package sample.Tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.GameLaunch;

public class SniperTower extends Tower {
    public SniperTower(double positionX, double positionY, double width, double height, int airId) {
        super(positionX, positionY, width, height, airId);
        damageTower = 1200;
        areaTower = 240;
        speedTower = 80;
        speedBulletTower = 5;
        idBulletTower = 1;
    }
    public void drawArea(GraphicsContext graphicsContext){
        //try{
        graphicsContext.setFill(Color.rgb(200, 200, 200, 0.1));
        graphicsContext.fillOval(displayAreaTower.getCenterX() - displayAreaTower.getRadius(), displayAreaTower.getCenterY() - displayAreaTower.getRadius(), displayAreaTower.getRadius() * 2, displayAreaTower.getRadius() * 2);
        graphicsContext.setStroke(Color.rgb(200, 150, 150, 0.5));
        graphicsContext.strokeOval(displayAreaTower.getCenterX() - displayAreaTower.getRadius(), displayAreaTower.getCenterY() - displayAreaTower.getRadius(), displayAreaTower.getRadius() * 2, displayAreaTower.getRadius() * 2);



        if(shoting && shotMob != -1) {
            if (GameLaunch.enemies.get(shotMob).isDead()) {
                shoting = false;
                shotMob = -1;
            }
        }
    }
}
