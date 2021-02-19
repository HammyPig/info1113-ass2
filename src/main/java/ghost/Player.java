package ghost;

import processing.core.PImage;
import processing.core.PApplet;

public class Player extends Cell {

    private int arrayX;
    private int arrayY;

    private int lives;

    private int dx = 1;
    private int dy;
    private int xOffset;
    private int direction = 3;
    private int requestedDirection = 0;
    private int moveCount = 0;
    private Cell[][] map;

    private PImage[] playerSprites;
    private int animationCount = 0;

    public Player(int x, int y, int lives, PImage[] playerSprites) {
        super(x, y, playerSprites[0]);
        this.arrayX = x;
        this.arrayY = y;
        this.lives = lives;
        this.playerSprites = playerSprites;
    }

    public boolean collide() {
        return false;
    }

    public void tick() {
        animate();
        printStats();

        map[arrayY][arrayX] = null;
        arrayX = x / 16;
        arrayY = y / 16;
        map[arrayY][arrayX] = this;

        dx = (direction - 2) % 2;
        dy = (direction - 1) % 2;

        if (canMove(direction)) {
            x += dx;
            y += dy;
        }

        xOffset = ((x - 8) % 16 - 8);

        // Opposite directions can change anytime
        if (direction != requestedDirection && ((direction - requestedDirection) % 2) == 0) {
            System.out.println("changing direction");
            direction = requestedDirection;
        } else {
            if ((x % 16) == 0) {
                if (requestedDirection == direction) {
                    ;
                }
            }
        }
        
        /*
        if (canMove(direction)) {
            x += dx;
            y += dy;
        }
        
        // Check at each block interval if request move is available
        if ((x % 16) == 0 && (y % 16) == 0)
            if (canMove(requestedDirection)) {

            }


            if (canMove(requestedDirection)) {
                moveCount++;
                x += dx;
                y += dy;
                if (moveCount >= 16) {
                    map[arrayY][arrayX] = null;
                    arrayX += dx;
                    arrayY += dy;
                    map[arrayY][arrayX] = this;
                    x = arrayX * 16;
                    y = arrayY * 16;
                    moveCount = 0;
                }  
            }
        }
        */
    }

    @Override
    public void draw(PApplet app) {
        app.image(sprite, x - 4, y - 5);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLives() {
        return lives;
    }

    public void setMap(Cell[][] map) {
        this.map = map;
    }

    public void setDir(int direction) {
        requestedDirection = direction;
    }

    public void printStats() {
        System.out.println(String.format("Current:  x: %d, y: %d", arrayX, arrayY));
        System.out.println(String.format("Incoming: x: %d, y: %d", arrayX + dx, arrayY + dy));
        System.out.println(String.format("Requestion direction: %d", requestedDirection));
        System.out.println(String.format("Direction: %d", direction));

        System.out.print(xOffset);
        System.out.print(" ");

    }

    public void animate() {
        animationCount++;

        // Loop through mouth animation
        if (animationCount <= 10) {
            sprite = playerSprites[direction];
        } else if (animationCount <= 20) {
            sprite = playerSprites[4];
        } else {
            animationCount = 0;
        }
    }

    public boolean canMove(int direction) {
        int dx = (direction - 2) % 2;
        int dy = (direction - 1) % 2;
        Cell incoming = map[arrayY + dy][arrayX + dx];

        System.out.println(incoming);

        if (incoming == null || !incoming.collide()) {
            return true;
        }

        return false;
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