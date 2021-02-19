package ghost;

import processing.core.PImage;
import processing.core.PApplet;

public class Wall extends Cell {

    public Wall(int x, int y, PImage sprite) {
        super(x, y, sprite);
    }

    public boolean collide() {
        return true;
    }
}