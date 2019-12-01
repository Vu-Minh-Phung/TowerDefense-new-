
package sample.Enemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.Block;
import sample.GameLaunch;
import sample.Room;
import sample.Value;

public abstract class Enemy extends Rectangle {

    public int xC, yC;

    public int speedEnemy = 1;       // SpeedEnemy
    public int maxHealth = 3500;
    public int healthEnemy;
    public int healthSpace = 3, healthHeight = 6;
    public int damEnemy = 1;
    public float scaleHealth;
    public boolean isDie = false;

    public int modSize = Room.blockSize;
    public int enemyId = 0;             // Xét hình ảnh

    public boolean inGame = false;

    public int modWalk = 0;

    public enum Direction {UP, DOWN, LEFT, RIGHT};

    public Direction direction = Direction.RIGHT;

    public boolean hasUpward = false;
    public boolean hasDown = false;
    public boolean hasLeft = false;
    public boolean hasRight = false;

    public AudioClip audioClipEnemy = new AudioClip((Value.sound7).toURI().toString());

    public Enemy() {
        scaleHealth = modSize;
        healthEnemy = maxHealth;
    }

    public void spawnMob(Block[][] blocks) {
        setX(GameLaunch.spawnPlace.positionX);
        setY(GameLaunch.spawnPlace.positionY);
        setWidth(modSize);
        setHeight(modSize);

        xC = GameLaunch.spawnPlace.idX;
        yC = GameLaunch.spawnPlace.idY;

        scaleHealth = modSize;
        isDie = false;
        inGame = true;
    }

    public void deleteMob() {
        if(isDie == false)  getMoney();
        isDie = true;
        inGame = false;
        audioClipEnemy.play();
    }

    public void attackHealth() {
        if(!isDead()) GameLaunch.life -= damEnemy;
        isDie = true;
    }

    public void loseHealth(int damageTower) {
        healthEnemy -= damageTower;
        checkDeath();
    }

    public void checkDeath() {
        if (healthEnemy <= 0) {
            deleteMob();
        }
    }

    public boolean isDead() {
        return isDie;
    }

    public void  getMoney(){
        GameLaunch.coin += Value.deadReward[enemyId];
    }

    public void physic(Block[][] blocks) {

        if (direction == Direction.UP) {
            setY(getY() - speedEnemy);
        }
        if (direction == Direction.DOWN) {
            setY(getY() + speedEnemy);
        }
        if (direction == Direction.RIGHT) {
            setX(getX() + speedEnemy);
        }
        if (direction == Direction.LEFT) {
            setX(getX() - speedEnemy);
        }

        modWalk += speedEnemy;

        if (modWalk >= Room.blockSize) {
            if (direction == Direction.UP) {
                yC -= 1;
                hasUpward = true;
            }
            if (direction == Direction.DOWN) {
                yC += 1;
                hasDown = true;
            }
            if (direction == Direction.LEFT) {
                xC -= 1;
                hasLeft = true;
            }
            if (direction == Direction.RIGHT) {
                xC += 1;
                hasRight = true;
            }

            try {
                if (!hasDown) {
                    if (blocks[yC - 1][xC].groundId == Value.roadId1
                    || blocks[yC - 1][xC].groundId == Value.roadId2
                    || blocks[yC - 1][xC].groundId == Value.roadId3) {
                        direction = Direction.UP;
                    }
                }
            } catch (Exception e) { }

            try {
                if (!hasUpward) {
                    if (blocks[yC + 1][xC].groundId == Value.roadId1
                    || blocks[yC + 1][xC].groundId == Value.roadId2
                    || blocks[yC + 1][xC].groundId == Value.roadId3) {
                        direction = Direction.DOWN;
                    }
                }
            } catch (Exception e) { }

            try {
                if (!hasRight) {
                    if (blocks[yC][xC - 1].groundId == Value.roadId1
                    || blocks[yC][xC - 1].groundId == Value.roadId2
                    || blocks[yC][xC - 1].groundId == Value.roadId3) {
                        direction = Direction.LEFT;
                    }
                }
            } catch (Exception e) {
            }

            try {
                if (!hasLeft) {
                    if (blocks[yC][xC + 1].groundId == Value.roadId1
                    || blocks[yC][xC + 1].groundId == Value.roadId2
                    || blocks[yC][xC + 1].groundId == Value.roadId3) {
                        direction = Direction.RIGHT;
                    }
                }
            } catch (Exception e) { }

            modWalk = 0;
            hasUpward = false;
            hasDown = false;
            hasLeft = false;
            hasRight = false;
        }
    }

    public void draw(int id, GraphicsContext graphicsContext){
            double t = System.nanoTime() / 1000000000.0;
            graphicsContext.setFill(Color.GREEN);
            scaleHealth = healthEnemy * modSize / maxHealth;

            graphicsContext.fillRect(getX(), getY() - (healthSpace + healthSpace), scaleHealth, healthHeight);
            graphicsContext.setStroke(Color.BLACK);
            graphicsContext.strokeRect(getX(), getY() - (healthSpace + healthSpace), modSize, healthHeight);

            graphicsContext.drawImage(GameLaunch.titlesEnemy[id].getFrame(t), getX(), getY(), getWidth(), getHeight());
    }

    public void drawEnemy(GraphicsContext graphicsContext){
        if (direction == Direction.DOWN) {
            draw(enemyId * 4 + 0, graphicsContext);
        }
        else if (direction == Direction.LEFT) {
            draw(enemyId * 4 + 1, graphicsContext);
        }
        else  if (direction == Direction.RIGHT) {
            draw(enemyId * 4 + 2, graphicsContext);
        }
        else {
            draw(enemyId * 4 + 3, graphicsContext);
        }
    }
}

