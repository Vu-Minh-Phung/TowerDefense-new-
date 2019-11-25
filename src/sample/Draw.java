package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Draw {
    public Draw(){ }
    public static void drawImage(double positionX, double positionY, double width, double height, Image image, Group group, boolean alignImage){
        ImageView imageView = new ImageView(image);
        imageView.setX(positionX);
        imageView.setY(positionY);

        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        imageView.setPreserveRatio(alignImage);

        group.getChildren().add(imageView);

    }
    public static Image loadImage(String name) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(name);
        Image image = new Image(inputStream);

        return image;
    }

    public static void writeText(Text text, double x, double y, Group group, int FontSize, Paint paint){
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, FontSize));
        text.setFill(paint);
        text.setX(x);
        text.setY(y);
        group.getChildren().add(text);
    }

    public static void drawImageView(double positionX, double positionY, double width, double height, ImageView imageView, Group group, boolean alignImage){

        imageView.setX(positionX);
        imageView.setY(positionY);

        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        imageView.setPreserveRatio(alignImage);

        group.getChildren().add(imageView);
    }


    public static void loadAnimatedImage(AnimatedImages animatedImages, String name, int n, int m) throws FileNotFoundException {
        Image[] image = new Image[m - n + 1];
        for (int i = n; i <= m; i++){
            System.out.println( name + i + ".png");
            image[i - n] = Draw.loadImage(name + i + ".png");
            System.out.println(name + i + ".png");
        }

        animatedImages.frames = image;
        animatedImages.duration = 0.100;
    }
}

/**

 public Image loadImages(String name) throws FileNotFoundException {
 FileInputStream inputStream = new FileInputStream(name);
 Image image = new Image(inputStream);

 return image;
 }
 */