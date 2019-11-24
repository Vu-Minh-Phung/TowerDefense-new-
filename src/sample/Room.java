package sample;

import javafx.scene.canvas.GraphicsContext;
import sample.Tower.Tower;

import java.util.List;

public class Room {

    public static int worldWidth = 16;
    public static int worldHeight = 9;

    public static int blockSize = 60;

    public Block[][] blocks = new Block[worldHeight][worldWidth];

    public Room() {
        createBlock();
    }

    public void createBlock() {
        for (int y = 0; y < blocks.length; y++)
            for (int x = 0; x < blocks[y].length; x++) {
                blocks[y][x] = new Block(x * blockSize + GameLaunch.paddingX, y * blockSize + GameLaunch.paddingY, blockSize, blockSize, Value.groundGrass, Value.airAir);
            }
    }

    public void drawRoom(GraphicsContext graphicsContext) {
        try {
            for (int y = 0; y < blocks.length; y++)
                for (int x = 0; x < blocks[y].length; x++) {
                    blocks[y][x].drawBlock(graphicsContext);
                }
        } catch (Exception e) {
            System.out.println("Error: drawRoom");
        }

        for (int i = 0; i < GameLaunch.towers.size(); i++) {
            if (GameLaunch.towers.get(i).towerId == Value.airNormal || GameLaunch.towers.get(i).towerId == Value.airTowerLaser || GameLaunch.towers.get(i).towerId == Value.airMachineGun
                    ||GameLaunch.towers.get(i).towerId == Value.airSniper || GameLaunch.towers.get(i).towerId == Value.airSpecial) {
                GameLaunch.towers.get(i).drawBlock(graphicsContext);
            }
        }

        for (int i = 0; i < GameLaunch.towers.size(); i++) {
            if (GameLaunch.towers.get(i).towerId == Value.airNormal || GameLaunch.towers.get(i).towerId == Value.airTowerLaser || GameLaunch.towers.get(i).towerId == Value.airMachineGun
                    ||GameLaunch.towers.get(i).towerId == Value.airSniper || GameLaunch.towers.get(i).towerId == Value.airSpecial) {
                GameLaunch.towers.get(i).drawArea(graphicsContext);
            }
        }

    }

    public void physic(List<Tower> towers) {
        for (int i = 0; i < towers.size(); i++) {
            if (towers.get(i).towerId == Value.airNormal || towers.get(i).towerId == Value.airTowerLaser || towers.get(i).towerId == Value.airMachineGun || towers.get(i).towerId == Value.airSniper || towers.get(i).towerId == Value.airSpecial) {
                towers.get(i).physic();
            }
        }
    }
}
