module GameJavaFx {
    opens sample;
    exports sample.Bullet;
    exports sample.Enemy;
    exports sample.Place;
    exports sample.Tower;

    requires javafx.base;
    requires javafx.media;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
}