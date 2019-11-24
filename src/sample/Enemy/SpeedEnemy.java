package sample.Enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.GameLaunch;

public class SpeedEnemy extends  Enemy{
    public SpeedEnemy(){
        enemyId = 1;
        speedEnemy = 2;
        maxHealth = 2800;
        healthEnemy = maxHealth;
    }
}
