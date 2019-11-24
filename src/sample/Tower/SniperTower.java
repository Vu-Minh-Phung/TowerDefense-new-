package sample.Tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.GameLaunch;

public class SniperTower extends Tower {
    public SniperTower(double positionX, double positionY, double width, double height, int airId) {
        super(positionX, positionY, width, height, airId);
        damageTower = 1200;
        areaTower = 180;
        speedTower = 80;
        speedBulletTower = 10;
    }
    public void drawArea(GraphicsContext graphicsContext){
        //try{
        graphicsContext.setFill(Color.rgb(200, 200, 200, 0.1));
        graphicsContext.fillOval(displayAreaTower.getCenterX() - displayAreaTower.getRadius(), displayAreaTower.getCenterY() - displayAreaTower.getRadius(), displayAreaTower.getRadius() * 2, displayAreaTower.getRadius() * 2);
        graphicsContext.setStroke(Color.rgb(0, 200, 200, 0.5));
        graphicsContext.strokeOval(displayAreaTower.getCenterX() - displayAreaTower.getRadius(), displayAreaTower.getCenterY() - displayAreaTower.getRadius(), displayAreaTower.getRadius() * 2, displayAreaTower.getRadius() * 2);



        if(shoting && shotMob != -1) {
            //System.out.println("shot mod: " + shotMob);
            graphicsContext.setStroke(Color.RED);
            graphicsContext.beginPath();
            graphicsContext.moveTo(getX() + getWidth() / 2, getY() + getHeight() / 2);
            graphicsContext.lineTo(GameLaunch.enemies.get(shotMob).getX() + GameLaunch.enemies.get(shotMob).getWidth() / 2,
                    GameLaunch.enemies.get(shotMob).getY() + GameLaunch.enemies.get(shotMob).getHeight() / 2);
            graphicsContext.stroke();

            if (GameLaunch.enemies.get(shotMob).isDead()) {
                shoting = false;
                shotMob = -1;
            }
        }
    }
}
