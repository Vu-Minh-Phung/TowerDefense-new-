package sample.Place;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sample.GameLaunch;
import sample.Room;

public class Target implements Place{
    public static int idX;
    public static int idY;
    public static int positionX = idX * Room.blockSize + GameLaunch.paddingX;
    public static int positionY = idY *Room.blockSize + GameLaunch.paddingY;
    public int widthTarget;
    public int heightTarget;

    public Target(){
        widthTarget = Room.blockSize;
        heightTarget = Room.blockSize;
    }

    @Override
    public void drawPlace(GraphicsContext graphicsContext, Image image) {
        graphicsContext.drawImage(image, positionX, positionY , widthTarget, heightTarget);
    }

    public void destroyEnemy(){
        for(int i = 0; i < GameLaunch.enemies.size(); i++){
            if(GameLaunch.enemies.get(i).getX() == positionX
            && GameLaunch.enemies.get(i).getY() == positionY){
                GameLaunch.enemies.get(i).attackHealth();
            }
        }
    }

}
