package sample.Enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.GameLaunch;

public class TankerEnemy extends Enemy{
    public TankerEnemy(){
        enemyId = 2;
        speedEnemy = 1;
        maxHealth = 4000;
        damEnemy = 2;
        healthEnemy = maxHealth;
    }
}
