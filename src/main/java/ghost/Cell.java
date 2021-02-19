package ghost;

import processing.core.PImage;
import processing.core.PApplet;

public abstract class Cell {

    protected int x;
    protected int y;
    protected PImage sprite;

    public Cell(int x, int y, PImage sprite) {
        this.x = x * 16;
        this.y = y * 16;
        this.sprite = sprite;
    }

    public abstract boolean collide();

    public void draw(PApplet app) {
        app.image(sprite, x, y);
    }

}