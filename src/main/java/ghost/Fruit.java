package ghost;

import processing.core.PImage;
import processing.core.PApplet;

public class Fruit extends Cell {

    public Fruit(int x, int y, PImage sprite) {
        super(x, y, sprite);
    }

    public boolean collide() {
        return false;
    }
}