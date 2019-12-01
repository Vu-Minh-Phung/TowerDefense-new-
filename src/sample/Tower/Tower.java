package sample.Tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import sample.Bullet.Bullet;
import sample.Draw;
import sample.GameLaunch;
import sample.Room;
import sample.Value;

public class Tower extends Rectangle{

    public Circle displayAreaTower;
    public int towerId;
    protected int damageTower;
    protected int areaTower;
    protected int speedBulletTower;

    protected int speedTower = 40;
    protected int delaySpeedTower = speedTower;
    public int upgrade = 1;
    public AudioClip audioClipBullet = new AudioClip((Value.sound4).toURI().toString());
    int idBulletTower = 0;

    public Tower(double positionX, double positionY, double width, double height, int airId) {
        super(positionX, positionY, width, height);
        this.towerId = airId;
        this.areaTower = 150;
        this.damageTower = 1;
        this.speedBulletTower = 3;
        displayAreaTower = new Circle(getX() + getWidth()/2, getY() + getHeight()/2, areaTower);
        audioClipBullet = new AudioClip((Value.sound5).toURI().toString());
    }

    public int shotMob = -1;
    public boolean shoting = false;


    public void physic(){
        try {
            //System.out.println("X: " + displayAreaTower.getCenterX() + " | Y: " + displayAreaTower.getCenterY() + " | Area: " + displayAreaTower.getRadius() );
            shotMob = -1;
            shoting = false;
            for (int i = 0; i < GameLaunch.enemies.size(); i++) {
                if (towerId == Value.airNormal || towerId == Value.airTowerLaser || towerId == Value.airMachineGun || towerId == Value.airSniper || towerId == Value.airSpecial) {
                    if (GameLaunch.enemies.get(i).inGame && !GameLaunch.enemies.get(i).isDead()) {
                        if (GameLaunch.enemies.get(i).intersects(displayAreaTower.getBoundsInLocal())
                                && Math.abs((GameLaunch.enemies.get(i).getX() + GameLaunch.enemies.get(i).getWidth() / 2) - displayAreaTower.getCenterX()) < displayAreaTower.getRadius()
                                && Math.abs((GameLaunch.enemies.get(i).getY() + GameLaunch.enemies.get(i).getHeight() / 2) - displayAreaTower.getCenterY()) < displayAreaTower.getRadius()) {
                            shoting = true;
                            shotMob = i;

                            if (delaySpeedTower >= speedTower) {
                                GameLaunch.bullets.add(new Bullet(idBulletTower, shotMob, getX() + getWidth() / 2, getY() + getHeight() / 2, Room.blockSize / 4, speedBulletTower, damageTower));
                                delaySpeedTower = 0;
                                audioClipBullet.play();
                            }
                            break;
                        }
                    }
                }
            }
            delaySpeedTower++;
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

            //GameLaunch.enemies.get(shotMob).loseHealth(damageTower);

            if (GameLaunch.enemies.get(shotMob).isDead()) {
                shoting = false;
                shotMob = -1;
            }
        }
    }

    public void upgradeTower(){
        upgrade++;
        damageTower += 1;
        areaTower += 100;
        speedTower -= 1;
        displayAreaTower = new Circle(getX() + getWidth()/2, getY() + getHeight()/2, areaTower);

    }

    public void drawBlock(GraphicsContext graphicsContext) {
            graphicsContext.drawImage(GameLaunch.titlesAir[towerId], getX(), getY(), getWidth(), getHeight());
    }

}
