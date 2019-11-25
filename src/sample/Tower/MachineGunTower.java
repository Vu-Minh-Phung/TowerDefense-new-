package sample.Tower;

import javafx.scene.shape.Circle;

public class MachineGunTower extends Tower {
    public MachineGunTower(double positionX, double positionY, double width, double height, int airId) {
        super(positionX, positionY, width, height, airId);
        damageTower = 7;
        areaTower = 90;
        speedTower = 15;
        displayAreaTower = new Circle(getX() + getWidth()/2, getY() + getHeight()/2, areaTower);
        idBulletTower = 2;
        System.out.println("Machine Gun");
    }

}
