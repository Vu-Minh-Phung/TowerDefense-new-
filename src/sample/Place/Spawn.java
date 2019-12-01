package sample.Place;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import sample.*;
import sample.Enemy.BossEnemy;

public class Spawn implements Place{
    public static int idX;
    public static int idY;
    public static int positionX;
    public static int positionY;
    public int widthSpawn;
    public int heightSpawn;

    public int delaySpawn = 100;
    public int delay = 0;
    AudioClip audioClipEnemy = new AudioClip(Value.sound6.toURI().toString());
    public Spawn(){
        widthSpawn = Room.blockSize;
        heightSpawn = Room.blockSize + 20;
    }

    @Override
    public void drawPlace(GraphicsContext graphicsContext, Image image) {
        graphicsContext.drawImage(image, positionX, positionY - 20/2, widthSpawn, heightSpawn);
    }

    public void modSpawner(Block[][] blocks) {
        if (GameLaunch.create) {
            if (delay >= delaySpawn) {
                for (int i = 0; i < GameLaunch.enemies.size(); i++) {
                    if(GameLaunch.enemies.get(i) instanceof BossEnemy){
                        //audioClipEnemy = new AudioClip(Value.sound2.toURI().toString());
                        audioClipEnemy.play();
                    }
                    if (!GameLaunch.enemies.get(i).inGame && !GameLaunch.enemies.get(i).isDead()) {
                        GameLaunch.enemies.get(i).spawnMob(blocks);
                        break;
                    }
                    if (GameLaunch.enemies.get(GameLaunch.enemies.size() - 1).inGame) {
                        GameLaunch.create = false;
                    }
                }

                delay = 0;
            } else {
                delay += 1;
                //System.out.println("delay" + delay);
            }
        }
    }
}
