package sample;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

//import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import sample.Bullet.Bullet;
import sample.Enemy.*;
import sample.Place.Spawn;
import sample.Place.Target;
import sample.Tower.Tower;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GameLaunch {

    public enum GameStatus{INIT, RUNNING, END};
    private GameStatus gameStatus = GameStatus.INIT;

    public static int myWidthGame = Room.worldWidth * Room.blockSize;
    public static int myHeightGame = Room.worldHeight * Room.blockSize;
    public static int paddingX = (GameStart.WIDTH - GameLaunch.myWidthGame) / 2;
    public static int paddingY = 5;
    public static Point2D mse;

    public static int life = 1;
    public static int coin = 100;

    boolean isFirst = true;
    boolean loadLv = false;
    public static int MapId = 0;
    public String mission1 = "Map";
    public int lv = 1;

    public boolean isWin = false;
    public boolean isLose = false;


    public Room room;
    private Save save;
    private Store store;
    public static List<Bullet> bullets = new ArrayList<>();

    public static List<Tower> towers = new ArrayList<>();

    public static double times = 0.017;
    public static boolean create = true;
    public static List<Enemy> enemies= new ArrayList<>();
    public static Spawn spawnPlace;
    public static Target targetPlace;

    public GameLaunch(){
        define();
    }

    public static Image[] titlesMap = new Image[10];
    public static Image[] titlesGround = new Image[10];
    public static Image[] titlesAir = new Image[20];
    public static Image[] titlesIcon = new Image[10];
    public static AnimatedImages[] titlesBullet = new AnimatedImages[10];
    public static AnimatedImages[] titlesEnemy = new AnimatedImages[20];
    public static List<MediaPlayer> mediaGame = new ArrayList<>();


    public void define() {
        try {
            titlesMap[0] = Draw.loadImage(Value.pathMap + "start1.png");
            titlesMap[1] = Draw.loadImage(Value.pathMap + "Map1.jpg");
            titlesMap[2] = Draw.loadImage(Value.pathMap + "Map2.jpg");
            titlesMap[3] = Draw.loadImage(Value.pathMap + "Map3.jpg");
            titlesMap[4] = Draw.loadImage(Value.pathMap + "Map4.jpg");
            titlesMap[5] = Draw.loadImage(Value.pathMap + "Map5.jpg");
            titlesMap[6] = Draw.loadImage(Value.pathMap + "Lose.jpg");
            titlesMap[7] = Draw.loadImage(Value.pathMap + "nextMap.jpg");
            titlesMap[8] = Draw.loadImage(Value.pathMap + "Win1.jpg");
            titlesMap[9] = Draw.loadImage(Value.pathMap + "Background1.jpg");
            System.out.println("Loaded titlesMap!");

            titlesGround[0] = Draw.loadImage(Value.pathBricks + "brick1.png");
            titlesGround[1] = Draw.loadImage(Value.pathBricks + "brick2.png");
            titlesGround[2] = Draw.loadImage(Value.pathBricks + "brick3.png");
            System.out.println("Loaded titlesGround!");

            titlesAir[0] = Draw.loadImage( Value.pathTower + "start.png");
            titlesAir[1] = Draw.loadImage(Value.pathTower + "recycle.png");
            titlesAir[2] = Draw.loadImage( Value.pathTower + "dolar.png");
            titlesAir[3] = Draw.loadImage(Value.pathTower + "update.png");
            titlesAir[4] = Draw.loadImage( Value.pathTower + "tower1.png");
            titlesAir[5] = Draw.loadImage(Value.pathTower + "tower2.png");
            titlesAir[6] = Draw.loadImage( Value.pathTower + "tower3.png");
            titlesAir[7] = Draw.loadImage(Value.pathTower + "tower4.png");
            titlesAir[8] = Draw.loadImage(Value.pathTower + "tower5.png");
            titlesAir[9] = Draw.loadImage(Value.pathTower + "Target.png");
            titlesAir[10] = Draw.loadImage(Value.pathTower + "skip.png");
            System.out.println("Loaded titlesAir!");

            titlesBullet[0] = new AnimatedImages();
            Draw.loadAnimatedImage(titlesBullet[0], Value.pathBullet + "boommerang", 1 , 8);
            titlesBullet[1] = new AnimatedImages();
            Draw.loadAnimatedImage(titlesBullet[1], Value.pathBullet + "round", 1 , 4);
            titlesBullet[2] = new AnimatedImages();
            Draw.loadAnimatedImage(titlesBullet[2], Value.pathBullet + "bullet", 1 , 2);

            titlesIcon[0] = Draw.loadImage(Value.pathIcon + "life.png");
            titlesIcon[1] = Draw.loadImage(Value.pathIcon + "coin.png");
            titlesIcon[2] = Draw.loadImage(Value.pathIcon + "cell.png");
            titlesIcon[3] = Draw.loadImage(Value.pathIcon + "button2.png");
            System.out.println("Loaded titlesIcon!");


            int j = 0;
            for(int i = 0; i <= 3; i++){
                titlesEnemy[j] = new AnimatedImages();
                Draw.loadAnimatedImage(titlesEnemy[j], Value.pathEnemy+"SpeedEnemy", i*4 + 1 , (i*4 + 4));
                j++;
            }

            for(int i = 0; i <= 3; i++){
                titlesEnemy[j] = new AnimatedImages();
                Draw.loadAnimatedImage(titlesEnemy[j], Value.pathEnemy+"NormalEnemy", i*4 + 1 , (i*4 + 4));
                j++;
            }

            for(int i = 0; i <= 3; i++){
                titlesEnemy[j] = new AnimatedImages();
                Draw.loadAnimatedImage(titlesEnemy[j], Value.pathEnemy+"TankEnemy", i*4 + 1 , (i*4 + 4));
                j++;
            }

            for(int i = 0; i <= 3; i++){
                titlesEnemy[j] = new AnimatedImages();
                Draw.loadAnimatedImage(titlesEnemy[j], Value.pathEnemy+"BossEnemy", i*4 + 1 , (i*4 + 4));
                j++;
            }

            System.out.println("Loaded titlesEnemy!" + j);

            mediaGame.add( Draw.loadMedia(Value.sound1) );
            mediaGame.add( Draw.loadMedia(Value.sound2) );
            mediaGame.add( Draw.loadMedia(Value.sound3) );
            mediaGame.add( Draw.loadMedia(Value.sound9) );
            mediaGame.add( Draw.loadMedia(Value.sound10) );
        }
        catch (Exception e){
            System.out.println("Cannot load Image");
        }
    }



    public void initGameLaunch(Group group, GraphicsContext graphicsContext){
        try {
            for(int i = 0; i < mediaGame.size(); i++){
                mediaGame.get(i).pause();
            }
            mediaGame.get(1).play();

            room = new Room();

            spawnPlace= new Spawn();
            targetPlace = new Target();

            if (loadLv) {
                lv++;
                loadLv = false;
            }
                save = new Save();
                save.loadSave(new File(mission1 + lv), room.blocks);


            store = new Store();

        }
        catch (Exception e){
            System.out.println("Error: initGameLaunch");
        }
    }

    public void destroyBullet(){
        for(int i = 0; i < GameLaunch.bullets.size(); i++){
            if (GameLaunch.bullets.get(i).intersects(GameLaunch.enemies.get(GameLaunch.bullets.get(i).idBullet).getBoundsInLocal())) {
                GameLaunch.enemies.get(GameLaunch.bullets.get(i).idBullet).loseHealth(GameLaunch.bullets.get(i).damageBullet);
                System.out.println(GameLaunch.bullets.get(i).damageBullet);
                GameLaunch.bullets.remove(i);
            }
        }
    }

    public void checkWin(){
        for(int i = 0; i < enemies.size(); i++){
            if( !enemies.get(i).isDead()){
                isWin = false;
                //System.out.println("zo");
                return;
            }
        }
        isWin = true;
        Draw.playSound(3);
    }

    public void checkLose(){
        if(life <= 0 ){
            isLose = true;
            System.out.println("zo");
            Draw.playSound(4);
        }
        else isLose = false;
    }

    public void screenNextMap(GraphicsContext graphicsContext){
        graphicsContext.clearRect(0, 0, GameStart.WIDTH, GameStart.HEIGHT);     // Delete Screen
        graphicsContext.drawImage(titlesMap[7], 0, 0, GameStart.WIDTH, GameStart.HEIGHT);       // Background back
        graphicsContext.setStroke(Color.BLUE);
        graphicsContext.setLineWidth(2);
        graphicsContext.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 25));
        graphicsContext.strokeText("Next Map. Press to continue!", GameStart.WIDTH/2, GameStart.HEIGHT/1.2);
    }

    public void screenWinner(GraphicsContext graphicsContext){
        graphicsContext.clearRect(0, 0, GameStart.WIDTH, GameStart.HEIGHT);     // Delete Screen
        graphicsContext.drawImage(titlesMap[8], 0, 0, GameStart.WIDTH, GameStart.HEIGHT);       // Background back
        graphicsContext.setStroke(Color.YELLOW);
        graphicsContext.setLineWidth(2);
        graphicsContext.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        graphicsContext.strokeText("You Win", GameStart.WIDTH/2, GameStart.HEIGHT/1.2);
        graphicsContext.setTextAlign(TextAlignment.CENTER);
    }

    public void screenLoser(GraphicsContext graphicsContext){
        graphicsContext.clearRect(0, 0, GameStart.WIDTH, GameStart.HEIGHT);     // Delete Screen
        graphicsContext.drawImage(titlesMap[6], 0, 0, GameStart.WIDTH, GameStart.HEIGHT);       // Background back
        graphicsContext.setStroke(Color.RED);
        graphicsContext.setLineWidth(2);
        graphicsContext.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 30));
        graphicsContext.strokeText("You Lose", GameStart.WIDTH/2, GameStart.HEIGHT/1.2);
        graphicsContext.setTextAlign(TextAlignment.CENTER);

    }

    void init(Group group, GraphicsContext graphicsContext){
        group.setOnMouseClicked(e->{
            gameStatus = GameStatus.RUNNING;
            graphicsContext.fillRect(0, 0, GameStart.WIDTH, GameStart.HEIGHT);
            isFirst = true;
        });
        if(isFirst){
            Draw.playSound(0);
            isFirst = false;
        }
        graphicsContext.drawImage(titlesMap[0], 0, 0, GameStart.WIDTH, GameStart.HEIGHT);
        graphicsContext.setStroke(Color.RED);
        graphicsContext.setLineWidth(2);
        graphicsContext.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 30));
        graphicsContext.strokeText("Press to start!", GameStart.WIDTH/2, GameStart.HEIGHT/1.2);
        graphicsContext.setTextAlign(TextAlignment.CENTER);
    }

    public void start(Group group, Canvas canvas, GraphicsContext graphicsContext){
        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );

        final long timeStart = System.currentTimeMillis();

        KeyFrame kf = new KeyFrame(
            Duration.seconds(times),                // 60 FPS
            new EventHandler<ActionEvent>()
            {
                public void handle(ActionEvent ae)
                {

                    // Start Game
                    if(gameStatus == GameStatus.INIT){
                        init(group, graphicsContext);

                    }
                    else if(gameStatus == GameStatus.RUNNING) {
                        graphicsContext.clearRect(0, 0, GameStart.WIDTH, GameStart.HEIGHT);     // Delete Screen
                        graphicsContext.drawImage(titlesMap[9], 0, 0, GameStart.WIDTH, GameStart.HEIGHT);       // Background back
                        graphicsContext.drawImage(titlesMap[MapId], paddingX, paddingY, myWidthGame, myHeightGame);     // Background front

                        if (isFirst){

                            initGameLaunch(group, graphicsContext);         // Tải dữ liệu từ file, Tạo Room , Tạo Store, Tạo Enemy;
                            store.draw(group, graphicsContext, room.blocks);    // Vẽ store
                            life = 1;
                            coin = 100;
                            create = true;
                            isFirst = false;
                        }

                        spawnPlace.modSpawner(room.blocks);                 // Delay spawn Enemy
                        targetPlace.destroyEnemy();                         // Kiểm tra xóa Enemy

                        room.physic(towers);

                        room.drawRoom(graphicsContext);                     // Vẽ map, tower, start, target

                        for (int i = 0; i < bullets.size(); i++) {
                            bullets.get(i).drawBullet(graphicsContext);   // Vẽ Enemy
                            bullets.get(i).update();     // di chuyển Enemy
                        }

                        destroyBullet();

                        for(int i = 0; i< enemies.size(); i++){
                            if(enemies.get(i).inGame && !enemies.get(i).isDead()){
                                enemies.get(i).physic(room.blocks);     // di chuyển Enemy
                                enemies.get(i).drawEnemy(graphicsContext);   // Vẽ Enemy
                            }
                        }

                        store.updateIcon(graphicsContext);          // Cập nhật cửa hàng

                        checkWin();
                        checkLose();
                        if(isWin || isLose){
                            gameStatus = GameStatus.END;
                        }
                    }
                    else {
                        if(isLose){
                            screenLoser(graphicsContext);
                            loadLv = true;
                            System.out.println("Lose");
                        }
                        else {
                            if(lv == 5){
                                screenWinner(graphicsContext);
                                System.out.println("Winner");
                            }
                            else screenNextMap(graphicsContext);
                            loadLv = true;
                            System.out.println("Next map");
                        }

                        group.getChildren().setAll();
                        group.getChildren().add(canvas);

                        group.setOnMouseClicked(e->{
                            if(isWin && lv != 5) {
                                gameStatus = GameStatus.RUNNING;
                            }
                            if(isLose || (isWin && lv == 5)) {
                                gameStatus = GameStatus.INIT;
                                lv = 0;
                                //Draw.pauseSound();
                            }
                            graphicsContext.fillRect(0, 0, GameStart.WIDTH, GameStart.HEIGHT);

                            for(int i = enemies.size() - 1; i >= 0; --i){
                                enemies.remove(i);
                            }
                            for(int i = towers.size() - 1; i >= 0; --i){
                                towers.remove(i);
                            }
                            try {
                                for (int i = bullets.size() - 1; i >= 0; --i) {
                                    bullets.remove(i);
                                }
                                isFirst = true;
                            }
                            catch (Exception ez){ }
                            isWin = false;
                            isLose = false;
                        });
                    }
                }
            });
        gameLoop.getKeyFrames().add( kf );
        gameLoop.play();
    }























































}
