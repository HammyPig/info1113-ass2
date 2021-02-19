package ghost;

import processing.core.PImage;
import processing.core.PApplet;
import java.util.Random;

public class Ghost extends Cell {

    private int arrayX;
    private int arrayY;

    private int dx = 0;
    private int dy = 0;
    private int direction = 2;
    private int moveCount = 0;
    private Cell previousBlock = null;
    private Cell[][] map;

    private Random rand = new Random();

    public Ghost(int x, int y, PImage sprite) {
        super(x, y, sprite);
        arrayX = x;
        arrayY = y;
    }

    public boolean collide() {
        return false;
    }

    public void setMap(Cell[][] map) {
        this.map = map;
    }

    public void tick() {
        /*
        moveCount++;

        if (moveCount >= 10) {
            direction = rand.nextInt(3) + 1;
            while (!canMove(direction)) {
                direction = rand.nextInt(3) + 1;
            }
            if (canMove(direction)) {
                dy = (direction - 2) * (direction % 2);
                dx = (direction - 1 - 2) * ((direction - 1) % 2);

                map[arrayY][arrayX] = previousBlock;
                arrayX += dx;
                arrayY += dy;
                previousBlock = map[arrayY][arrayX];
                map[arrayY][arrayX] = this;
            } else {}

            moveCount = 0;
        }
        */
    }
    /*
    public boolean canMove(int direction) {
        int ddx = (direction - 2) % 2;
        int ddy = (direction - 1) % 2;
        Cell incoming = map[arrayY + ddy][arrayX + ddx]; // look at incoming cell based on potential direction
        
        if (incoming == null || !incoming.collide()) {
            dx = ddx;
            dy = ddy;
            return true;
        }
        
        return false;
    }
    */
}