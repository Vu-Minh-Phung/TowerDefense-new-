package sample;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import sample.Tower.*;

public class Store {

    public static int shopWidth = 8;
    public Rectangle[] position = new Rectangle[shopWidth];
    public int[] buttonId = {Value.airNormal, Value.airTowerLaser, Value.airMachineGun, Value.airSniper, Value.airSpecial, 3, 2, 1};
    public int[] buttonPrice = {20, 25, 30, 35, 50, 0, 0};

    public int buttonSize = 52;
    public int cellSpace = 3;
    public int awayFromRoom = 29;

    public int iconSize = 45;
    public int spaceIcon = 10;
    public int itemIn = 10;

    public static int helpId = -1;

    public boolean holdItem = false;

    public ImageView buttonImage[] = new ImageView[shopWidth];

    public Rectangle buttonHealth;
    public Rectangle buttonCoins;

    public Store() {
        define();
    }

    public void define() {
        for (int i = 0; i < position.length; i++) {
            buttonImage[i] = new ImageView(GameLaunch.titlesAir[buttonId[i]]);

            position[i] = new Rectangle(GameLaunch.myWidthGame / 2 - (shopWidth * buttonSize) / 2  + i * (buttonSize + cellSpace),
                    GameLaunch.myHeightGame + awayFromRoom , buttonSize, buttonSize);
        }
        buttonHealth = new Rectangle(GameLaunch.paddingX, position[0].getY(), iconSize, iconSize);
        buttonCoins = new Rectangle(GameLaunch.paddingX, position[0].getY() + buttonSize - iconSize/2 + spaceIcon, iconSize, iconSize);
    }

    public void updateIcon(GraphicsContext graphicsContext){
        graphicsContext.clearRect(buttonHealth.getX(), buttonHealth.getY(),buttonCoins.getX() + iconSize + spaceIcon, buttonHealth.getY() + iconSize / 2 + Value.FontSize / 2 );
        graphicsContext.setStroke(Color.RED);
        graphicsContext.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20));
        graphicsContext.strokeText("" + GameLaunch.life, buttonHealth.getX() + iconSize + spaceIcon, buttonHealth.getY() + iconSize / 2 + Value.FontSize / 2);
        graphicsContext.drawImage(GameLaunch.titlesIcon[0], buttonHealth.getX(), buttonHealth.getY(), buttonHealth.getWidth(), buttonHealth.getHeight());
        graphicsContext.strokeText("" + GameLaunch.coin, buttonCoins.getX() + iconSize + spaceIcon, buttonCoins.getY() + iconSize / 2 + Value.FontSize / 2);
        graphicsContext.drawImage(GameLaunch.titlesIcon[1], buttonCoins.getX(), buttonCoins.getY(), buttonCoins.getWidth(), buttonCoins.getHeight());

    }

    public void draw(Group group, GraphicsContext graphicsContext, Block[][] blocks) {

        for (int i = 0; i < position.length; i++){
            position[i].setFill(Color.grayRgb(100));
            position[i].setStroke(Color.BROWN);
            position[i].setStrokeWidth(1);
            group.getChildren().add(position[i]);

            int finalI1 = i;
            group.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    for(int j = 0; j < position.length ; j++){
                        if(position[j].contains(GameLaunch.mse)) {
                            for (int k = 0; k <= position.length; k++)
                                if (holdItem == true && buttonId[helpId] == buttonId[k]) {
                                    buttonImage[helpId].setX(position[helpId].getX() + itemIn / 2);
                                    buttonImage[helpId].setY(position[helpId].getY() + itemIn / 2);
                                    helpId = Value.airAir;
                                    holdItem = false;
                                }
                                if (buttonId[j] == Value.airTrashCan && holdItem == true) {
                                    buttonImage[helpId].setX(position[helpId].getX() + itemIn / 2);
                                    buttonImage[helpId].setY(position[helpId].getY() + itemIn / 2);
                                    helpId = Value.airAir;
                                    holdItem = false;
                                } else if (buttonId[j] != Value.airTrashCan) {
                                    helpId = j;
                                    holdItem = true;
                                }
                        }
                    }

                     if(holdItem) {
                        if ( GameLaunch.coin >= buttonPrice[helpId]) {

                             for (int y = 0; y < blocks.length; y++) {
                                 for (int x = 0; x < blocks[y].length; x++) {
                                    if (blocks[y][x].contains(GameLaunch.mse)) {
                                        if(buttonId[helpId] == 2 && (blocks[y][x].airId == Value.airNormal || blocks[y][x].airId == Value.airTowerLaser
                                        || blocks[y][x].airId == Value.airSpecial || blocks[y][x].airId == Value.airMachineGun || blocks[y][x].airId == Value.airSniper)){

                                            GameLaunch.coin += buttonPrice[blocks[y][x].airId - Value.airNormal]/2;;

                                            for(int l = 0; l < GameLaunch.towers.size(); l++){
                                                if (GameLaunch.towers.get(l).getX() == blocks[y][x].getX()
                                                        && GameLaunch.towers.get(l).getY() == blocks[y][x].getY()){
                                                    GameLaunch.towers.remove(l);
                                                    blocks[y][x].airId = -1;
                                                }
                                            }
                                            System.out.println("Hello");
                                        }
                                        else if(buttonId[helpId] == 3 && (blocks[y][x].airId == Value.airNormal || blocks[y][x].airId == Value.airTowerLaser
                                            || blocks[y][x].airId == Value.airSpecial || blocks[y][x].airId == Value.airMachineGun || blocks[y][x].airId == Value.airSniper)){
                                                for(int l = 0; l < GameLaunch.towers.size(); l++){
                                                    if(blocks[y][x].getX() == GameLaunch.towers.get(l).getX()
                                                    && blocks[y][x].getY() == GameLaunch.towers.get(l).getY()
                                                    && GameLaunch.towers.get(l).upgrade < 3
                                                    && GameLaunch.coin >= GameLaunch.towers.get(l).upgrade * buttonPrice[helpId]){
                                                        GameLaunch.towers.get(l).upgradeTower();
                                                        GameLaunch.coin -= GameLaunch.towers.get(l).upgrade * buttonPrice[helpId];
                                                        System.out.println("Hello");
                                                    }
                                                }
                                        }
                                        else
                                         if (blocks[y][x].groundId != Value.roadId1
                                             && blocks[y][x].groundId != Value.roadId2
                                             && blocks[y][x].groundId != Value.roadId3
                                             && blocks[y][x].airId == Value.airAir) {

                                                 position[finalI1].setFill(Color.RED);

                                                 buttonImage[helpId].setX(position[helpId].getX() + itemIn / 2);
                                                 buttonImage[helpId].setY(position[helpId].getY() + itemIn / 2);

                                                 if(buttonId[helpId] == Value.airNormal || buttonId[helpId] == Value.airTowerLaser || buttonId[helpId] == Value.airSpecial || buttonId[helpId] == Value.airMachineGun || buttonId[helpId] == Value.airSniper){

                                                     blocks[y][x].airId = buttonId[helpId];
                                                     GameLaunch.coin -= buttonPrice[helpId];

                                                     switch (blocks[y][x].airId){
                                                     case Value.airNormal: GameLaunch.towers.add(new NormalTower(blocks[y][x].getX(), blocks[y][x].getY(), blocks[y][x].getWidth(), blocks[y][x].getHeight(), blocks[y][x].airId));
                                                     break;
                                                     case Value.airSpecial: GameLaunch.towers.add(new SpecialTower(blocks[y][x].getX(), blocks[y][x].getY(), blocks[y][x].getWidth(), blocks[y][x].getHeight(), blocks[y][x].airId));
                                                     break;
                                                     case Value.airTowerLaser: GameLaunch.towers.add(new LaserTower(blocks[y][x].getX(), blocks[y][x].getY(), blocks[y][x].getWidth(), blocks[y][x].getHeight(), blocks[y][x].airId));
                                                     break;
                                                     case Value.airMachineGun:GameLaunch.towers.add(new MachineGunTower(blocks[y][x].getX(), blocks[y][x].getY(), blocks[y][x].getWidth(), blocks[y][x].getHeight(), blocks[y][x].airId));
                                                     break;
                                                     case Value.airSniper: GameLaunch.towers.add(new SniperTower(blocks[y][x].getX(), blocks[y][x].getY(), blocks[y][x].getWidth(), blocks[y][x].getHeight(), blocks[y][x].airId));
                                                     }

                                                 }
                                                 holdItem = false;
                                         }
                                     }
                                 }
                             }
                         }
                     }
                }
            });

            group.setOnMouseMoved(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    GameLaunch.mse = new Point2D(mouseEvent.getX(), mouseEvent.getY());
                    if(holdItem){
                        //System.out.println(Store.helpId);
                        buttonImage[helpId].setX(GameLaunch.mse.getX() - iconSize/2);
                        buttonImage[helpId].setY(GameLaunch.mse.getY() - iconSize/2);
                    }
                }
            });

            int finalI = i;
            buttonImage[i].setOnMouseMoved(e->moveMouse(finalI, group));
            buttonImage[i].setOnMouseExited(e->exitMouse(finalI, group));

            Draw.drawImage(position[i].getX(), position[i].getY(), position[i].getWidth(), position[i].getHeight(), GameLaunch.titlesIcon[2], group, true);
            Draw.drawImageView(position[i].getX() + itemIn/2, position[i].getY() + itemIn/2, position[i].getWidth() - itemIn , position[i].getHeight() - itemIn, buttonImage[i], group, true);

            if(i < buttonPrice.length)
                Draw.writeText(new Text("" + buttonPrice[i]), position[i].getX() + itemIn + cellSpace, position[i].getY() + itemIn, group, 10, Color.WHITE);
        }

    }

    public void moveMouse(int id, Group group){
        position[id].setFill(Color.WHITE);
        position[id].setStroke(Color.RED);
    }

    public void exitMouse(int id, Group group){
        position[id].setFill(Color.grayRgb(100));
        position[id].setStroke(Color.grayRgb(150));
    }

}

