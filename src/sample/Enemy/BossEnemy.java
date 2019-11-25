package sample.Enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.GameLaunch;

public class BossEnemy extends Enemy{

    public BossEnemy(){
        enemyId = 3;
        speedEnemy = 1;
        maxHealth = 5000;
        healthEnemy = maxHealth;
        damEnemy = 20;
    }
}
