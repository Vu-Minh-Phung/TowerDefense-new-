package sample.Tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.Bullet.Bullet;
import sample.GameLaunch;
import sample.Room;
import sample.Value;

public class SpecialTower extends Tower{
    public SpecialTower(double positionX, double positionY, double width, double height, int airId) {
        super(positionX, positionY, width, height, airId);
        damageTower = 50;
        areaTower = 75;
        speedTower = 2;
    }

    public void physic(){
        try {
            //System.out.println("X: " + displayAreaTower.getCenterX() + " | Y: " + displayAreaTower.getCenterY() + " | Area: " + displayAreaTower.getRadius() );
            shotMob = -1;
            shoting = false;
            boolean checkShotting = false;
            for (int i = 0; i < GameLaunch.enemies.size(); i++) {
                if (towerId == Value.airNormal || towerId == Value.airTowerLaser || towerId == Value.airMachineGun || towerId == Value.airSniper || towerId == Value.airSpecial) {
                    if (GameLaunch.enemies.get(i).inGame && !GameLaunch.enemies.get(i).isDead()) {
                        if (GameLaunch.enemies.get(i).intersects(displayAreaTower.getBoundsInLocal())
                                && Math.abs((GameLaunch.enemies.get(i).getX() + GameLaunch.enemies.get(i).getWidth() / 2) - displayAreaTower.getCenterX()) < displayAreaTower.getRadius()
                                && Math.abs((GameLaunch.enemies.get(i).getY() + GameLaunch.enemies.get(i).getHeight() / 2) - displayAreaTower.getCenterY()) < displayAreaTower.getRadius()) {
                            shoting = true;
                            shotMob = i;

                            if (delaySpeedTower >= speedTower) {
                                GameLaunch.bullets.add(new Bullet(towerId, shotMob, getX() + getWidth() / 2, getY() + getHeight() / 2, Room.blockSize / 4, speedBulletTower, damageTower));
                                checkShotting = true;
                            }
                        }
                    }
                }
            }
            if(checkShotting) delaySpeedTower = 0;
            else delaySpeedTower++;
        }
        catch (Exception e){
            System.out.println("Error: Physic");
        }
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
