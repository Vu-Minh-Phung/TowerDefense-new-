package sample;

import sample.Place.Place;
import sample.Place.Spawn;
import sample.Place.Target;

import java.io.File;
import java.util.Scanner;

public class Save {
    public Save(){}

    public void loadSave(File loadPath, Block[][] blocks){

        try {
            Scanner loadScanner = new Scanner(loadPath);

            Room.worldWidth = loadScanner.nextInt();
            Room.worldHeight = loadScanner.nextInt();
            GameLaunch.MapId = loadScanner.nextInt();

            for (int y = 0; y < blocks.length; y++)
                for (int x = 0; x < blocks[y].length; x++) {
                    blocks[y][x].groundId = loadScanner.nextInt();
                }
            for (int y = 0; y < blocks.length; y++)
                for (int x = 0; x < blocks[y].length; x++) {
                    blocks[y][x].airId = loadScanner.nextInt();
                }
            while (loadScanner.hasNext()){
                String str = (String)loadScanner.next();
                System.out.println(str);
                if(str.equals("Spawn")){
                    GameLaunch.spawnPlace.idX = loadScanner.nextInt();
                    GameLaunch.spawnPlace.idY = loadScanner.nextInt();
                    GameLaunch.spawnPlace.positionX = GameLaunch.spawnPlace.idX * Room.blockSize + GameLaunch.paddingX;
                    GameLaunch.spawnPlace.positionY = GameLaunch.spawnPlace.idY *Room.blockSize + GameLaunch.paddingY;
                    blocks[GameLaunch.spawnPlace.idY][GameLaunch.spawnPlace.idX].airId = 9;
                    System.out.println(GameLaunch.spawnPlace.idX + " | " + GameLaunch.spawnPlace.idY);
                }
                else if(str.equals("Target")){
                    GameLaunch.targetPlace.idX = loadScanner.nextInt();
                    GameLaunch.targetPlace.idY = loadScanner.nextInt();
                    GameLaunch.targetPlace.positionX = GameLaunch.targetPlace.idX * Room.blockSize + GameLaunch.paddingX;
                    GameLaunch.targetPlace.positionY = GameLaunch.targetPlace.idY * Room.blockSize + GameLaunch.paddingY;
                    blocks[GameLaunch.targetPlace.idY][GameLaunch.targetPlace.idX].airId = 0;
                    System.out.println(GameLaunch.targetPlace.idX + " | " + GameLaunch.targetPlace.idY);
                }
            }
            loadScanner.close();
        }
        catch (Exception e){
            System.out.println("Error: Load file text!");
        }
    }

}
