package sample.Bullet;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import sample.GameLaunch;
import sample.Room;


public class Bullet extends Rectangle {
    public double targetPositionBulletX;
    public double targetPositionBulletY;

    public int speedBullet;
    public int damageBullet;

    public int idBullet;
    public int idBulletTower;

    public Bullet(int idBulletTower, int idEnemy, double positionBulletX, double positionBulletY, double bulletSize, int speed, int damageBullet){
        super(positionBulletX, positionBulletY, bulletSize, bulletSize);
        this.speedBullet = speed;
        this.idBullet = idEnemy;
        this.damageBullet = damageBullet;
        this.idBulletTower = idBulletTower;
    }

    public void drawBullet(GraphicsContext graphicsContext){
        double times = System.nanoTime() / 1000000000.0;;
        double line = Math.sqrt(Math.pow(targetPositionBulletX - getX(), 2) + Math.pow(targetPositionBulletY - getY(), 2));

        setX(getX() + speedBullet * (targetPositionBulletX - getX()) / line);
        setY(getY() + speedBullet * (targetPositionBulletY - getY()) / line);

        //System.out.println("positionBulletX : " + getX() + " | " + "positionBulletY : " + getY());
        graphicsContext.drawImage(GameLaunch.titlesBullet[idBulletTower].getFrame(times), getX(), getY(), Room.blockSize / 4, Room.blockSize / 4);
    }

    public void update() {
        try {
            if (!GameLaunch.enemies.isEmpty()) {
                if (GameLaunch.enemies.size() <= idBullet) {
                    idBullet = GameLaunch.enemies.size() - 1;
                }
                if (GameLaunch.enemies.size() > idBullet) {
                    targetPositionBulletX = GameLaunch.enemies.get(idBullet).getX() + getWidth() / 2;
                    targetPositionBulletY = GameLaunch.enemies.get(idBullet).getY() + getHeight() / 2;
                }
            }
        }
        catch (Exception e){
            System.out.println("Error: Update bullet");
        }
    }



}
